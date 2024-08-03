package com.enuygun.utilities;
import com.enuygun.model.Element;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ElementJsonReader {
    private static final String DEFAULT_DIRECTORY_PATH = ConfigReader.getProperty("elementJsonPath");
    private static final String PLATFORM_NAME = ConfigReader.getProperty("platformName");
    private static final Map<String, Element> elementCache = new ConcurrentHashMap<>();

    public static By findElementFromJsonFiles(String elementKey) {
        Element element = elementCache.computeIfAbsent(elementKey, ElementJsonReader::loadElement);
        if (element == null) {
            throw new RuntimeException("Element not found: " + elementKey);
        }
        return ElementType.getBy(element, PLATFORM_NAME);
    }

    private static Element loadElement(String elementKey) {
        return getJsonFilesFromFolder().parallelStream()
                .flatMap(file -> parseJsonFile(file).stream())
                .filter(element -> element.getKey().equals(elementKey))
                .findFirst()
                .orElse(null);
    }

    private static List<File> getJsonFilesFromFolder() {
        File folder = new File(DEFAULT_DIRECTORY_PATH);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
        if (files == null) {
            throw new RuntimeException("No JSON files found in the specified directory");
        }
        return List.of(files);
    }

    private static List<Element> parseJsonFile(File file) {
        try (FileReader reader = new FileReader(file)) {
            return new Gson().fromJson(reader, new TypeToken<List<Element>>() {}.getType());
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file: " + file.getName(), e);
        }
    }
}
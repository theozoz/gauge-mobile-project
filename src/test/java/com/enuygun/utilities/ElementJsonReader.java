package com.enuygun.utilities;

import com.enuygun.model.Element;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.gauge.Logger;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ElementJsonReader {

    public static ConfigReader configReader = new ConfigReader();

    private static final String DEFAULT_DIRECTORY_PATH = configReader.getProperty("elementJsonPath");


    public List<File> getJsonFilesFromFolder() {
        List<File> files = new ArrayList<>();
        File folder = new File(DEFAULT_DIRECTORY_PATH);
        if (folder.exists()) {
            File[] fileList = folder.listFiles();
            if (fileList != null) {
                for (File file : fileList) {
                    if (file.getName().endsWith(".json")) {
                        files.add(file);
                    }
                }
            }
        }
        return files;
    }

    public By findElementFromJsonFiles(String elementKey) {
        List<File> files = getJsonFilesFromFolder();
        List<Element> elements;
        By by = null;
        int count = 0;

        for (File file : files) {
            Gson gson = new Gson();
            try {
                elements = gson.fromJson(new FileReader(file), new TypeToken<List<Element>>() {
                }.getType());
                for (Element element : elements) {

                    if (element.getKey().equals(elementKey)) {
                        count++;
                    }
                    if (element.getKey().equals(elementKey)) {
                        by = ElementType.elementType(element);
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        if (by == null) {
            Logger.error("Element is not found "+ elementKey);
            throw new RuntimeException("Element is not found");
        } else {
            if (count > 1) {
                throw new RuntimeException("Element is duplicated");
            } else {
                return by;
            }
        }

    }

}

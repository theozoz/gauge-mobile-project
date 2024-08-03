package com.enuygun.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;


    public static String getProperty(String propertyName) {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(propertyName);
    }

    private static void loadProperties() {
        properties = new Properties();

        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new IOException("Properties file '" + "application.properties" + "' not found in the application properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

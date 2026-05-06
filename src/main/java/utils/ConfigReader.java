package utils;

import io.qameta.allure.Step;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    public static String getProperty(String key) {
        if (prop == null) {
            try {
                prop = new Properties();
                FileInputStream fis =
                        new FileInputStream("src/test/resources/config.properties");
                prop.load(fis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return prop.getProperty(key);
    }
}
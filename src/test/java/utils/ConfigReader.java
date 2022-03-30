package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {
    private ConfigReader() {}

    public static Properties readConfig(String propertiesFileName) {
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(propertiesFileName)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;

        } catch (IOException e) {
            throw new RuntimeException("Unable to read '" + propertiesFileName + "' file", e);
        }
    }
}
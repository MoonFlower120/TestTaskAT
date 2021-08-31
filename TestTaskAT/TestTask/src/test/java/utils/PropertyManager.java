package utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    private static final Properties PROPERTY = new Properties();
    private static final String FRAMEWORKPROPERTIESPATH = "/src/main/resources/";
    private static final Logger LOGGER = Logger.getLogger(PropertyManager.class);

    public static Properties loadPropertyFile(String propertyToLoad) {
        try {
            PROPERTY.load(new FileInputStream(System.getProperty("user.dir")
                    + FRAMEWORKPROPERTIESPATH + propertyToLoad));
        } catch (IOException io) {
            LOGGER.info(
                    "IOException in the loadFrameworkPropertyFile() method of the PropertyManager class",
                    io);
            Runtime.getRuntime().halt(0);
        }
        return PROPERTY;
    }
}

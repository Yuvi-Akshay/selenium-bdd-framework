package com.freecrm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties prop;

    public Properties initProp() {

        prop = new Properties();
        File file = new File(
                System.getProperty("user.dir")
                + "/src/test/resources/config/config.properties"
        );

        try {
            FileInputStream fis = new FileInputStream(file);
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }
}

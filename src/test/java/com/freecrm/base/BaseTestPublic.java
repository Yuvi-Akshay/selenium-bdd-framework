package com.freecrm.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.freecrm.utils.ConfigReader;
import com.freecrm.utils.DriverFactory;

public class BaseTestPublic {

    protected WebDriver driver;
    protected Properties prop;
    protected static final Logger log = LogManager.getLogger(BaseTestPublic.class);

    @BeforeMethod
    public void setUp() {
        log.info("===== Test setup started =====");
        ConfigReader configReader = new ConfigReader();
        DriverFactory driverFactory = new DriverFactory();

        prop = configReader.initProp();
        driver = driverFactory.initDriver(prop.getProperty("browser"));

        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();

        log.info("Browser launched and configured successfully");

    }

    @AfterMethod
    public void tearDown() {
        log.info("===== Test execution finished =====");
        driver.quit();
        log.info("Browser closed");
    }
}

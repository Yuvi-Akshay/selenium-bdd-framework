package com.freecrm.hooks;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.freecrm.utils.ConfigReader;
import com.freecrm.utils.DriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    
    private WebDriver driver;
    private Properties prop;

    @Before
    public void setUp(){

        ConfigReader configReader = new ConfigReader();
        DriverFactory driverFactory = new DriverFactory();

        prop = configReader.initProp();
        driver = driverFactory.initDriver(prop.getProperty("browser"));

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(prop.getProperty("url"));
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}

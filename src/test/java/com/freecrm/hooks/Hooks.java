package com.freecrm.hooks;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.freecrm.utils.ConfigReader;
import com.freecrm.utils.DriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    
    private Properties prop;
    private TestContext context;

    public Hooks(TestContext context){
        this.context = context;
    }

    @Before
    public void setUp(){

        ConfigReader configReader = new ConfigReader();
        DriverFactory driverFactory = new DriverFactory();

        prop = configReader.initProp();
        WebDriver driver = driverFactory.initDriver(prop.getProperty("browser"));
        context.setDriver(driver);


        context.getDriver().manage().window().maximize();
        context.getDriver().manage().deleteAllCookies();
        context.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        context.getDriver().get(prop.getProperty("url"));
    }

    @After
    public void tearDown(){
        context.getDriver().quit();
    }
}

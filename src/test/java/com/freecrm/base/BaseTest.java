package com.freecrm.base;

import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.freecrm.pages.HomePage;
import com.freecrm.pages.LandingPage;
import com.freecrm.pages.LoginPage;
import com.freecrm.utils.ConfigReader;
import com.freecrm.utils.DriverFactory;

import io.github.cdimascio.dotenv.Dotenv;

public class BaseTest {

    protected WebDriver driver;
    protected Properties prop;
    protected HomePage homePage;
    protected static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        log.info("===== Test setup started =====");
        ConfigReader configReader = new ConfigReader();
        DriverFactory driverFactory = new DriverFactory();

        prop = configReader.initProp();
        String browser = prop.getProperty("browser");
        log.info("Browser from config: {}", browser);

        driver = driverFactory.initDriver(browser);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(prop.getProperty("url"));

        log.info("Browser launched and configured successfully");

        Dotenv dotenv = Dotenv.configure().directory(System.getProperty("user.dir")).ignoreIfMissing().load();
        String userName = dotenv.get("FREECRM_USERNAME");
        String password = dotenv.get("FREECRM_PASSWORD");
        log.info("Credentials loaded from environment");

        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = landingPage.clickOnLoginBtn();
        homePage = loginPage.login(userName, password);

    }

    @AfterMethod
    public void tearDown() {
        log.info("===== Test execution finished =====");
        driver.quit();
        log.info("Browser closed");
    }

}

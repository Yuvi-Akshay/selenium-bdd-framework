package com.freecrm.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.freecrm.pages.HomePage;
import com.freecrm.pages.LandingPage;
import com.freecrm.pages.LoginPage;
import com.freecrm.utils.DriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.cdimascio.dotenv.Dotenv;

public class LoginSteps {

    WebDriver driver;
    LandingPage landingPage;
    LoginPage loginPage;
    HomePage homePage;

    @Given("user is on landing page")
    public void user_is_on_landing_page() {
        driver = DriverFactory.getDriver();
        landingPage = new LandingPage(driver);
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        loginPage = landingPage.clickOnLoginBtn();
    }

    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {
        Dotenv dotenv = Dotenv.load();
        String userName = dotenv.get("FREECRM_USERNAME");
        String password = dotenv.get("FREECRM_PASSWORD");
        homePage = loginPage.login(userName, password);
    }

    @Then("user should be navigated to home page")
    public void user_should_be_navigated_to_home_page() {
        String homePageTitle = homePage.getHomePageTitle();
        Assert.assertTrue(homePageTitle.contains("Free CRM"), "Home page not loaded");
    }

}

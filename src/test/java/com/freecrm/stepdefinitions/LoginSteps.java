package com.freecrm.stepdefinitions;

import org.testng.Assert;

import com.freecrm.hooks.TestContext;
import com.freecrm.pages.HomePage;
import com.freecrm.pages.LandingPage;
import com.freecrm.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.cdimascio.dotenv.Dotenv;

public class LoginSteps {

    private TestContext context;

    public LoginSteps(TestContext context){
        this.context=context;
    }

    @Given("user is on landing page")
    public void user_is_on_landing_page() {
        LandingPage landingPage = new LandingPage(context.getDriver());
        context.setLandingPage(landingPage);
    }

    @When("user clicks on login button") 
    public void user_clicks_on_login_button() {
        LoginPage loginPage = context.getLandingPage().clickOnLoginBtn();
        context.setLoginPage(loginPage);
    }

    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {
        String userName = context.getDotenv().get("FREECRM_USERNAME");
        String password = context.getDotenv().get("FREECRM_PASSWORD");
        HomePage homePage = context.getLoginPage().login(userName, password);
        context.setHomePage(homePage);
    }

    @Then("user should be navigated to home page")
    public void user_should_be_navigated_to_home_page() {
        String homePageTitle = context.getHomePage().getHomePageTitle();
        Assert.assertTrue(homePageTitle.contains("Free CRM"), "Home page not loaded");
    }

}

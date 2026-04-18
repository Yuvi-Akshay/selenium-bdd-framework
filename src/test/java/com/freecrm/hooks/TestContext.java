package com.freecrm.hooks;

import org.openqa.selenium.WebDriver;

import com.freecrm.pages.HomePage;
import com.freecrm.pages.LandingPage;
import com.freecrm.pages.LoginPage;

public class TestContext {
    private WebDriver driver;
    private LandingPage landingPage;
    private LoginPage loginPage;
    private HomePage homePage;

    public WebDriver getDriver(){
        return this.driver;
    }
    public void setDriver(WebDriver driver){
        this.driver= driver;
    }

    public void setLandingPage(LandingPage landingPage){
        this.landingPage= landingPage;

    }
    public LandingPage getLandingPage(){
        return this.landingPage;
    }

    public void setLoginPage(LoginPage loginPage){
        this.loginPage= loginPage;
    }
    public LoginPage getLoginPage(){
        return this.loginPage;
    }

    public void setHomePage(HomePage homePage){
        this.homePage= homePage;
    }
    public HomePage getHomePage(){
        return this.homePage;
    }
}

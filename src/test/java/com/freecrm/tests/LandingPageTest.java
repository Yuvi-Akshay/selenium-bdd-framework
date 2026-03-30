package com.freecrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.freecrm.base.BaseTestPublic;
import com.freecrm.pages.HomePage;
import com.freecrm.pages.LandingPage;
import com.freecrm.pages.LoginPage;

import io.github.cdimascio.dotenv.Dotenv;


public class LandingPageTest extends BaseTestPublic {
    @Test
    public void verifyHomePageTitle() {
        LandingPage landingPage = new LandingPage(driver);
        String loginPageTitle = landingPage.getLandingPageTitle();
        System.out.println("Login Page Title: "+loginPageTitle);

    }

    @Test
    public void loginTest(){
        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = landingPage.clickOnLoginBtn();
        Dotenv dotenv = Dotenv.load();
        String userName = dotenv.get("FREECRM_USERNAME");
        String password = dotenv.get("FREECRM_PASSWORD");
        HomePage homePage = loginPage.login(userName, password);
        String title = homePage.getHomePageTitle();
        Assert.assertTrue(title.contains("Free CRM"), "Home page title is incorrect");
    }

    @Test
    public void verifyingpageHeader(){
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageHeaderVisible(), "page not loaded");
    }
}

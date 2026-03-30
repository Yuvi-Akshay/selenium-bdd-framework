package com.freecrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.freecrm.base.BasePage;

public class LoginPage extends BasePage {

    // Locators
    @FindBy(xpath = "//input[@name='email']")
    private WebElement userName;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//div[text()='Login']")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginBtnVisible() {
        return loginBtn.isEnabled();
    }

    public HomePage login(String uName, String pwd) {
        userName.sendKeys(uName);
        password.sendKeys(pwd);
        loginBtn.click();
        return new HomePage(driver);
    }

    public String loginPageTitle() {
        return driver.getTitle();
    }

}

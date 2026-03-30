package com.freecrm.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.freecrm.base.BasePage;
import com.freecrm.utils.WaitUtils;

public class LandingPage extends BasePage {


    // locators
    // private By loginBtn = By.xpath("//span[text()='Log In']");
    @FindBy(xpath = "//*[text()='Log In']")
    private WebElement loginBtn;

    @FindBy(xpath = "//a[@title='free crm home']//span[text()='free']")
    private WebElement pageHeader;
    private static final Logger log = LogManager.getLogger(LandingPage.class);

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public String getLandingPageTitle() {
        return driver.getTitle();
    }

    public LoginPage clickOnLoginBtn() {
        log.info("Clicking on Login button");
        WaitUtils waitUtils = new WaitUtils(driver);
        waitUtils.waitForElementClickability(loginBtn);
        loginBtn.click();
        return new LoginPage(driver);
    }

    public boolean isPageHeaderVisible() {
        WaitUtils waitUtils = new WaitUtils(driver);
        waitUtils.waitForElementVisibility(pageHeader);
        return pageHeader.isDisplayed();
    }
}

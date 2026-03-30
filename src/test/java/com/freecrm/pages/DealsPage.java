package com.freecrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.freecrm.base.BasePage;
import com.freecrm.utils.WaitUtils;

public class DealsPage extends BasePage {

    // locators
    @FindBy(xpath = "//*[@class='selectable ']")
    private WebElement dealsHeadingLaber;

    public DealsPage(WebDriver driver) {
        super(driver);
    }

    public String getDealsPageTitle() {
        return driver.getTitle();
    }

    public String getDealsPageHeader() {
        WaitUtils waitUtils = new WaitUtils(driver);
        waitUtils.waitForElementVisibility(dealsHeadingLaber);
        return dealsHeadingLaber.getText();
    }

}

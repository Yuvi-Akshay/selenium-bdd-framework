package com.freecrm.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.freecrm.base.BasePage;
import com.freecrm.utils.WaitUtils;

public class ContactsPage extends BasePage {

    // locators
    @FindBy(xpath = "//*[@class='selectable ']")
    private WebElement contactHeadingLaber;

    @FindBy(xpath = "//input[@name='first_name']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@name='last_name']")
    private WebElement lastName;

    @FindBy(xpath = "//*[@placeholder='Email address']")
    private WebElement emailID;

    @FindBy(xpath = "//*[@class='ui linkedin button']")
    private WebElement saveBtn;

    @FindBy(xpath = "//*[@name='category' and @role='listbox'] ")
    private WebElement catagoryDrpDown;

    @FindBy(xpath = "//*[@name='status' and @role='listbox'] ")
    private WebElement statusDrpDown;

    @FindBy(xpath = "//*[text()='Invoices' and @class='item'] ")
    private WebElement invoicesTab;

    @FindBy(xpath = "//button[text()='Delete']")
    private WebElement popUpDeleteBtnl;

    @FindBy(xpath = "//*[@class='ui linkedin button' and text()='Create']")
    private WebElement createNewContactBtn;

    private static final Logger log = LogManager.getLogger(ContactsPage.class);

    public ContactsPage(WebDriver driver) {
        super(driver);
    }

    public String getContactsPageTitle() {
        return driver.getTitle();
    }

    public String getContactPageHeader() {
        WaitUtils waitUtils = new WaitUtils(driver);
        waitUtils.waitForElementVisibility(contactHeadingLaber);
        return contactHeadingLaber.getText();
    }

    public void selectContactByName(String name) {
        log.info("Selecting the contact: {}", name);
        WebElement checkBox = driver
                .findElement(By.xpath("//tr[.//a[text()='" + name + "']]//div[@class='ui fitted checkbox']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkBox);
    }

    public boolean isContactSelected(String name) {
        By checkBox = By.xpath("//tr[.//a[text()='" + name + "']]//input[@type='checkbox']");
        return driver.findElement(checkBox).isSelected();
    }

    public void addNewContact(String fName, String lName, String emailId, String catag, String stat) {
        log.info("Adding a new contact");
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        emailID.sendKeys(emailId);

        catagoryDrpDown.click();
        WebElement catDrpDownValue = driver
                .findElement(By.xpath("//*[@name='category' and @role='option' and ./span[text()='" + catag + "']]"));
        catDrpDownValue.click();

        statusDrpDown.click();
        WebElement staDrpDownValue = driver
                .findElement(By.xpath("//*[@name='status' and @role='option' and ./span[text()='" + stat + "']]"));
        staDrpDownValue.click();

        saveBtn.click();

        WaitUtils waitUtils = new WaitUtils(driver);
        waitUtils.waitForElementClickability(invoicesTab);
        invoicesTab.click();
    }

    public void deleteContact(String name) {
        log.info("Deleting the contact with Name: {}", name);
        driver.findElement(By.xpath("//button[@class='ui button icon' and ./ ancestor::tr//a[text()='" + name + "']]"))
                .click();
        WaitUtils waitUtils = new WaitUtils(driver);
        waitUtils.waitForElementClickability(popUpDeleteBtnl);
        popUpDeleteBtnl.click();

    }

    public boolean isContactPresent(String name) {
        return driver.findElements(By.xpath("//a[text()='" + name + "']")).size() > 0;
    }

    public void clickOnCreateNewContact() {
        log.info("Clickin on new contact Button");
        createNewContactBtn.click();
    }

}

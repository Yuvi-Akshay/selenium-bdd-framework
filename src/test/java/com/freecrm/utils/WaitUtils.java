package com.freecrm.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
    
    private WebDriverWait wait;

    public WaitUtils(WebDriver driver){
        this.wait= new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    public void waitForElementVisibility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementClickability(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}

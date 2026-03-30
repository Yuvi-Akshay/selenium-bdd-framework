package com.freecrm.utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String takeScreenShot(WebDriver driver, String testName) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss"));
        File screenshotDir = new File(System.getProperty("user.dir") + "/test-output/screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        String screenshotPath = System.getProperty("user.dir")+ "/test-output/screenshots/" + testName + "_" + timestamp + ".png";
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(screenshotPath);
            FileUtils.copyFile(scrFile, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "screenshots/" + testName + "_" + timestamp + ".png";
    }

}
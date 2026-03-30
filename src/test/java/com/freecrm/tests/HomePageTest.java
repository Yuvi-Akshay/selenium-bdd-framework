package com.freecrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.freecrm.base.BaseTest;

public class HomePageTest extends BaseTest {

    @Test
    public void VerifyUserTest() {
        String dispName = homePage.getDisplayName();
        Assert.assertTrue(dispName.contains("Yuvi Akshay"), "User Names does not match");
    }

//     @Test
//     public void navigateToContactTabTest(){
//     ContactsPage contactsPage = homePage.goToContacts();
//     }
}

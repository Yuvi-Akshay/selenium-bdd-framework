package com.freecrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.freecrm.base.BaseTest;
import com.freecrm.pages.DealsPage;

public class DealsPageTest extends BaseTest {

    @Test
    public void verifyDealsPageTitle() {
        DealsPage dealsPage = homePage.goToDeals();
        String title = dealsPage.getDealsPageTitle();
        Assert.assertTrue(title.contains("Free CRM"), "Deals page title Doese not match");
    }

    @Test
    public void verifyDealsPageHeader() {
        DealsPage dealsPage = homePage.goToDeals();
        String header = dealsPage.getDealsPageHeader();
        Assert.assertTrue(header.contains("Deals"), "Deals page header Doese not match");
    }

}

package com.freecrm.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.freecrm.base.BaseTest;
import com.freecrm.pages.ContactsPage;
import com.freecrm.utils.TestUtil;

public class ContactsPageTest extends BaseTest {
    @Test
    public void verifyContactPageTitle() {
        ContactsPage contactsPage = homePage.goToContacts();
        String pageHeader = contactsPage.getContactPageHeader();
        Assert.assertTrue(pageHeader.contains("Contacts"), "Contacts page title is incorrect");

    }

    @Test
    public void selectContactsByName() {
        ContactsPage contactsPage = homePage.goToContacts();
        contactsPage.selectContactByName("Bruce Wayne");
        Boolean isSelected = contactsPage.isContactSelected("Bruce Wayne");
        Assert.assertTrue(isSelected,"Bruce Wayne is not selected");
    }

    @Test
    public void selectMultipleContactsByName() {
        ContactsPage contactsPage = homePage.goToContacts();
        contactsPage.selectContactByName("Bruce Wayne");
        contactsPage.selectContactByName("Tony Stark");
        Boolean isSelected1 = contactsPage.isContactSelected("Bruce Wayne");
        Assert.assertTrue(isSelected1,"Bruce Wayne is not selected");

        Boolean isSelected2 = contactsPage.isContactSelected("Tony Stark");
        Assert.assertTrue(isSelected2,"Tony Stark is not selected");
    }

    @DataProvider
    public Object[][] getCrmTestData() {
        Object data[][] = TestUtil.getTestData("one");
        return data;
    }

    @Test(dataProvider = "getCrmTestData",priority=1)
    public void addNewContact(String firstName, String lastName, String emailId, String catag, String stat) {
        ContactsPage contactsPage = homePage.clickOnAddNewContact();
        contactsPage.addNewContact(firstName, lastName, emailId, catag, stat);
    }

    @Test(priority=2)
    public void deleteContactByName(){
        ContactsPage contactsPage = homePage.goToContacts();
        contactsPage.deleteContact("will smith");
        Boolean isContactDeleted= contactsPage.isContactPresent("will smith");
        Assert.assertFalse(isContactDeleted,"will smith not deleted");
    }

    @Test (dataProvider = "getCrmTestData",enabled = false)
    public void createNewContact(String firstName, String lastName, String emailId, String catag, String stat){
        ContactsPage contactsPage = homePage.goToContacts();
        contactsPage.clickOnCreateNewContact();
        contactsPage.addNewContact(firstName, lastName, emailId, catag, stat);
    }

}

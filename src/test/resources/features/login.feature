Feature: Login Feature

Scenario: Successful login 

Given user is on landing page
When user clicks on login button
And user enters valid credentials
Then user should be navigated to home page
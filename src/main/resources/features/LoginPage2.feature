Feature: Login page feature for Salesforce

@smoke
Scenario: Login page title
Given user is on login page
When user gets the title of the page
Then page title should be "Free CRM - CRM software for customer relationship management, sales, and support."

Scenario: verify the user navigating to all the pages
Given user is on login page
And click on contact menu and page title should be "Contact us"
And click on Features menu and page title should be "The Professional CRM Solution"

Scenario: verify the user navigating to all the pages
Given user is on login page
And click on contact menu and page title should be "Contact us"
And click on Features menu and page title should be "The Professional CRM Solution"

Scenario: verify the user navigating to all the pages
Given user is on login page
And click on contact menu and page title should be "Contact us"
And click on Features menu and page title should be "The Professional CRM Solution"
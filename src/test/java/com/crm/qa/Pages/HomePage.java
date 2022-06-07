package com.crm.qa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.BaseClass.TestBase;

public class HomePage extends TestBase {
	@FindBy(xpath = "//td[contains(text(),'User: Pavan Reddy')]")
	@CacheLookup
	WebElement userNameLabel;

	@FindBy(xpath = "//a[contains(text(),'Contact1')]")
	WebElement contactsLink;

	// @FindBy(xpath = "//a[contains(text(),'Contact')]")
	//By contactsLink = By.xpath("//a[contains(text(),'Contact')]");
	// WebElement contactsLink;

	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement dealsLink;

	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	WebElement tasksLink;

	@FindBy(xpath = "//a[contains(text(),'New Contact')]")
	WebElement newContactsLink;

	@FindBy(xpath = "//a[contains(text(),'Features')]")
	WebElement featuresLink;

	@FindBy(xpath = "//h1[@class='text-center']")
	WebElement title;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyCorrectUserName() {
		return userNameLabel.isDisplayed();
	}

	public String verifyHomePageTitle() {
		return driver.getTitle();
	}

	public String PageText() {
		return title.getText();
	}

	public ContactsPage clickOnContactsLink()

	{
//		waitFor(contactsLink, 30);
//		driver.findElement(contactsLink).click();
//		sendKeys(contactsLink, "testing");
		contactsLink.click();
		sendKeys(contactsLink, "");
		return new ContactsPage();
	}

	public DealsPage clickOnDealsLink() {
		dealsLink.click();
		return new DealsPage();
	}

	public void featuresclick() {
		featuresLink.click();
	}

	public void contactsclick() {
		contactsLink.click();
	}

	public TasksPage clickOnTasksLink() {
		tasksLink.click();
		return new TasksPage();
	}

	public void clickOnNewContactLink() {
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		newContactsLink.click();
	}

}

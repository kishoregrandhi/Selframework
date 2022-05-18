package stepdefinitions;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import com.crm.qa.BaseClass.TestBase;
import com.crm.qa.Pages.ContactsPage;
import com.crm.qa.Pages.DealsPage;
import com.crm.qa.Pages.HomePage;
import com.crm.qa.Pages.LoginPage;
import com.crm.qa.Utilities.TestUtility;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageTestVerification extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	TestUtility testUtil;
	ContactsPage contactsPage;
	DealsPage dealsPage;
	String homePageTitle;
	
	//@Before()
	public void setUp()
	{
		//initialization();
		Log.info("Application Launched Successfully");
		
		testUtil = new TestUtility();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		dealsPage = new DealsPage();
		homePage = loginPage.login(property.getProperty("Username"),property.getProperty("Password"));
	}
	

	@Given("user is on login page")
	public void user_is_on_login_page() {
		setUp();
		
	}
	@When("user gets the title of the page")
	public void user_gets_the_title_of_the_page() {
		homePageTitle = homePage.verifyHomePageTitle();
		System.out.println(homePageTitle);
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String title) throws IOException {
		//Assert.assertEquals(homePageTitle, Constants.HOME_PAGE_TITLE, "Home Page Title is not Matched");
		if (homePageTitle.equalsIgnoreCase(title))
		{
			System.out.println("title was matched");
			Log.info("Home Page Title Verified");
		} else {
               Assert.fail("page title was not matched");
		}
		
		}
	
	@Given("click on contact menu and page title should be {string}")
	public void click_on_contact_menu_and_page_title_should_be(String contact) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		//driver.findElement(By.linkText("Contact")).click();
		homePage.contactsclick();
		Thread.sleep(2000);
		String contactttile= homePage.PageText();
		if (contactttile.equalsIgnoreCase(contact)) {
			System.out.println("Contact Us Page is Opened");
		} else {
			Assert.fail("Contact Us Page is not opened");
		}
	}

	@Given("click on Features menu and page title should be {string}")
	public void click_on_features_menu_and_page_title_should_be(String features) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		homePage.featuresclick();
		//driver.findElement(By.linkText("Features")).click();
		Thread.sleep(2000);
		String featuresttile= homePage.PageText();
		if (featuresttile.equalsIgnoreCase(features)) {
			System.out.println("Features Page is Opened");
		} else {
			Assert.fail("Features is not opened");
		}
	}
	
	
	
}

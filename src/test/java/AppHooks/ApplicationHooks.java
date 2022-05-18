package AppHooks;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.BaseClass.TestBase;
import com.crm.qa.Constants.Constants;
import com.crm.qa.Utilities.WebEventListener;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ApplicationHooks extends TestBase {

	

	@Before(order = 0)
	public  void lauchbrowser()
	{
		//String broswerName = property.getProperty("Browser");
		
		//String broswerName = System.getProperty("Browser");
		String broswerName = "Chrome";
		if(broswerName.equals("Chrome"))
		{
			chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("useAutomationExtension", false);
			chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			//System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
			WebDriverManager.chromedriver().capabilities(chromeOptions).setup();
			driver = new ChromeDriver(chromeOptions);
		}
		else if(broswerName.equals("IE"))
		{
			System.setProperty("webdriver.ie.driver", Constants.INTERNET_EXPLORER_DRIVER_PATH);
			driver = new InternetExplorerDriver();
		}
		else if(broswerName.equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER_PATH);
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("Path of Driver Executable is not Set for any Browser");
		}
		
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(property.getProperty("Url"));
	}
	


	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);

		}
	}

}

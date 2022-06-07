package com.crm.qa.BaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.crm.qa.Constants.Constants;
import com.crm.qa.Utilities.TestUtility;
import com.crm.qa.Utilities.WebEventListener;
import com.epam.healenium.SelfHealingDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public static WebDriver delegate;
	public static Properties property;
	public static ChromeOptions chromeOptions;

	public static WebEventListener eventListener;
	public static Logger Log;

	public TestBase() {
		Log = Logger.getLogger(this.getClass());
		try {
			property = new Properties();
			FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir")
					+ "/src/main/java/com/crm/qa/Configuration/Configuration.properties");
			property.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void setLog4j() {
		TestUtility.setDateForLog4j();
	}

	public static void initialization() {
		// String broswerName = property.getProperty("Browser");

		// String broswerName = System.getProperty("Browser");
		String broswerName = "Chrome";
		if (broswerName.equals("Chrome")) {
			chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("useAutomationExtension", false);
			chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			// System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
			WebDriverManager.chromedriver().capabilities(chromeOptions).setup();
			delegate = new ChromeDriver(chromeOptions);
			driver= SelfHealingDriver.create(delegate);
			
		} else if (broswerName.equals("IE")) {
			System.setProperty("webdriver.ie.driver", Constants.INTERNET_EXPLORER_DRIVER_PATH);
			driver = new InternetExplorerDriver();
		} else if (broswerName.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER_PATH);
			driver = new FirefoxDriver();
		} else {
			System.out.println("Path of Driver Executable is not Set for any Browser");
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constants.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
		driver.get(property.getProperty("Url"));
	}

	public void implicitWait(long... Seconds) {
		long Secs = Seconds.length > 0 ? Seconds[0] : 30;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Secs));
	}

	public void waitFor(WebElement element, long... Seconds) {
		long Secs = Seconds.length > 0 ? Seconds[0] : 30;
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(Secs));
		webDriverWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	

	public void sendKeys(WebElement ele, String text) {
		//WebElement ele = driver.findElement(contactsLink)
		//driver.findElement(By.xpath(ele)).sendKeys(text);
		ele.sendKeys(text);
	}

	public void click(WebElement element) {
		element.click();
	}

	public String getText(WebElement element) {
		return element.getText();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void selectByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public void selectByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public void selectByVisibleText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public boolean isSelected(WebElement element) {
		return element.isSelected();
	}

	public boolean isEnabled(WebElement element) {
		return element.isEnabled();
	}

	public boolean isDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	public String getAttribute(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}

	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	public void dismissAlert() {
		driver.switchTo().alert().dismiss();
	}

	public String getAlertText() {
		return driver.switchTo().alert().getText();
	}

	public List<WebElement> getElements(List<WebElement> element) {
		return element;
	}

	public void mouseOver(WebElement element, int... offset) {
		int x = offset.length > 0 ? offset[0] : 0;
		int y = offset.length > 0 ? offset[1] : 0;
		Actions actions = new Actions(driver);
		actions.moveToElement(element, x, y).build().perform();
	}

	public void rightClick(WebElement element, int... offset) {
		int x = offset.length > 0 ? offset[0] : 0;
		int y = offset.length > 0 ? offset[1] : 0;
		Actions actions = new Actions(driver);
		actions.moveToElement(element, x, y).contextClick().build().perform();
	}

	public void doubleClick(WebElement element, int... offset) {
		int x = offset.length > 0 ? offset[0] : 0;
		int y = offset.length > 0 ? offset[1] : 0;
		Actions actions = new Actions(driver);
		actions.moveToElement(element, x, y).doubleClick().build().perform();
	}

	public void dragAndDrop(WebElement source, WebElement destination) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, destination).build().perform();
	}

	public void sendKeyboardKeys(WebElement element, CharSequence keys, int... offset) {
		int x = offset.length > 0 ? offset[0] : 0;
		int y = offset.length > 0 ? offset[1] : 0;
		Actions actions = new Actions(driver);
		actions.moveToElement(element, x, y).sendKeys(keys).build().perform();
	}

	public void sendKeyboardSpecialKeys(WebElement element, Keys keys, int... offset) {
		int x = offset.length > 0 ? offset[0] : 0;
		int y = offset.length > 0 ? offset[1] : 0;
		Actions actions = new Actions(driver);
		actions.moveToElement(element, x, y).sendKeys(keys).build().perform();
	}

	public void switchToWindow(int index) {
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> i = handles.iterator();
		ArrayList<String> arr = new ArrayList<String>();
		while (i.hasNext()) {
			arr.add(i.next());
		}
		driver.switchTo().window(arr.get(index));
	}

	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}

	public void switchToDefault() {
		driver.switchTo().defaultContent();
	}

	public void OpenInNewTab(WebElement element) {
		element.sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
	}

	@AfterTest
	public void endReport() {

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws IOException {
		driver.quit();
		Log.info("Browser Terminated");
		Log.info("-----------------------------------------------");
	}
}

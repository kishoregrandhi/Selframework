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

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
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
			driver = new ChromeDriver(chromeOptions);
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

	public void waitFor(By by, long... Seconds) {
		long Secs = Seconds.length > 0 ? Seconds[0] : 30;
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(Secs));
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	

	public void sendKeys(By by, String text) {
		driver.findElement(by).sendKeys(text);
	}

	public void click(By by) {
		driver.findElement(by).click();
	}

	public String getText(By by) {
		return driver.findElement(by).getText();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void selectByIndex(By by, int index) {
		Select select = new Select(driver.findElement(by));
		select.selectByIndex(index);
	}

	public void selectByValue(By by, String value) {
		Select select = new Select(driver.findElement(by));
		select.selectByValue(value);
	}

	public void selectByVisibleText(By by, String text) {
		Select select = new Select(driver.findElement(by));
		select.selectByVisibleText(text);
	}

	public boolean isSelected(By by) {
		return driver.findElement(by).isSelected();
	}

	public boolean isEnabled(By by) {
		return driver.findElement(by).isEnabled();
	}

	public boolean isDisplayed(By by) {
		return driver.findElement(by).isDisplayed();
	}

	public String getAttribute(By by, String attribute) {
		return driver.findElement(by).getAttribute(attribute);
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

	public List<WebElement> getElements(By by) {
		return driver.findElements(by);
	}

	public void mouseOver(By by, int... offset) {
		int x = offset.length > 0 ? offset[0] : 0;
		int y = offset.length > 0 ? offset[1] : 0;
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(by), x, y).build().perform();
	}

	public void rightClick(By by, int... offset) {
		int x = offset.length > 0 ? offset[0] : 0;
		int y = offset.length > 0 ? offset[1] : 0;
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(by), x, y).contextClick().build().perform();
	}

	public void doubleClick(By by, int... offset) {
		int x = offset.length > 0 ? offset[0] : 0;
		int y = offset.length > 0 ? offset[1] : 0;
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(by), x, y).doubleClick().build().perform();
	}

	public void dragAndDrop(By source, By destination) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(driver.findElement(source), driver.findElement(destination)).build().perform();
	}

	public void sendKeyboardKeys(By by, CharSequence keys, int... offset) {
		int x = offset.length > 0 ? offset[0] : 0;
		int y = offset.length > 0 ? offset[1] : 0;
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(by), x, y).sendKeys(keys).build().perform();
	}

	public void sendKeyboardSpecialKeys(By by, Keys keys, int... offset) {
		int x = offset.length > 0 ? offset[0] : 0;
		int y = offset.length > 0 ? offset[1] : 0;
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(by), x, y).sendKeys(keys).build().perform();
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

	public void switchToFrame(By by) {
		WebElement element = driver.findElement(by);
		driver.switchTo().frame(element);
	}

	public void switchToDefault(By by) {
		driver.switchTo().defaultContent();
	}

	public void OpenInNewTab(By by) {
		driver.findElement(by).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
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

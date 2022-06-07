package AppHooks;

import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.crm.qa.BaseClass.TestBase;
import com.epam.healenium.SelfHealingDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test1 extends TestBase {

	public static WebDriver delegate;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("useAutomationExtension", false);
		chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		//System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
		WebDriverManager.chromedriver().capabilities(chromeOptions).setup();
		delegate = new ChromeDriver(chromeOptions);
		driver= SelfHealingDriver.create(delegate);
		driver.get(System.getProperty("user.dir") + "/src/main/resources/sample html page/sample1.html");
		
		String text= driver.findElement(By.xpath("//h1[contains(text(),'Minime vero, inquit ille, consentit.')]")).getText();
		
		System.out.println(text);

	}

}

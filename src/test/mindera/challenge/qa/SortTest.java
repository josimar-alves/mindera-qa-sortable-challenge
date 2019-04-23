package test.mindera.challenge.qa;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SortTest {
	
	WebDriver driver;
	Capabilities chromeCapabilities = DesiredCapabilities.chrome();

	@BeforeMethod
	public void setupBeforeTest() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "");
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeCapabilities);
	}

	@AfterMethod
	public void setupAfterTest() {
		 driver.quit();
	}
	
	@Test
	public void sort () {
		driver.get("http://node:3000/");
		
		// Take a picture before
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("screenshot-result\\" + timestamp.getTime() + "0-before.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Sort
		List <WebElement> itens = driver.findElement(By.id("app")).findElements(By.tagName("li"));
		for (int i = 0; i < itens.size(); i++) {
			for (WebElement element : itens) {
				if (element.getText().equals("Item " + i)) {
					(new Actions(driver)).dragAndDrop(element, itens.get(i)).perform();
					break;
				}
			}
		}
		
		// Take a picture after
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("screenshot-result\\" + timestamp.getTime() + "1-after.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

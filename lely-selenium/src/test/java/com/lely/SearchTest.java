package com.lely;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest {

	@Test
	public void Navigate() {

		System.out.println("Starting navigateTest");

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		// sleep for 3 seconds
		sleep(3000);

		driver.manage().window().maximize();

		String url = "https://www.lely.com/en";

		driver.get(url);

		WebElement cookie = driver.findElement(By.id("cookienotice-button-accept"));
		cookie.click();

		System.out.println("Page is opened.");

		// Close browser
		driver.quit();

	}

	@Test

	public void OpenSearchArea() {

		System.out.println("Starting Open Search Area Test");

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		// sleep for 3 seconds
		sleep(3000);

		driver.manage().window().maximize();

		String url = "https://www.lely.com/en";

		driver.get(url);

		WebElement cookie = driver.findElement(By.id("cookienotice-button-accept"));
		cookie.click();


		WebElement search = driver.findElement(By.xpath("//*[@id=\"site-header\"]/div/div[2]/div/div"));
		search.click();

		sleep(2000);

		// verify search area appeared.
		WebElement globalSearchText = driver.findElement(By.id("global-search"));
		Assert.assertTrue(globalSearchText.isDisplayed(), "Global Search text area is not visible");

		// Close browser
		driver.quit();

	}

	@Test

	public void MakeKeywordSearch() {

		System.out.println("Make Keyword Search Test");

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		// sleep for 3 seconds
		sleep(3000);

		driver.manage().window().maximize();

		String url = "https://www.lely.com/en";

		driver.get(url);

		WebElement cookie = driver.findElement(By.id("cookienotice-button-accept"));
		cookie.click();

		// sleep for 2 seconds
		sleep(2000);

		WebElement search = driver.findElement(By.xpath("//*[@id=\"site-header\"]/div/div[2]/div/div"));
		search.click();
		sleep(3000);

		// verify search area appeared.

		WebElement globalSearchText = driver.findElement(By.id("global-search"));
		globalSearchText.sendKeys("happy");

		// sleep 2 seconds
		sleep(2000);

		WebElement submitButton = driver.findElement(By.xpath(
				"/html//form[@id='search']/div[@class='menu-level-2-container']//button[@class='button button-tertiary']"));

		submitButton.click();

		sleep(2000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)", "");

		// Close browser
		driver.quit();

	}

	@Test

	public void VerifySearchResults() {

		System.out.println("Verify Search Result Test");

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		// sleep for 3 seconds
		sleep(3000);

		driver.manage().window().maximize();

		String url = "https://www.lely.com/en";

		driver.get(url);

		WebElement cookie = driver.findElement(By.id("cookienotice-button-accept"));
		cookie.click();

		// sleep for 2 seconds
		sleep(2000);

		WebElement search = driver.findElement(By.xpath("//*[@id=\"site-header\"]/div/div[2]/div/div"));
		search.click();
		sleep(3000);

		// verify search area appeared.

		WebElement globalSearchText = driver.findElement(By.id("global-search"));
		globalSearchText.sendKeys("happy");

		// sleep 2 seconds
		sleep(2000);

		WebElement submitButton = driver.findElement(By.xpath(
				"/html//form[@id='search']/div[@class='menu-level-2-container']//button[@class='button button-tertiary']"));

		submitButton.click();
		
        //sleep 6 seconds for getting all items in the list
		sleep(6000);

		List<WebElement> itemsList = driver.findElements(By.xpath("/html/body/main/div[2]/div/section/ul/li"));
		
		
		for (int i = 0; i < itemsList.size(); i++) {
	        Assert.assertTrue(itemsList.get(i).getText().contains("happy"), "Search result validation failed at instance.");
	      
	    }
		 
		// Close browser
		driver.quit();

	}

	private void sleep(long m) {

		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

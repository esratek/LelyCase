package com.lely;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class CatalogDetailTest {

	@Test
	public void Navigate() {

		System.out.println("Starting navigateTest");

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		// sleep for 3 seconds
		sleep(3000);

		driver.manage().window().maximize();

		String url = "https://www.lely.com/techdocs/";

		driver.get(url);

		WebElement cookie = driver.findElement(By.id("cookienotice-button-accept"));
		cookie.click();

		System.out.println("Page is opened.");

		// Close browser
		driver.quit();

	}

	@Test

	public void DisplayCatalogs() {

		System.out.println("Display Catalog Test");

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		// sleep for 3 seconds
		sleep(3000);

		driver.manage().window().maximize();

		String url = "https://www.lely.com/techdocs/luna/eur/";

		driver.get(url);

		WebElement cookie = driver.findElement(By.id("cookienotice-button-accept"));
		cookie.click();

		List<WebElement> cataloglist = driver.findElements(By.xpath("//*[@id='items-list']/div/section/ul/li"));

		for (WebElement webElement : cataloglist) {

			Assert.assertEquals(webElement.findElement(By.tagName("h3")).getText().trim(), "LUNA EUR (Multi Language)");

		}

		// Close browser
		driver.quit();

	}

	@Test

	public void DownloadCatalog() {

		System.out.println("Display Catalog Test");

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

		String downloadFilepath = "C:\\DownloadTest";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		WebDriver driver = new ChromeDriver(options);

		// sleep for 3 seconds
		sleep(3000);

		driver.manage().window().maximize();

		String url = "https://www.lely.com/techdocs/luna/eur/";

		driver.get(url);

		WebElement cookie = driver.findElement(By.id("cookienotice-button-accept"));
		cookie.click();

		WebElement catalog = driver
				.findElement(By.xpath("//*[@id=\"items-list\"]/div/section/ul/li[1]/section/footer/p[1]/a"));
		String link = catalog.getAttribute("href");
		System.out.println("link is " + link);
		catalog.click();

		sleep(4000);

		Assert.assertTrue(new File(downloadFilepath + "\\D-S006VT_-.pdf").exists());

	}

	@Test

	public void SelectCatalog() {

		System.out.println("Select catalog test for LUNA EUR");

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		try {

			driver.manage().window().maximize();

			String url = "https://www.lely.com/techdocs/";

			driver.get(url);

			WebElement cookie = driver.findElement(By.id("cookienotice-button-accept"));
			cookie.click();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));

			WebElement selectionPlaceHolder = driver.findElement(
					By.xpath("//span[@id='select2-id_q-container']/span[@class='select2-selection__placeholder']"));

			wait.until(ExpectedConditions.elementToBeClickable(selectionPlaceHolder)).click();
			WebElement textBox = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
			textBox.sendKeys("Luna EUR");

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		driver.close();

		driver.quit();

	}

	public boolean isFileDownloaded(String downloadPath, String fileName) {
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();

		for (int i = 0; i < dirContents.length; i++) {

			System.out.println(dirContents[i].getName());

			if (dirContents[i].getName().equals(fileName)) {

				return true;
			}
		}
		return false;
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

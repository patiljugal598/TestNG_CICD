package com.testComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pageObjects.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver initializeDriver() throws IOException {

		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream(".//src//main//java//com//resources//GlobalData.properties");
		pro.load(fis);

		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: pro.getProperty("browser");

		if (browser.contains("chrome")) {
			ChromeOptions option = new ChromeOptions();

			if (browser.contains("headless")) {
				option.addArguments("headless");

			}
			driver = new ChromeDriver(option);

		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApp() throws IOException {
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goTo();
		return loginPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		String jsonString = FileUtils
				.readFileToString(new File(".//src//test//java//com//data//PurchaseOrderData.json"));

		ObjectMapper objectMapper = new ObjectMapper();
		List<HashMap<String, String>> data = objectMapper.readValue(jsonString,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public void takeScreenshot(String methodName, WebDriver driver) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "//ExtentReport//" + methodName + ".png"));

	}

}

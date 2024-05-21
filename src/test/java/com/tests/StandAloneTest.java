package com.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageObjects.CartPage;
import com.pageObjects.CatalogPage;
import com.pageObjects.CheckOutPage;
import com.pageObjects.LoginPage;
import com.testComponent.BaseTest;

public class StandAloneTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	void submitOrder(HashMap<String, String> input) throws IOException {

		CatalogPage catalogPage = loginPage.login(input.get("email"), input.get("password"));

		catalogPage.addProductToCart(input.get("product"));
		boolean match = catalogPage.goToCart().verifyProductOnCart(input.get("product"));

		Assert.assertEquals(match, true);

		CartPage cartPage = new CartPage(driver);
		cartPage.goToCheckOutPage();

		CheckOutPage checkOutPage = new CheckOutPage(driver);
		String message = checkOutPage.selectCountry("India").clickOnCheckoutButton().getConfirmationMessage();

		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrder" }, groups = { "Purchase" })

	void orderHistoryTest() {
		CatalogPage catalogPage = loginPage.login("test_automation@test.com", "Test@123");
		boolean match = catalogPage.goToOrderHistoryPage().verifyProductNamePresent(productName);
		Assert.assertTrue(match);

	}
	/*
	 * Way 1
	 * 
	 * @DataProvider public Object[][] getData() { return new Object[][]
	 * {{"patiljugal598@gmail.com","Test@123","ZARA COAT 3"},{
	 * "test_automation@test.com","Test@123","ADIDAS ORIGINAL"}}; }
	 */

	/* Way 2
	 * @DataProvider public Object[][] getData() {
	 * 
	 * HashMap<String, String> map1 = new HashMap<String, String>();
	 * map1.put("email", "patiljugal598@gmail.com"); map1.put("password",
	 * "Test@123"); map1.put("product", "ZARA COAT 3");
	 * 
	 * HashMap<String, String> map2 = new HashMap<String, String>();
	 * map2.put("email", "test_automation@test.com"); map2.put("password",
	 * "Test@123"); map2.put("product", "ADIDAS ORIGINAL");
	 * 
	 * return new Object[][] {{map1},{map2}}; }
	 */
	
	// Way 3 using Json
	
	@DataProvider
	public Object[][] getData() throws IOException {
	List<HashMap<String, String>> data =	getJsonDataToMap();
	return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
}

package com.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String productName = "ZARA COAT 3";

		LoginPage loginPage = new LoginPage(driver);
		loginPage.goTo();

		CatalogPage catalogPage =loginPage.login("patiljugal598@gmail.com", "Test@123");

		catalogPage.addProductToCart(productName);
		boolean match =catalogPage.goToCart().verifyProductOnCart(productName);

		Assert.assertEquals(match, true);

		CartPage cartPage = new CartPage(driver);
		cartPage.goToCheckOutPage();
		
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		String message = checkOutPage.selectCountry("India").clickOnCheckoutButton().getConfirmationMessage();
		
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.quit();
	}
}

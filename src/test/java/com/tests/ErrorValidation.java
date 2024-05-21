package com.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageObjects.CartPage;
import com.pageObjects.CatalogPage;
import com.pageObjects.CheckOutPage;
import com.pageObjects.LoginPage;
import com.testComponent.BaseTest;
import com.testComponent.Retry;

public class ErrorValidation extends BaseTest{
	
	String productName = "ZARA COAT 3";

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	void errorValidation() {

		loginPage.login("invalid@email.com", "Test123");
		Assert.assertEquals(loginPage.getLoginError(), "incorrect email or password.");	
	}
	
	@Test(groups = {"ErrorHandling"})
	void test() throws IOException{

		CatalogPage catalogPage =loginPage.login("patiljugal598@gmail.com", "Test@123");

		catalogPage.addProductToCart(productName);
		boolean match =catalogPage.goToCart().verifyProductOnCart("ZARA COAT 33");

		Assert.assertEquals(match, false);

	}
	

}

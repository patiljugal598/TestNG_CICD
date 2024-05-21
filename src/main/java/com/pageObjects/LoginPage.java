package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement submitButton;
	
	@FindBy(css ="[class*='flyInOut'] div")
	WebElement error;

	public CatalogPage login(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submitButton.click();

		CatalogPage catalogPage = new CatalogPage(driver);
		return catalogPage;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
	}
	
	public String getLoginError() {
		waitForElementToAppear(error);
		return error.getText().trim();
	}
}

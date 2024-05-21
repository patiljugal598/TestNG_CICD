package com.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.abstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countrySearchBox;
	
	@FindBy(xpath="(//span//i)[2]")
	WebElement selectIndia;
	
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	WebElement checkoutButton;
	
	
	
	By countryOption = By.cssSelector("section .ta-results");
	public CheckOutPage selectCountry(String countryName) {
		countrySearchBox.sendKeys(countryName);
		waitForElementToAppear(countryOption);
		selectIndia.click();
		return this;
	}

	public ConfirmationPage clickOnCheckoutButton() {
		checkoutButton.click();
		return new ConfirmationPage(driver);
	}
}

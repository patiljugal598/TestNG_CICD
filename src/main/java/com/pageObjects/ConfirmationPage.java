package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//td//h1)[1]")
	WebElement confirmationMsg;
	
	public String getConfirmationMessage() {
		return confirmationMsg.getText().trim();
	}
	

}

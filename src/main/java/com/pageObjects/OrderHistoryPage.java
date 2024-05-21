package com.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractComponents.AbstractComponent;

public class OrderHistoryPage extends AbstractComponent {

	WebDriver driver;

	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//tr//td[2]")
	List<WebElement> listOfProducts;

	public boolean verifyProductNamePresent(String productName) {

		boolean match = listOfProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

}

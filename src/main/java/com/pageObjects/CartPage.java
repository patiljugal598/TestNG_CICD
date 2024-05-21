package com.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkOutBtn;
	
	List<WebElement> getCartProducts() {
		return cartProducts;
	}

	public boolean verifyProductOnCart(String productName) {
		return getCartProducts().stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
	}
	
	public CheckOutPage goToCheckOutPage() {
		checkOutBtn.click();
		return new CheckOutPage(driver);
	}

}

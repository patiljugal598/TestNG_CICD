package com.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abstractComponents.AbstractComponent;

public class CatalogPage extends AbstractComponent {

	WebDriver driver;

	CatalogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toast_container = By.cssSelector("#toast-container");
	List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	WebElement getProductByName(String productName) {
		WebElement element = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return element;
	}

	public void addProductToCart(String productName) {
		WebElement product = getProductByName(productName);
		product.findElement(addToCart).click();
		waitForElementToAppear(toast_container);
		waitForElementToDisAppear(toast_container);
	}

}

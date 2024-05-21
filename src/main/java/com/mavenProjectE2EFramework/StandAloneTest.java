package com.mavenProjectE2EFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("patiljugal598@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Test@123");
		driver.findElement(By.cssSelector("#login")).click();

		List<WebElement> productElements = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = productElements.stream().filter(product -> product.findElement(By.cssSelector("b"))
				.getText().equals("ZARA COAT 3"))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@aria-label='Product Added To Cart']")));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase("ZARA COAT 3"));
		System.out.println(match);
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
//		Actions a = new Actions(driver);
//		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India");
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
		WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(15));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section .ta-results")));
		
		driver.findElement(By.xpath("(//span//i)[2]")).click();
		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		
		boolean match1 =driver.findElement(By.xpath("(//td//h1)[1]")).getText().trim().equalsIgnoreCase("THANKYOU FOR THE ORDER.");
		System.out.println(match1);
		driver.quit();
	}
	}



package _10Store.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import _10Store.pageObjects.*;

public class BaseTest {
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*"); 
		options.addArguments("--disable-notifications");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-extensions");
		
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.get("https://7-10.in/");
		
		HomePage home = new HomePage(driver);
		ProductResult result = new ProductResult(driver);
		ProductInfo info = new ProductInfo(driver);
		CartDetails cart = new CartDetails(driver);
		BuyerInfo buy = new BuyerInfo(driver);
		
		
		home.navigate();
		result.filterProduct();
		result.selectProduct();
		info.captureSS();
		info.toCart();
		cart.loadCart();
		cart.allProducts();
		cart.allQty();
		cart.splitProdPrice();
		cart.finalQuote();
		cart.cartCheckout();
		buy.fillDetails();
		buy.payment();
		
	}
}

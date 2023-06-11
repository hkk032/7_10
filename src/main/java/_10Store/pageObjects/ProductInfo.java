package _10Store.pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import _10Store.abstractComponents.*;

public class ProductInfo extends abstractComponents {

	WebDriver driver;
	public ProductInfo(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="#ProductThumbs-7303752646837 div div:nth-child(2)")
	WebElement prodImage;
	
	@FindBy(css="div[data-value='42'] label")
	WebElement prodSize;
	
	@FindBy(css=".sales-point span span:nth-child(2)")
	WebElement prodAvail;
	
	@FindBy(css="button[name='add']")
	WebElement addToCart;
		
	public void captureSS() throws IOException {
		explicitWaitToAppearWebElement(prodImage);
		prodImage.click();
		scrollPage(200);
		fullScreenshot();
		scrollPage(400);
		partialScreenshot();
	}
	
	public void toCart() {
		scrollPage(-600);
		prodSize.click();
		String availability = prodAvail.getText();
		if(availability.contains("In stock") || availability.contains("Low stock")) {
			addToCart.click();
		}
	}
	
}
package _10Store.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import _10Store.abstractComponents.abstractComponents;

public class HomePage extends abstractComponents {
	
	WebDriver driver;
	Actions a;
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		a = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//div[@id='shopify-section-header']//div[@data-section-id='header']//ul//li[2]//a")
	WebElement menNav;
	
	@FindBy(xpath="//div[@id='shopify-section-header']//div[@data-section-id='header']//ul//li[2]//ul")
	WebElement menNavDrop;
	
	@FindBy(xpath="//div[@id='shopify-section-header']//div[@data-section-id='header']//ul//li[2]//ul//li[3]")
	WebElement highTopNav;

	public void navigate() {
		explicitWaitToAppearWebElement(menNav);
		a.moveToElement(menNav).build().perform();
		explicitWaitToAppearWebElement(menNavDrop);
		highTopNav.click();
	}
	
//	calling next page
	public ProductResult product() {
		ProductResult pr = new ProductResult(driver);
		return pr;
	}

}

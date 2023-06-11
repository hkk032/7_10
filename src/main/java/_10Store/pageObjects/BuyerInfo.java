package _10Store.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import _10Store.abstractComponents.*;

public class BuyerInfo extends abstractComponents {
	
	WebDriver driver;
	public BuyerInfo(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="Select0")
	WebElement country;
	
	@FindBy(id="Select1")
	WebElement state;
	
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="TextField1")
	WebElement fName;
	
	@FindBy(id="TextField2")
	WebElement lName;
	
	@FindBy(id="address1")
	WebElement add1;
	
	@FindBy(id="TextField4")
	WebElement add2;
	
	@FindBy(id="TextField5")
	WebElement city;
	
	@FindBy(id="TextField6")
	WebElement pin;
	
	@FindBy(id="TextField7")
	WebElement phone;
	
	@FindBy(css="div[class*='WD4IV'] button[type='submit']")
	WebElement submit;
	
	@FindBy(css="input[id*='e72bca043a496397a6215b1befa69c5a']")
	WebElement cod;
	
	@FindBy(css="div[class*='WD4IV'] button")
	WebElement pay;
	
	public void fillDetails() {
		Select s = new Select(country);
		s.selectByVisibleText("India");
		
		email.sendKeys("hkushwaha345@gmail.com");
		fName.sendKeys("Hemant");
		lName.sendKeys("Kushwaha");
		add1.sendKeys("B2 507, Gagan LaWish, Sr No 12/2");
		add2.sendKeys("Behind Dharmavat Petrol Pump, Pisoli Road");
		
		Select s1 = new Select(state);
		s1.selectByVisibleText("Maharashtra");
		
		city.sendKeys("Pune");
		pin.sendKeys("411060");
		phone.sendKeys("9049746735");
		
		submit.click();
	}
	
	public void payment() {
		explicitWaitToAppearWebElement(cod);
		cod.click();
		pay.click();
	}
	
}


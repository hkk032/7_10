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
	
	public void fillDetails(String ctry, String mail, String fname, String lname, String ad1, String ad2, String stte, String cty, String pincode, String phn) throws InterruptedException {
		Select s = new Select(country);
		s.selectByVisibleText(ctry);
		
		email.sendKeys(mail);
		fName.sendKeys(fname);
		lName.sendKeys(lname);
		add1.sendKeys(ad1);
		add2.sendKeys(ad2);
		
		threadSleep();
		Select s1 = new Select(state);
		s1.selectByValue(stte);
		
		city.sendKeys(cty);
		pin.sendKeys(pincode);
		phone.sendKeys(phn);
		
		submit.click();
	}
	
	public void payment() {
		explicitWaitToAppearWebElement(cod);
		cod.click();
		pay.click();
	}
	
}


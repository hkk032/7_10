package _10Store.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import _10Store.abstractComponents.abstractComponents;

public class UserLogin extends abstractComponents {

	WebDriver driver;
	
	public UserLogin(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="icon-user")
	WebElement userIcon;
	
	@FindBy(id="CustomerEmail")
	WebElement userEmail;
	
	@FindBy(id="CustomerPassword")
	WebElement userPassword;
	
	@FindBy(css="input[value='Sign In']")
	WebElement loginButton;
	
	@FindBy(css="div.errors ul li")
	WebElement errorMsg;
	
	@FindBy(id="customer_logout_link")
	WebElement logoutButton;
	
	public void userLogin(String mail, String pass) {
		userIcon.click();
		explicitWaitToAppearWebElement(loginButton);
		userEmail.sendKeys(mail);
		userPassword.sendKeys(pass);
		loginButton.click();
	}
	
	public String errorMessage() {
		explicitWaitToAppearWebElement(errorMsg);
		return errorMsg.getText();
	}
	
	public void logOut() {
		explicitWaitToAppearWebElement(logoutButton);
		logoutButton.click();
	}
}

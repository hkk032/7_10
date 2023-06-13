package _10Store.testComponents;

import org.testng.annotations.Test;

import _10Store.pageObjects.UserLogin;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ValidatingErrors extends VerifySetup {

	String umail, upass, error;
	
	public void read() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\_10Store\\resources\\GlobalData.properties");
		prop.load(fis);
		umail = prop.getProperty("email");
		upass = prop.getProperty("wrongPassword");
		error = prop.getProperty("errorMessage");
	}
	
	@Test(groups={"ErrorValidations"})
	public void login() throws IOException {	
		read();
		UserLogin login = new UserLogin(driver);
		login.userLogin(umail, upass);
//		Assert.assertEquals(error, login.errorMessage());
	}

}

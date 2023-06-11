package _10Store.testComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import _10Store.pageObjects.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifySetup {
	
	WebDriver driver;
	
	public WebDriver initialise() throws IOException {
//		Properties file can be used using properties class
		Properties prop = new Properties();
//		as the load method takes InputStream as an arg, converting file to inputStream
//		breaking the long path to smaller
//		C:\\Users\\softtech\\Documents\\SeleniumTraining\\SeleniumFrameworkDesign\\src\\main\\java\\esb\\SeleniumFrameworkDesign\\resources\\GlobalData.properties
//		Till project name we have the system path which will be different for all users and
//		if replaced with system path will be helpful
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\_10Store\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*"); 
			options.addArguments("--disable-notifications");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-extensions");
			
			driver = new ChromeDriver(options);
		}
		
		driver.manage().window().maximize();
		
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public void startApplication() throws IOException {
		driver = initialise();
		driver.get("https://7-10.in/");
	}
	
	@Test
	public void startTest() {
		HomePage home = new HomePage(driver);
		home.navigate();
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeApplication() {
		driver.close();
	}
}
package _10Store.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import _10Store.data.DataReader;
import _10Store.pageObjects.BuyerInfo;
import _10Store.pageObjects.CartDetails;
import _10Store.pageObjects.HomePage;
import _10Store.pageObjects.ProductInfo;
import _10Store.pageObjects.ProductResult;
import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifySetup {
	
	public WebDriver driver;
	HomePage home;
	
	public WebDriver initialise() throws IOException {
//		Properties file can be used using properties class
		Properties prop = new Properties();
//		as the load method takes InputStream as an arg, converting file to inputStream
//		breaking the long path to smaller
//		C:\\Users\\softtech\\Documents\\SeleniumTraining\\SeleniumFrameworkDesign\\src\\main\\java\\esb\\SeleniumFrameworkDesign\\resources\\GlobalData.properties
//		Till project name we have the system path which will be different for all users and
//		if replaced with system path will be helpful
		
//		Taking browser name from properties file
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
	
	public String getScreenshot(String tcName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir") + "//reports//" + tcName + ".jpg");
		FileUtils.copyFile(source, destination);
		return System.getProperty("user.dir") + "//reports//" + tcName + ".jpg";
	}
	
	@BeforeTest(alwaysRun=true)
	public void startApplication() throws IOException {
		driver = initialise();
		driver.get("https://7-10.in/");
		home = new HomePage(driver);
	}
	
	@Test
	public void startTest() {
		home.navigate();
	}
	
	@Test(dependsOnMethods= {"startTest"}, dataProvider= "prodData")
	public void buyProduct(HashMap<String, String> name) throws InterruptedException, IOException {
		ProductResult pr = home.product();
		pr.filterProduct();
		pr.selectProduct(name.get("name"));
		
		ProductInfo pi = pr.prodDetails();
		pi.captureSS();
		pi.toCart();
		
		CartDetails cd = pi.cart();
		cd.loadCart();
		cd.cartCheckout();
		
		BuyerInfo bi = cd.custDetails();
		bi.fillDetails();
		bi.payment();
	}
	
	@DataProvider
	public Object[][] prodData() throws IOException {
//		User data reader to get required product name and click on it
		DataReader dr = new DataReader();
		List<HashMap<String, String>> map = dr.getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\_10Store\\data\\productDetails.json");
		return new Object[][] {{map.get(0)}};
	}
	
	@AfterTest(alwaysRun=true)
	public void closeApplication() {
		driver.close();
	}
}
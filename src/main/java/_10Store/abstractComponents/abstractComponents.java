package _10Store.abstractComponents;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class abstractComponents {
	WebDriver driver;
	public abstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	public void explicitWaitToAppear(By findObject) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
////		as we'll send the element through test, so sending the By locator to method
//		wait.until(ExpectedConditions.visibilityOfElementLocated(findObject));
//	}
	
	Date d = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy hh_mm_ss");
	String date = sdf.format(d);
	
	public void explicitWaitToAppearWebElement(WebElement findElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		as we'll send the element through test, so sending the By locator to method
		wait.until(ExpectedConditions.visibilityOf(findElement));
	}
	
	public void threadSleep() throws InterruptedException {
		Thread.sleep(5000);
	}
	
	public void scrollPage(int dim) {
		JavascriptExecutor js  = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, "+dim+")");
	}
	
	public void fullScreenshot() throws IOException {
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile , new File(System.getProperty("user.dir")+"\\screenshots\\"+"product "+date+".png"));
	}
	
	public void partialScreenshot() throws IOException {
		File file = driver.findElement(By.cssSelector(".rte p")).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"\\screenshots\\"+"product info "+date+".png"));
	}
}

package _10Store.abstractComponents;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

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
		FileUtils.copyFile(screenshotFile , new File("product.png"));
	}
	
	public void partialScreenshot() throws IOException {
		File file = driver.findElement(By.cssSelector(".rte p")).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("product info.png"));
	}
}

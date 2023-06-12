package _10Store.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import _10Store.resources.ExtentReporterNG;

public class ReportListeners extends VerifySetup implements ITestListener {
	ExtentTest test;
//	ExtentReporterNG is a common class defined in resources to define all the common lines of code in reports
	ExtentReports extent = ExtentReporterNG.createReport();
	
//	while running tests in parallel we might see mix match of test results, so we use ThreadLocal to avoid that
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
//		result variable holds the info about test which is going to be executed
//		for report name - get the method name that is executing to be test name
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //unique thread ID for every TC
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		String ssPath = null;
		
//		giving life to driver
//		getting driver object from the class
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			ssPath = getScreenshot(result.getMethod().getMethodName(), driver);
		}
		catch(IOException e){
			e.getStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(ssPath, result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	
	@Override
	public void onStart(ITestContext context) {
		
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}

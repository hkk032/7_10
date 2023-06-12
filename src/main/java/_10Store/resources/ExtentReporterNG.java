package _10Store.resources;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import _10Store.abstractComponents.abstractComponents;

public class ExtentReporterNG extends abstractComponents {
	public ExtentReporterNG(WebDriver driver) {
		super(driver);
	}

	public static ExtentReports createReport() {
//		ExtentSparkReporter - responsible for creating report and expects path where report needs to be placed
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy hh_mm_ss");
		String date = sdf.format(d);
		String path = System.getProperty("user.dir")+"\\reports\\Run_"+date+".html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
//		changing report title
		reporter.config().setReportName("Framework Automation Test Result");
		reporter.config().setDocumentTitle("Test Results");
		
//		ExtentReport - responsible to drive all reporting actions
		ExtentReports report = new ExtentReports();
		report.attachReporter(reporter);
//		setting tester name in report
		report.setSystemInfo("Tester", "Hemant");
		
//		returning extentReport object so it can be accessed in listener class
		return report;
	}
}

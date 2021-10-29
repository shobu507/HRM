package com.hrm.reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class CustomExtentReport {

	
	ExtentHtmlReporter extentHtmlReporter;
	ExtentReports extentReports;
	
	public ExtentReports getExtentReports()	{
		
		extentHtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
	        
	        //initialize ExtentReports and attach the HtmlReporter
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentHtmlReporter);
	         
	        //To add system or environment info by using the setSystemInfo method.
		extentReports.setSystemInfo("OS", "Linux");
		extentReports.setSystemInfo("Browser", "Chrome");
	        
	        //configuration items to change the look and feel
	        //add content, manage tests etc
	        extentHtmlReporter.config().setChartVisibilityOnOpen(true);
	        extentHtmlReporter.config().setDocumentTitle("RACPAD-QA");
	        extentHtmlReporter.config().setReportName("SANITY Report");
	        extentHtmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	        extentHtmlReporter.config().setTheme(Theme.DARK);
	        extentHtmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	        
	        return extentReports;
	}
	

		public  String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
				File finalDestination = new File(destination);
				FileUtils.copyFile(source, finalDestination);
				return destination;
		}
}

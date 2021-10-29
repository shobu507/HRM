package com.hrm.login.tests;

import org.testng.annotations.Test;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.hrm.basetest.BaseTest;
import com.hrm.pageoperations.LoginPageOperations;
import com.hrm.reports.CustomExtentReport;
import com.hrm.validations.Validations;

public class LoginTests extends BaseTest {

	LoginPageOperations loginPageOperations;
	Validations validations;
	CustomExtentReport customExtentReport;
	ExtentTest extentTest;
	ExtentReports extentReports;
	Properties properties;
	
	Logger log = Logger.getLogger(LoginTests.class.getName());  
	
	@BeforeTest
	public void setup() {
		
		customExtentReport=new CustomExtentReport();
		extentReports=customExtentReport.getExtentReports();
		validations=new Validations();
		properties=getEnvProperites("qaenv.properties");
	}
	
	@Parameters({"browser"})
	@BeforeMethod
	public void beforeMethod(@Optional("firefox") String  browser) {
		
		webDriver=getDriver(browser);
		webDriver.get(properties.getProperty("url"));
		webDriver.manage().window().maximize();
		loginPageOperations=new LoginPageOperations(webDriver);
		
		
	}
	
	@Test(priority=1,groups= {"sanity"})
	public void withValidUserNameAndPassword() {
		
		extentTest=extentReports.createTest("withValidUserNameAndPassword");
		extentTest.log(Status.INFO, "Entering User Name");
		loginPageOperations.enterUserName(properties.getProperty("username"));
		extentTest.log(Status.INFO, "Entering Password");
		loginPageOperations.enterPassword(properties.getProperty("password"));
		extentTest.log(Status.INFO, "click on login");
		loginPageOperations.clickOnLoginBtn();
		
		
	}
	@Test(priority=2)
	public void withInvalidUserNameAndPassword() {
		extentTest=extentReports.createTest("withInvalidUserNameAndPassword");
		loginPageOperations.enterUserName("Admin1");
		loginPageOperations.enterPassword("admin1234");
		loginPageOperations.clickOnLoginBtn();
		String actualErrorMessage=loginPageOperations.getInvalidLoginCredentialsErrormessage();
		log.debug(actualErrorMessage);
		assert validations.Equals("Invalid credentials",actualErrorMessage);
	}
	
	@Test(priority=4)
	public void withInvalidUserNameAndValidPassword() {
		extentTest=extentReports.createTest("withInvalidUserNameAndValidPassword");
		loginPageOperations.enterUserName("Admin1");
		loginPageOperations.enterPassword("admin123");
		loginPageOperations.clickOnLoginBtn();
		String actualErrorMessage=loginPageOperations.getInvalidLoginCredentialsErrormessage();
		log.debug(actualErrorMessage);
		assert validations.Equals("Invalid credentials",actualErrorMessage);
	}
	
	@Test(priority=3)
	public void withvalidUserNameAndInvalidPassword() {
		extentTest=extentReports.createTest("withvalidUserNameAndInvalidPassword");
		loginPageOperations.enterUserName("Admin");
		loginPageOperations.enterPassword("admin1234");
		loginPageOperations.clickOnLoginBtn();
		String actualErrorMessage=loginPageOperations.getInvalidLoginCredentialsErrormessage();
		log.debug(actualErrorMessage);
		assert validations.Equals("Invalid credential",actualErrorMessage);
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {
	  System.out.println("method name:" + result.getMethod().getMethodName());
	  
	  if(result.getStatus() == ITestResult.FAILURE) {
          extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
          extentTest.fail(result.getThrowable());
          String screenshotPath=null;
	
			screenshotPath = customExtentReport.getScreenshot(webDriver, result.getName());
			//extentTest.log(Status.FAIL, extentTest.addScreenCaptureFromPath(screenshotPath).toString());
			//extentTest.log(Status.FAIL, MarkupHelper.createLabel(extentTest.addScreenCaptureFromPath(screenshotPath).toString(), ExtentColor.RED));
			//extentTest.addScreenCaptureFromPath(screenshotPath);
			extentTest.fail("Snapshot below: " + extentTest.addScreenCaptureFromPath(screenshotPath));
      }
      else if(result.getStatus() == ITestResult.SUCCESS) {
          extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
      }
      else {
          extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
          extentTest.skip(result.getThrowable());
      }

	  webDriver.close();
	}
	 
	@AfterTest
	public void teardown() {
		  extentReports.flush();
	}
	

	
}

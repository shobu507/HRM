package com.hrm.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	public WebDriver webDriver;
	
	public LoginPage(WebDriver driver) {
		webDriver=driver;
	}
			// Object Repository
	
	By username=By.id("txtUsername");
	By password=By.id("txtPassword");
	By loginBtn=By.id("btnLogin");
	By errorMessage=By.id("spanMessage");
	
			// Operation on object repo
	
	public void enterUserName(String userName) {
		webDriver.findElement(username).clear();
		webDriver.findElement(username).sendKeys(userName);
	}
	
	public void enterPassword(String pwd) {
		webDriver.findElement(password).clear();
		webDriver.findElement(password).sendKeys(pwd);
	}
	
	public void clickOnLoginBtn() {
		webDriver.findElement(loginBtn).click();
	}
	
	public String getInvlidCredentialsMsg() {
		return webDriver.findElement(errorMessage).getText();
	}
}

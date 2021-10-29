package com.hrm.pageoperations;

import org.openqa.selenium.WebDriver;

import com.hrm.pageobjects.LoginPage;

public class LoginPageOperations {
	
	public WebDriver webDriver;
	
	LoginPage loginPage;
	
	public  LoginPageOperations(WebDriver driver) {
		
		webDriver=driver;
		loginPage=new LoginPage(webDriver);
	}
	public void clickOnLoginBtn() {
		loginPage.clickOnLoginBtn();
	}
	
	public void getLoginErrorMessage() {
		
	}
	
	public void enterUserName(String name) {
		loginPage.enterUserName(name);
	}
	
	public void enterPassword(String password) {
		loginPage.enterPassword(password);
	}
	
	public String getInvalidLoginCredentialsErrormessage() {
		return loginPage.getInvlidCredentialsMsg();
	
	}
}

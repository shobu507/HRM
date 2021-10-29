package com.hrm.basetest;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver webDriver;
	
	public BaseTest(){
		
	}
	
	public Properties getEnvProperites(String propFile) {
		
		Properties prop=null;
		FileInputStream fis=null;
		
		try {
			fis=new FileInputStream(new File(System.getProperty("user.dir")+"/src/resources/"+propFile));
			prop=new Properties();
			prop.load(fis);
					
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	public WebDriver getDriver(String browserName) {
		
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			webDriver=new ChromeDriver(); 
		}else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			webDriver=new FirefoxDriver(); 
		}
		return webDriver;
	}
	public void closeWebdriver() {
		
		webDriver.close();
	}
	

}

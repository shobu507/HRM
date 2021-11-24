package com.hrm.login.tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hrm.basetest.BaseTest;



public class SimpleTest3 extends BaseTest{

	@Parameters({"OS","Browser"})
	@Test
	public void display(String a,String  b) {
		System.out.println(a);
		System.out.println(b);
		System.out.println();
	}
	
	@Parameters({"OS","Browser"})
	@Test
	public void print(String a,String  b) {
		System.out.println(a);
		System.out.println(b);
		
		
	}
}

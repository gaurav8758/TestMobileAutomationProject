package com.projectname.qa.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.projectname.qa.util.ExtentTestManager;
import com.projectname.qa.util.Verify;
import com.relevantcodes.extentreports.LogStatus;


public class Screen_Welcome {
	WebDriver driver=null;
	
	//Object declaration
	private By btnAgreeAndContinue = By.id("com.whatsapp:id/eula_accept");
	private By TextWelcome = By.xpath("//android.widget.TextView[@text='Welcome to WhatsApp']");
	
	public Screen_Welcome(WebDriver tdriver) {
		driver = tdriver;
		ExtentTestManager.getTest().log(LogStatus.PASS, "<font color=Blue><B>Welcome Screen</B></font>");
		// TODO Auto-generated constructor stub
	}
	
	public WebElement btnAgreeAndContinue(){
		return driver.findElement(btnAgreeAndContinue);
	}
	
	public WebElement TextWelcome(){
		return driver.findElement(TextWelcome);
	}
}

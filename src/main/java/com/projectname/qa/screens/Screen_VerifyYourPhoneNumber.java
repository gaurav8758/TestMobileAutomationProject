package com.projectname.qa.screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.projectname.qa.util.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;


public class Screen_VerifyYourPhoneNumber {
	WebDriver driver=null;
	
	//Object declaration
	
	
	public Screen_VerifyYourPhoneNumber(WebDriver tdriver) {
		driver = tdriver;
		ExtentTestManager.getTest().log(LogStatus.PASS, "<font color=Blue><B>Verify Your Phone Number Screen</B></font>");
		// TODO Auto-generated constructor stub
	}
	
	
	
}

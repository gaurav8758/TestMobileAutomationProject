package com.projectname.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Screen;
import com.projectname.qa.base.MobileTestBase;
import com.projectname.qa.screens.Screen_Welcome;
import com.projectname.qa.util.*;
import com.relevantcodes.extentreports.LogStatus;

public class Mobile_LoginTest extends MobileTestBase{
	WebDriver driver;
	Verify Assert;
	SupportSelenium SupportSelenium = new SupportSelenium();
	//LoginPage loginPage;
	//HomePage homePage;

	public Mobile_LoginTest(){
		super();
	}
	
	public void setup(){
		driver = getDriver();
		Assert = new Verify(driver);
		//loginPage = PageFactory.initElements(driver, LoginPage.class);
		ExtentTestManager.getTest().log(LogStatus.PASS, "<font color=GREEN><B>Setup Completed</B></font>");
	}

	@Test(priority=1, groups={"Testing","Mobile"})
	public void Mobile_Login_Test001(){
		setup();
		
		//Welcome Screen 
		Screen_Welcome WelcomeScreen = PageFactory.initElements(driver, Screen_Welcome.class);
		SupportSelenium.ElementDisplayed(driver, WelcomeScreen.element_TextWelcome, "'Welcome to Whatsapp' should be displayed in the screen.");
		//Assert.assertTrue(WelcomeScreen.TextWelcome().isDisplayed(), "'Welcome to Whatsapp' is not displayed in the screen.");
		WelcomeScreen.btnAgreeAndContinue().click();
		
		//driver.findElement(By.id("com.whatsapp:id/eula_accept")).click();
		//driver.findElement(By.id("com.whatsapp:id/cancel")).click();
		
		//driver.findElement(By.id("com.whatsapp:id/registration_phone")).sendKeys("2107739101");
		
		//Selecting a value
		//driver.findElement(By.id("com.whatsapp:id/registration_country")).click();
		//driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();
	}
	
	@Test(priority=1, groups={"Testing","Mobile"},enabled=false)
	public void Mobile_Login_Test002(){
		setup();
		driver.findElement(By.id("com.whatsapp:id/eula_accept")).click();
		driver.findElement(By.id("com.whatsapp:id/submit")).click();
		driver.findElement(By.id("com.android.packageinstaller:id/permission_deny_button")).click();
		driver.findElement(By.id("com.android.packageinstaller:id/permission_deny_button")).click();
		driver.findElement(By.id("com.whatsapp:id/registration_phone")).sendKeys("2107739101");
	}
}

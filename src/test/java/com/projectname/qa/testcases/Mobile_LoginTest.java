package com.projectname.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.projectname.qa.base.MobileTestBase;
import com.projectname.qa.util.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class Mobile_LoginTest extends MobileTestBase{
	WebDriver driver;
	//LoginPage loginPage;
	//HomePage homePage;

	public Mobile_LoginTest(){
		super();
	}
	
	public void setup(){
		driver = getDriver();
		//loginPage = PageFactory.initElements(driver, LoginPage.class);
		ExtentTestManager.getTest().log(LogStatus.PASS, "<font color=GREEN><B>Setup Completed</B></font>");
	}

	@Test(priority=1, groups={"Testing","Mobile"})
	public void Mobile_Login_Test001(){
		setup();
		driver.findElement(By.id("com.whatsapp:id/eula_accept")).click();
		driver.findElement(By.id("com.whatsapp:id/cancel")).click();
		driver.findElement(By.id("com.whatsapp:id/registration_phone")).sendKeys("2107739101");
		
		//Selecting a value
		driver.findElement(By.id("com.whatsapp:id/registration_country")).click();
		driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();
	}
	
	@Test(priority=1, groups={"Testing","Mobile"},enabled=true)
	public void Mobile_Login_Test002(){
		setup();
		driver.findElement(By.id("com.whatsapp:id/eula_accept")).click();
		driver.findElement(By.id("com.whatsapp:id/submit")).click();
		driver.findElement(By.id("com.android.packageinstaller:id/permission_deny_button")).click();
		driver.findElement(By.id("com.android.packageinstaller:id/permission_deny_button")).click();
		driver.findElement(By.id("com.whatsapp:id/registration_phone")).sendKeys("2107739101");
	}
}

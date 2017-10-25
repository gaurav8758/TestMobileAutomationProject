package com.projectname.qa.util;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

import com.projectname.qa.base.MobileTestBase;
import com.relevantcodes.extentreports.LogStatus;

public class SupportSelenium extends MobileTestBase{
	
	public void refresh(WebDriver driver) throws InterruptedException {
		ThreadExt.sleep(5000);
		driver.navigate().refresh();
		ThreadExt.sleep(5000);
	}
	
	public void HoverOver(WebDriver driver, WebElement Hoverelement, WebElement Clickelement) throws InterruptedException{
		ThreadExt.sleep(1000);
		Actions action = new Actions(driver);
		action.moveToElement(Hoverelement).moveToElement(Clickelement).click().build().perform();
	}
	
	public void MoveToElement(WebDriver driver, WebElement element) throws InterruptedException{
		//WebElement element = driver.findElement(By.id("id_of_element"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		ThreadExt.sleep(500); 
	}
	
	public void RobotMoveTo(WebDriver driver)
	{
		
		try{
			ThreadExt.sleep(200);
		org.openqa.selenium.Point coordinates = driver.findElement(By.xpath(".//*[@id='tcp-logo-header']/a")).getLocation();
		  Robot robot = new Robot();
		  robot.mouseMove(coordinates.getX(),coordinates.getY()+120);
		}
		catch(AWTException e)
		{
			
		}
	}
	
	public void MoveToElementAndClick(WebDriver driver, WebElement element) throws InterruptedException{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		ThreadExt.sleep(500); 
		Actions action = new Actions(driver);
		action.moveToElement(element).click().build().perform();
	}
	
	public void JavaScriptClick(WebDriver driver, WebElement element, String LinkName){
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		//ATUReports.add("", "Click the link - " + LinkName, "Condition met.", true);
	}
	
	public void ElementPresent(WebDriver driver, By ByXpath, String ExpectedResult)
	{
		//ByXpath=By.xpath("//span[contains(@class,'regular-price')]");
		try {
			if(driver.findElements(ByXpath).size() != 0)
			{
				 //ATUReports.add("", ExpectedResult, "Condition met.", true);
			}
			else
			{
				  //ATUReports.add("", ExpectedResult, "Condition not met.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
			//System.out.println("ByXpath: "+driver.findElement(ByXpath).getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ElementNotPresent(WebDriver driver, By ByXpath, String ExpectedResult)
	{
		if(driver.findElements(ByXpath).size() == 0)
		{
			//ATUReports.add("", ExpectedResult, "Condition met.", true);
		}
		else
		{
            //ATUReports.add("", ExpectedResult, "Condition not met.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void ElementNotDisplayed(WebDriver driver, By ByXpath, String ExpectedResult)
	{
		if(driver.findElement(ByXpath).isDisplayed()==false)
		{
			//ATUReports.add("", ExpectedResult, "Condition met.", true);
		}
		else
		{
            //ATUReports.add("", ExpectedResult, "Condition not met.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void ElementDisplayed(WebDriver driver, By ByXpath, String ExpectedResult)
	{
		ThreadExt.sleep(1000);
		if (isElementPresent(driver, ByXpath))
		{
				if(driver.findElement(ByXpath).isDisplayed())
					{
						ExtentTestManager.getTest().log(LogStatus.PASS, ExpectedResult + "<br /><font color=green>Condition met.</font>");  
						//ATUReports.add("", ExpectedResult, "Condition met.", true);
					}
				else
					{
						ExtentTestManager.getTest().log(LogStatus.FAIL, ExpectedResult + "<br /><font color=red>Condition not met.</font>");
						ExtentTestManager.getTest().log(LogStatus.FAIL, ExpectedResult + "<br /><font color=red>Condition not met.</font>" + ExtentTestManager.getTest().addScreenCapture(GetScreenShot.capture(driver, "screenShotName")));
			            //ATUReports.add("", ExpectedResult, "Condition not met.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
					}
			}
		else
			{
			  //ATUReports.add("", ExpectedResult, "Element not present.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
	}
	
	public void TextPresent(WebDriver driver, String SearchText, String ExpectedResult, WaitTime WaitTimeSelection) throws InterruptedException
	{
		ThreadExt.sleep(WaitTimeSelection);
		if(driver.getPageSource().toLowerCase().contains(SearchText.toLowerCase()))
		{
			//ATUReports.add("", ExpectedResult, "Condition met.", true);	
		}
		else
		{
	        //ATUReports.add("", ExpectedResult, "Condition not met.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void TextNotPresent(WebDriver driver, String SearchText, String ExpectedResult, WaitTime WaitTimeSelection) throws InterruptedException
	{
		ThreadExt.sleep(WaitTimeSelection);
		if(!driver.getPageSource().toLowerCase().contains(SearchText.toLowerCase()))
		{
			//ATUReports.add("", ExpectedResult, "Condition met.", true);	
		}
		else
		{
	        //ATUReports.add("", ExpectedResult, "Condition not met.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void HandleAlerts(WebDriver driver) throws InterruptedException{
		if (isAlertPresent(driver))
		{
			Alert alert=driver.switchTo().alert();
			alert.accept();
		}
	}
	
	public boolean isAlertPresent(WebDriver driver) throws InterruptedException 
	{ 
		ThreadExt.sleep(1000);
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
	}   // isAlertPresent()
	
	public boolean isElementPresent(WebDriver driver, By ByXpath)
	{
		if(driver.findElements(ByXpath).size() != 0)
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
	
	public void compare(String screenText, String actualText, String expectedResult) throws InterruptedException
	{
		
		if(screenText.equals(actualText))
		{
			//ATUReports.add("", expectedResult, "Condition met.", true);	
		}
		else
		{
	         //ATUReports.add("", expectedResult, "Condition not met.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void compareWithoutCase(String screenText, String actualText, String expectedResult) throws InterruptedException
	{
		
		if(screenText.equalsIgnoreCase(actualText))
		{
			//ATUReports.add("", expectedResult, "Condition met.", true);	
		}
		else
		{
	        //ATUReports.add("", expectedResult, "Condition not met.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	

	public void textContains(String screenText, String actualText, String expectedResult){
		if(screenText.toLowerCase().contains(actualText.toLowerCase())){
			//ATUReports.add("", expectedResult, "Condition met.", true); 
		}
		else
		{
			//ATUReports.add("", expectedResult, "Condition not met.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}
	
	public void doubleValueCompare(Double value1, Double value2, String expectedResult){
		if(Double.compare(value1, value2) == 0){
			//ATUReports.add("", expectedResult, "Condition met.", true); 
		}else{
			//ATUReports.add("", expectedResult, "Condition not met.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}	
	
	public void ieContinue(String globalBrowser, WebDriver dr) throws InterruptedException{
		if(globalBrowser.toLowerCase().contains("ie")){
			dr.navigate().to("javascript:document.getElementById('overridelink').click()");
			ThreadExt.sleep(3000);
		}
		
	}
	
	public boolean IsTextPresent(WebDriver driver, String SearchText, WaitTime WaitTimeSelection) throws InterruptedException
	{
		ThreadExt.sleep(WaitTimeSelection);
		boolean returnValue = false;
		if(driver.getPageSource().toLowerCase().contains(SearchText.toLowerCase()))
		{
			returnValue = true;	
		}
		return returnValue;
	}
}

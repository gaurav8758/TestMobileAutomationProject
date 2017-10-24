package com.projectname.qa.util;

import io.appium.java_client.events.api.general.AppiumWebDriverEventListener;

import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.projectname.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;



public class WebEventListener extends TestBase implements WebDriverEventListener,AppiumWebDriverEventListener {

		public void beforeNavigateTo(String url, WebDriver driver) {
			//System.out.println("Before navigating to: '" + url + "'");
		}

		public void afterNavigateTo(String url, WebDriver driver) {
			//System.out.println("Navigated to:'" + url + "'");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Navigated to: '" + url + "'");
		}

		public void beforeChangeValueOf(WebElement element, WebDriver driver) {
			//System.out.println("Value of the:" + element.toString() + " before any changes made");
		}

		public void afterChangeValueOf(WebElement element, WebDriver driver) {
			//System.out.println("Element value changed to: " + element.toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, "Element value changed to: " + element.toString());
		}

		public void beforeClickOn(WebElement element, WebDriver driver) {
			//System.out.println("Trying to click on: " + element.toString());
			//JavascriptExecutor jse = (JavascriptExecutor) driver;
			//ExtentTestManager.getTest().log(LogStatus.INFO, "Element value: " + element.toString().substring(0,element.toString().length()-1).split(" -> xpath: ")[1]);
			//jse.executeScript("document.getElementById('" + element.toString().substring(0,element.toString().length()-1).split(" -> xpath: ")[1] + "').focus();");
		}

		public void afterClickOn(WebElement element, WebDriver driver) {
			//System.out.println("Clicked on: " + element.toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, "Clicked on: " + element.toString());
		}

		public void beforeNavigateBack(WebDriver driver) {
			//System.out.println("Navigating back to previous page");
			//ExtentTestManager.getTest().log(LogStatus.PASS, "Navigating back to previous page");
		}

		public void afterNavigateBack(WebDriver driver) {
			//System.out.println("Navigated back to previous page");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Navigated back to previous page");
		}

		public void beforeNavigateForward(WebDriver driver) {
			//System.out.println("Navigating forward to next page");
		}

		public void afterNavigateForward(WebDriver driver) {
			//System.out.println("Navigated forward to next page");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Navigated forward to next page");
		}

		public void onException(Throwable error, WebDriver driver) {
			//System.out.println("Exception occured: " + error);
			if (!error.toString().contains("org.openqa.selenium.UnsupportedCommandException: actions")){
				ExtentTestManager.getTest().log(LogStatus.FAIL, "<font color='RED'><B>Exception occured: </B></font><br />" + error);
			
			}
				try {
					TestUtil.takeScreenshotAtEndOfTest(driver);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		public void beforeFindBy(By by, WebElement element, WebDriver driver) {
			//System.out.println("Trying to find Element By : " + by.toString());
		}

		public void afterFindBy(By by, WebElement element, WebDriver driver) {
			//System.out.println("Found Element By : " + by.toString());
			//ExtentTestManager.getTest().log(LogStatus.INFO, "Found Element By : " + by.toString());
		}

		/*
		 * non overridden methods of WebListener class
		 */
		public void beforeScript(String script, WebDriver driver) {

		}

		public void afterScript(String script, WebDriver driver) {
		}

		public void beforeAlertAccept(WebDriver driver) {
			// TODO Auto-generated method stub

		}

		public void afterAlertAccept(WebDriver driver) {
			// TODO Auto-generated method stub
			ExtentTestManager.getTest().log(LogStatus.PASS, "Alert was accepted.");

		}

		public void afterAlertDismiss(WebDriver driver) {
			// TODO Auto-generated method stub

		}

		public void beforeAlertDismiss(WebDriver driver) {
			// TODO Auto-generated method stub
			ExtentTestManager.getTest().log(LogStatus.PASS, "Alert was dismissed.");
		}

		public void beforeNavigateRefresh(WebDriver driver) {
			// TODO Auto-generated method stub
			
		}

		public void afterNavigateRefresh(WebDriver driver) {
			// TODO Auto-generated method stub
			ExtentTestManager.getTest().log(LogStatus.PASS, "Browser page was refreshed.");
		}

		public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
			// TODO Auto-generated method stub
		}

		public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
			// TODO Auto-generated method stub
			ExtentTestManager.getTest().log(LogStatus.PASS, "Entered '" + Arrays.toString(keysToSend) + "' in the element '" + element.toString() + "'." );
		}

}

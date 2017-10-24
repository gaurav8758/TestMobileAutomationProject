package com.projectname.qa.util;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.projectname.qa.base.MobileTestBase;
import com.relevantcodes.extentreports.LogStatus;

public class WebDriverListener implements IInvokedMethodListener {

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
        	//System.out.println("beforeInvocation called");
        	String browserName;
        	//String browserName = TestBase.GlobalBrowser;
        	if (!MobileTestBase.GlobalBrowser.equals(null))
        	{
        		browserName = MobileTestBase.GlobalBrowser;
        	}
        	else if (!method.getTestMethod().getXmlTest().getLocalParameters().get("browserName").equals(null))
        	{
        		 browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
        	}
        	else
        	{
        		 browserName = System.getProperty("browserName");
        	}
        	
            WebDriver driver = LocalDriverFactory.createInstance(browserName);
            LocalDriverManager.setWebDriver(driver);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Browser: <B>" + browserName.toUpperCase() + "</B><br />Browser is opened.");
        }
    }
 
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            WebDriver driver = LocalDriverManager.getDriver();
            if (null!=driver) {
                driver.quit();
            }
            ExtentTestManager.getTest().log(LogStatus.PASS, "The browser is closed.");
        }
    }
    
}

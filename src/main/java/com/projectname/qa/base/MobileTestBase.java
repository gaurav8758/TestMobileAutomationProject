package com.projectname.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.projectname.qa.util.ExtentManager;
import com.projectname.qa.util.ExtentTestManager;
import com.projectname.qa.util.LocalDriverManager;
import com.projectname.qa.util.TestUtil;
import com.projectname.qa.util.WebEventListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MobileTestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static String GlobalBrowser;
	public static String GlobalURL;
	public static boolean GlobalExtentReportsOverWrite=false;
	public static String GlobalExtentReportsLocation="C:\\Automation_Framework\\Reports\\ExtentReport.html";
	public static String GlobaldriverLocation;
	public static String Globalplatform;
	
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public MobileTestBase(){
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.INFO);
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(new File(System.getProperty("user.dir") + 
					"\\src\\main\\java\\com\\projectname\\qa\\config\\config.properties"));
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public WebDriver getDriver() {
        System.out.println("Thread id = " + Thread.currentThread().getId());
        System.out.println("Hashcode of webDriver instance = " + LocalDriverManager.getDriver().hashCode());
        driver = LocalDriverManager.getDriver();
        
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		if (Globalplatform.equalsIgnoreCase("desktop"))
		{
			//driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
			
	        driver.get(MobileTestBase.GlobalURL);
		}

		return driver;
    }
    
    @Parameters({"platform"})
    @BeforeSuite(alwaysRun=true)
    public void beforeSuite(String platform) {
    	Globalplatform = platform;
    	if (platform.equalsIgnoreCase("mobile")){
    		
    	}
	}

    @Parameters({ "URL", "browserName", "extentReportsOverwrite","extentReportsLocation", "driverLocation"})
    @BeforeMethod(alwaysRun=true)
    public void beforeMethod(String URL, String browserName, boolean extentReportsOverwrite, String extentReportsLocation, 
    						String driverLocation,Method method) {
    	//System.out.println("beforemethod called");
    	GlobalBrowser = browserName;
    	GlobalURL = URL;
    	GlobalExtentReportsOverWrite = Boolean.valueOf(extentReportsOverwrite);
    	GlobalExtentReportsLocation = extentReportsLocation;
    	GlobaldriverLocation = driverLocation;
        ExtentTestManager.startTest(method.getName());
    }
    	
   @AfterMethod
    protected void afterMethod(ITestResult result) {
       if (result.getStatus() == ITestResult.FAILURE) {
           ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
           //ExtentTestManager.getTest().log(LogStatus.INFO, "Snapshot below: " + test.addScreenCapture(TestUtil.getLocationTimeStampOnFailures()));
       } else if (result.getStatus() == ITestResult.SKIP) {
           ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
       } else {
           ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
       }
       
       ExtentManager.getReporter().endTest(ExtentTestManager.getTest());        
       ExtentManager.getReporter().flush();
    }

   protected String getStackTrace(Throwable t) {
       StringWriter sw = new StringWriter();
       PrintWriter pw = new PrintWriter(sw);
       t.printStackTrace(pw);
       return sw.toString();
   }
}

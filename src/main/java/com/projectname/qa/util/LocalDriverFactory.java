package com.projectname.qa.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.projectname.qa.base.MobileTestBase;
import com.relevantcodes.extentreports.LogStatus;

public class LocalDriverFactory {
    static synchronized AppiumDriver<?> createInstance(String platformName)  {
        AppiumDriver<?> Localdriver = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName", MobileTestBase.GlobaldeviceName);
    	capabilities.setCapability("platformVersion", MobileTestBase.GlobalplatformVersion);
    	capabilities.setCapability("platformName", MobileTestBase.GlobalplatformName);

   		if ((null!=MobileTestBase.GloballocalapkURL) && !(MobileTestBase.GloballocalapkURL.equalsIgnoreCase("")))
   		{
   			File app = new File(MobileTestBase.GloballocalapkURL);
   			capabilities.setCapability("app", app.getAbsolutePath());
   		}
   		else
   		{
        	capabilities.setCapability("appPackage", MobileTestBase.GlobalappPackage);
        	capabilities.setCapability("appActivity", MobileTestBase.GlobalappActivity);
   		}
   		
   		//Checking the Appium server URL
   		String AppiumServerURL = "";
   		if ((null!=MobileTestBase.GlobalappiumURL) && !(MobileTestBase.GlobalappiumURL.equalsIgnoreCase("")))
   		{
   			AppiumServerURL = MobileTestBase.GlobalappiumURL;
   		}
   		
        if (AppiumServerStartStop.isAppiumrunning())
        {
        	ExtentTestManager.getTest().log(LogStatus.PASS, "The appium server is running in the path: " + AppiumServerStartStop.service_url);
        	AppiumServerURL = AppiumServerStartStop.service_url;
        }
        else
        {
        	ExtentTestManager.getTest().log(LogStatus.FAIL, "The appium server is <B><font color=red>NOT</font><B> running in the path: " + AppiumServerStartStop.service_url);
        }
   		//Checking the Appium server URL
   		
   		if (platformName.equalsIgnoreCase("android"))
   		{
   			AndroidDriver<AndroidElement>driver=null;
			try 
			{
				driver = new AndroidDriver<AndroidElement>(new URL(AppiumServerURL), capabilities);
			} 
			catch (MalformedURLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Localdriver = driver;	
   		}
   		else
   		{
   			IOSDriver<IOSElement>driver=null;
			try 
			{
				driver = new IOSDriver<IOSElement>(new URL(AppiumServerURL), capabilities);
			} 
			catch (MalformedURLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Localdriver = driver;
   		}
        return Localdriver;
    }
}
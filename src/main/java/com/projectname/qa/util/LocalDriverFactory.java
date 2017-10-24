package com.projectname.qa.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.projectname.qa.base.MobileTestBase;

public class LocalDriverFactory {
    static synchronized AppiumDriver<?> createInstance(String browserName)  {
        AppiumDriver<?> Localdriver = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        if (MobileTestBase.Globalplatform.equalsIgnoreCase("mobile"))
        {
        	if (browserName.toLowerCase().contains("android"))
        		{
        		//File app = new File("C:\\Automation_Framework\\apps\\Explorer_com.estrongs.android.pop.apk");
        		AndroidDriver<AndroidElement>driver=null;
	        	capabilities.setCapability("deviceName", "Nexus 5");
	        	capabilities.setCapability("platformVersion", "6.0.1");
	        	capabilities.setCapability("platformName", "Android");
	        	//capabilities.setCapability("app", app.getAbsolutePath());
	        	capabilities.setCapability("appPackage", "com.whatsapp");
	        	capabilities.setCapability("appActivity", "com.whatsapp.Main");
	        	
	        	try 
		        	{	
						driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
						Localdriver = driver;
		        	} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        	else//IOS section
        		{
        		IOSDriver<IOSElement>driver=null;
        		//File app = new File("C:\\Automation_Framework\\apps\\Explorer_com.estrongs.android.pop.apk");
	        	capabilities.setCapability("deviceName", "Nexus 5");
	        	capabilities.setCapability("platformVersion", "6.0.1");
	        	capabilities.setCapability("platformName", "Android");
	        	//capabilities.setCapability("app", app.getAbsolutePath());
	        	capabilities.setCapability("appPackage", "com.whatsapp");
	        	capabilities.setCapability("appActivity", "com.whatsapp.Main");
	        	
	        	try 
		        	{	
						driver = new IOSDriver<IOSElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
						Localdriver = driver;
		        	} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
        		}
        	}
        return Localdriver;
    }
}
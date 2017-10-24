package com.projectname.qa.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.projectname.qa.base.MobileTestBase;

public class LocalDriverFactory {
    static synchronized AppiumDriver createInstance(String browserName)  {
        AppiumDriver driver = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        if (MobileTestBase.Globalplatform.equalsIgnoreCase("mobile"))
        {
        	if (browserName.toLowerCase().contains("android"))
        		{
        		//File app = new File("C:\\Automation_Framework\\apps\\Explorer_com.estrongs.android.pop.apk");
	        	
	        	capabilities.setCapability("deviceName", "Nexus 5");
	        	capabilities.setCapability("platformVersion", "6.0.1");
	        	capabilities.setCapability("platformName", "Android");
	        	//capabilities.setCapability("app", app.getAbsolutePath());
	        	capabilities.setCapability("appPackage", "com.whatsapp");
	        	capabilities.setCapability("appActivity", "com.whatsapp.Main");
	        	
	        	try 
		        	{	
						driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        	else//IOS section
        		{
        		//File app = new File("C:\\Automation_Framework\\apps\\Explorer_com.estrongs.android.pop.apk");
	        	capabilities.setCapability("deviceName", "Nexus 5");
	        	capabilities.setCapability("platformVersion", "6.0.1");
	        	capabilities.setCapability("platformName", "Android");
	        	//capabilities.setCapability("app", app.getAbsolutePath());
	        	capabilities.setCapability("appPackage", "com.whatsapp");
	        	capabilities.setCapability("appActivity", "com.whatsapp.Main");
	        	
	        	try 
		        	{	
						driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
        		}
        	}
        return driver;
    }
}
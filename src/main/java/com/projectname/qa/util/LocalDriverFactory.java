package com.projectname.qa.util;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.projectname.qa.base.MobileTestBase;

public class LocalDriverFactory {
    static synchronized WebDriver createInstance(String browserName)  {
        WebDriver driver = null;
        if (MobileTestBase.Globalplatform.equalsIgnoreCase("desktop"))
        {
	        if (browserName.toLowerCase().contains("firefox")) {
	            driver = new FirefoxDriver();
	            return driver;
	        }
	        if (browserName.toLowerCase().contains("internet")) {
	            driver = new InternetExplorerDriver();
	            return driver;
	        }
	        if (browserName.toLowerCase().contains("chrome")) {
				System.setProperty("webdriver.chrome.driver", MobileTestBase.GlobaldriverLocation + "\\chromedriver.exe");	
	            driver = new ChromeDriver();
	            return driver;
	        }
	        if (browserName.toLowerCase().contains("chrome_mac")) {
				System.setProperty("webdriver.chrome.driver", MobileTestBase.GlobaldriverLocation + "\\chromedriver_mac");	
	            driver = new ChromeDriver();
	            return driver;
	        }
        }
        else if (MobileTestBase.Globalplatform.equalsIgnoreCase("mobile"))
        {
        	if (browserName.toLowerCase().contains("android"))
        	{
        		//File app = new File("C:\\Automation_Framework\\apps\\Explorer_com.estrongs.android.pop.apk");
	        	
        		DesiredCapabilities capabilities = new DesiredCapabilities();
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
        	}
        return driver;
    }
}
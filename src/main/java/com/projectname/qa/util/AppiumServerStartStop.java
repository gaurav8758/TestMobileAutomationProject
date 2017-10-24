package com.projectname.qa.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.net.UrlChecker;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumServerStartStop {
    static String Appium_Node_Path="C:\\Program Files (x86)\\Appium\\node.exe";
    static String Appium_JS_Path="C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js";
    static AppiumDriverLocalService service;
    public static String service_url;

    public static void appiumStart(){
        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().
                usingPort(4723).usingDriverExecutable(new File(Appium_Node_Path)).
                withAppiumJS(new File(Appium_JS_Path)));
        service.start();
        
        try 
        {
			Thread.sleep(25000);
		} catch (InterruptedException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        service_url = service.getUrl().toString();
    }

    public static void appiumStop(){
        service.stop();
    }
    
    public static boolean isAppiumrunning() {
        try {
            new UrlChecker().waitUntilAvailable(10, TimeUnit.SECONDS, new URL(service_url));
            return true;
        } catch (UrlChecker.TimeoutException e) {
            return false;
        } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
}

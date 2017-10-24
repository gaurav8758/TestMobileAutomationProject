package com.projectname.qa.util;

import io.appium.java_client.AppiumDriver;

public class LocalDriverManager {
    private static ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<AppiumDriver>();
 
    public static synchronized AppiumDriver getDriver() {
        return appiumDriver.get();
    }
 
    static void setAppiumDriver(AppiumDriver driver) {
    	appiumDriver.set(driver);
    }
}
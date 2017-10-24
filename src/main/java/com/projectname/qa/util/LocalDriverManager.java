package com.projectname.qa.util;

import org.openqa.selenium.WebDriver;

public class LocalDriverManager {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
 
    public static synchronized WebDriver getDriver() {
        return webDriver.get();
    }
 
    static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }
}
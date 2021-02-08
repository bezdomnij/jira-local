package com.codecool.util;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverS {
    public static WebDriver instance = null;

    private WebDriverS() { }

    public static WebDriver getInstance(){
        if (instance == null){
            ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
            instance = new ChromeDriver();
            instance.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        }
        return instance;
    }

    public static void close() {
        instance.quit();
    }
}

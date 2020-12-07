package com.codecool.pages;

import com.codecool.util.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class LoginPage {

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    WebDriver driver = WebDriverSingleton.getInstance();

    @FindBy(id="login-form-username")
    private WebElement username;

    @FindBy(id = "login-form-password")
    private WebElement password;

    @FindBy(id = "login")
    private WebElement loginButton;

    public void loginSuccessful() {
        driver.manage().window().maximize();
        username.sendKeys(System.getenv("USERNAME"));
        password.sendKeys(System.getenv("PASSWORD"));
        loginButton.click();
    }
}

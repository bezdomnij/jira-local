package com.codecool.pages;

import com.codecool.util.WebDriverSingleton;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class LoginPage {

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    WebDriver driver = WebDriverSingleton.getInstance();

    @FindBy(id = "login-form-username")
    private WebElement username;

    @FindBy(id = "login-form-password")
    private WebElement password;

    @FindBy(id = "login-form-submit")
    private WebElement loginButton;

    @FindBy(id = "usernameerror")
    private WebElement loginError;

    @FindBy(xpath = "//p[contains(text(),'Sorry, your userid is required to answer a CAPTCHA')]")
    private WebElement captcha;


    public void loginSuccessful() {
        driver.manage().window().maximize();
        username.sendKeys(System.getenv("USERNAME"));
        password.sendKeys(System.getenv("PASSWORD"));
        loginButton.click();
    }

    public WebElement loginFailed(String reason) throws InterruptedException {
        driver.manage().window().maximize();
        if (reason.equals("wrongUsername")) {
            username.sendKeys("wrongUsername");
            password.sendKeys(System.getenv("PASSWORD"));
        } else {
            Thread.sleep(3000);
            username.sendKeys(System.getenv("USERNAME"));
            password.sendKeys("wrongPassword");
        }
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(loginError));
        return loginError;
    }

    public WebElement loginWrongPassword3Times() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            username.sendKeys(System.getenv("USERNAME"));
            password.sendKeys("wrongPassword");
            loginButton.click();
        }
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(captcha));
        return captcha;
    }
}

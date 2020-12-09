package com.codecool.pages;

import com.codecool.util.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class CreateIssuePage {

    public CreateIssuePage() {
        PageFactory.initElements(driver, this);
    }

    WebDriver driver = WebDriverSingleton.getInstance();

    @FindBy(id ="project-field")
    private WebElement dropdown;

    @FindBy(id ="issuetype-field" )
    private WebElement dropDownIssue;

    @FindBy(id = "summary")
    private WebElement summary;

    public void createNewIssue() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        dropdown.click();
        dropdown.sendKeys("Main");
        dropdown.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
        dropDownIssue.click();
        Thread.sleep(2000);
        dropDownIssue.sendKeys("Task");
        dropDownIssue.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
        summary.click();
        Thread.sleep(2000);
        summary.sendKeys("randomString");
        summary.sendKeys(Keys.ENTER);
    }
}

package com.codecool.pages;

import com.codecool.util.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class CreateIssuePage {

    public CreateIssuePage() {
        PageFactory.initElements(driver, this);
    }

    WebDriver driver = WebDriverSingleton.getInstance();


    public boolean createNewIssue() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement dropdown = driver.findElement(By.xpath("//input[@id='project-field']"));
        dropdown.click();
        dropdown.sendKeys("Main");
        dropdown.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
        WebElement dropDownIssue = driver.findElement(By.xpath("//input[@id='issuetype-field']"));
        dropDownIssue.click();
        Thread.sleep(2000);
        dropDownIssue.sendKeys("Task");
        dropDownIssue.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
        WebElement summary = driver.findElement(By.xpath("//input[@id='summary']"));
        summary.click();
        Thread.sleep(2000);
        summary.sendKeys("randomString");
        summary.sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//a[@id='find_link']")).click();
        driver.findElement(By.xpath("//a[@id='filter_lnk_reported_lnk']")).click();
        WebElement searchQuery = driver.findElement(By.xpath("//input[@id='searcher-query']"));
        searchQuery.click();
        searchQuery.sendKeys("randomString");
        searchQuery.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[starts-with(@data-issue-key,'MTP')]")).click();
        driver.findElement(By.xpath("//a[@id='opsbar-operations_more']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Delete')]")).click();
        driver.findElement(By.xpath("//input[@id='delete-issue-submit']")).click();
        return true;
    }
}

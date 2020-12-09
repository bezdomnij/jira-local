package com.codecool.pages;

import com.codecool.util.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateIssuePage {
    @FindBy(xpath = "//div[@class=\"aui-message aui-message-success success closeable shadowed aui-will-close\"]")
    private WebElement successMessage;

    @FindBy(xpath = "//input[@id='project-field']")
    private WebElement dropDown;

    WebDriver driver = WebDriverSingleton.getInstance();
    LoginPage loginPage = new LoginPage();

    public CreateIssuePage() {
        PageFactory.initElements(driver, this);
    }


    public boolean createNewIssue() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOf(dropDown));
        dropDown.click();
        dropDown.sendKeys("Main");
        dropDown.sendKeys(Keys.ENTER);

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

        WebDriverWait wait3 = new WebDriverWait(driver, 3);
        wait3.until(ExpectedConditions.elementToBeClickable(successMessage));

        return successMessage.getText().endsWith(" has been successfully created.");
    }

    public void deleteIssue() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[starts-with(@data-issue-key,'MTP')]")).click();
        driver.findElement(By.xpath("//a[@id='opsbar-operations_more']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Delete')]")).click();
        driver.findElement(By.xpath("//input[@id='delete-issue-submit']")).click();
    }

    public void searchForIssue() {
        driver.findElement(By.xpath("//a[@id='find_link']")).click();
        driver.findElement(By.xpath("//a[@id='filter_lnk_reported_lnk']")).click();
        WebElement searchQuery = driver.findElement(By.xpath("//input[@id='searcher-query']"));
        searchQuery.click();
        searchQuery.sendKeys("randomString");
        searchQuery.sendKeys(Keys.ENTER);
    }
}

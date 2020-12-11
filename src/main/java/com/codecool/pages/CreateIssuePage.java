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

import java.util.concurrent.TimeUnit;

public class CreateIssuePage {
    @FindBy(xpath = "//div[@class=\"aui-message aui-message-success success closeable shadowed aui-will-close\"]")
    private WebElement successMessage;

    @FindBy(xpath = "//input[@id='project-field']")
    private WebElement dropDown;

    WebDriver driver = WebDriverSingleton.getInstance();
    LoginPage loginPage = new LoginPage();
    WebDriverWait wait = new WebDriverWait(driver, 5);

    private DashBoardPage dashBoardPage =new DashBoardPage();

    public CreateIssuePage() {
        PageFactory.initElements(driver, this);
    }

//    WebDriver driver = WebDriverSingleton.getInstance();

    @FindBy(id ="project-field")
    private WebElement dropdown;

    @FindBy(id ="issuetype-field" )
    private WebElement dropDownIssue;


    @FindBy(id = "summary")
    private WebElement summary;


    public String createNewIssue(String project, String issueType, String issueSummary) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(dashBoardPage.getCreateIssueButton()));
        dashBoardPage.getCreateIssueButton().click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        dropdown.click();
        dropdown.sendKeys(project);
        dropdown.sendKeys(Keys.ENTER);

        Thread.sleep(2000);

        dropDownIssue.click();
        Thread.sleep(2000);

        dropDownIssue.sendKeys(issueType + Keys.ENTER);

        Thread.sleep(2000);
        summary.click();
        Thread.sleep(2000);
        summary.sendKeys(issueSummary);
        summary.sendKeys(Keys.ENTER);

        WebDriverWait wait3 = new WebDriverWait(driver, 3);
        wait3.until(ExpectedConditions.elementToBeClickable(successMessage));
        String id = getCreatedIssueId(successMessage.getText());
        System.out.println(successMessage.getText());
        System.out.println(id);

        return id;
    }

    private String getCreatedIssueId(String text) {
        String [] words = text.split(" ");
        return words[1];
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

    public boolean compare(String result, String project) {
        String [] resultArray = result.split("-");
        return resultArray[0].equals(project);
    }
}

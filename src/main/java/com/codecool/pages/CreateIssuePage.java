package com.codecool.pages;

import com.codecool.util.WebDriverSingleton;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CreateIssuePage {
    //div[contains(@class, 'aui-message-success') and contains(@class, 'shadowed')];
    @FindBy(xpath = "//div[contains(@class, 'aui-message-success') and contains(@class, 'shadowed')]")
    private WebElement successMessage;

    @FindBy(xpath = "//input[@id='project-field']")
    private WebElement dropDown;

    WebDriver driver = WebDriverSingleton.getInstance();
    LoginPage loginPage = new LoginPage();

    public CreateIssuePage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 4), this);
    }

//    WebDriver driver = WebDriverSingleton.getInstance();

    @FindBy(id = "project-field")
    private WebElement dropdown;

    @FindBy(id = "issuetype-field")
    private WebElement dropDownIssue;


    @FindBy(id = "summary")
    private WebElement summary;

    private WebDriverWait wait = new WebDriverWait(driver, 5);

    public String createNewIssue(String project, String issueType, String issueSummary) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        dropdown.click();
        dropdown.sendKeys(project);
        dropdown.sendKeys(Keys.ENTER);


        try {
            wait.until(ExpectedConditions.stalenessOf(dropDownIssue));
        } catch (Exception e) {
            System.out.println("dropdown issue exception caught");
        }
        wait.until(ExpectedConditions.visibilityOf(dropDownIssue));
        dropDownIssue.click();
        dropDownIssue.sendKeys(issueType + Keys.ENTER);

        /*try {
            wait.until(ExpectedConditions.stalenessOf(summary));
        } catch (Exception e) {
            System.out.println("summary exception caught");
        }
        wait.until(ExpectedConditions.elementToBeClickable(summary));*/

        // testing usability of other way to ignore StaleElementReferenceException
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(summary));

        summary.sendKeys(issueSummary);
        summary.sendKeys(Keys.ENTER);


        wait.until(ExpectedConditions.elementToBeClickable(successMessage));
        String id = getCreatedIssueId(successMessage.getText());
        System.out.println(successMessage.getText());
        System.out.println(id);

        return id;
    }

    private String getCreatedIssueId(String text) {
        String[] words = text.split(" ");
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
        String[] resultArray = result.split("-");
        return resultArray[0].equals(project);
    }
}

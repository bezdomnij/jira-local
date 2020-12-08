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

public class IssuesPage {
    WebDriver driver = WebDriverSingleton.getInstance();
//    DashBoardPage dashBoardPage = new DashBoardPage();

    @FindBy(xpath="//img[starts-with(@alt, 'User profile')]")
    private WebElement userIcon;

    @FindBy(xpath = "//a[@title='Search for issues and view recent issues']")
    private WebElement issueMenuItem;

    @FindBy(xpath = "//a[@id=\"create_link\"]")
    private WebElement createButton;

    @FindBy(xpath = "//input[@id='project-field']")
    private WebElement projectInputField;

    @FindBy(xpath = "//input[@id='issuetype-field']")
    private WebElement typeField;

    @FindBy(xpath = "//div[@class=\"jira-dialog-heading\"]//h2")
    private WebElement createHeading;

    @FindBy(id = "summary")
    private WebElement summaryField;

    @FindBy(xpath = "//*[@id=\"issuetype-single-select\"]//span[text()='More']")
    private WebElement getTypeDropdown;

    @FindBy(xpath = "//*[@id=\"issuetype-single-select\"]//span[text()='More']")
    private WebElement typeLine;

    public IssuesPage() {
        PageFactory.initElements(driver, this);
        //driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
    }

    // where am I
    public boolean checkLoginStatus() throws InterruptedException {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOf(createButton));
        createButton.click();
        WebDriverWait wait2 = new WebDriverWait(driver, 3);
        wait2.until(ExpectedConditions.visibilityOf(createHeading));
        projectInputField.click(); // clear field
//        projectInputField.clear();
        projectInputField.clear();
        projectInputField.sendKeys("Main Testing Project (MTP)" + Keys.TAB);
        wait.until(ExpectedConditions.visibilityOf(typeField));
        System.out.println("megvan!");
        Thread.sleep(1000);
        WebElement lofax = driver.findElement(By.id("issuetype-field"));
        lofax.click();
        lofax.clear();
        lofax.sendKeys("Task" + Keys.TAB);
//        WebDriverWait wait3 = new WebDriverWait(driver, 3);
//        typeField.sendKeys(Keys.BACK_SPACE);
//        typeField.clear();
//        typeField.sendKeys(Keys.TAB);
//        Thread.sleep(2000);
//        WebElement summary = driver.findElement(By.xpath("//input[@id='summary']"));
//        wait3.until(ExpectedConditions.visibilityOf(summaryField));
        Thread.sleep(1000);
        WebElement sum = driver.findElement(By.xpath("//input[@id=\"summary\"]"));
        sum.click();
        sum.sendKeys("szoveg" + Keys.ENTER);

//        summary.sendKeys("randomString");
//        typeField.sendKeys(Keys.TAB);
        Thread.sleep(2000);
//        summaryField.sendKeys("Fuck that");

        WebElement result = driver.findElement(By.xpath("//div[@class=\"aui-message aui-message-success success closeable shadowed aui-will-close\"]"));
        return result.getText().endsWith(" has been successfully created.");
    }

    public String createIssue() {
//        WebDriverWait wait = new WebDriverWait(driver, 3);
//        wait.until(ExpectedConditions.visibilityOf(createButton));
        System.out.println(createButton);
//        createButton.click();
        return null;
    }
}

package com.codecool.pages;

import com.codecool.util.WebDriverSingleton;
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

    public IssuesPage() {
        PageFactory.initElements(driver, this);
        //driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
    }

    // where am I
    public boolean checkLoginStatus() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
//        wait.until(ExpectedConditions.visibilityOf(userIcon));
//        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOf(createButton));
        createButton.click();
        wait.until(ExpectedConditions.visibilityOf(projectInputField));
        projectInputField.click();
        projectInputField.sendKeys("Main Testing Project" + Keys.RETURN);
        return userIcon.isDisplayed();
    }

    public String createIssue() {
//        WebDriverWait wait = new WebDriverWait(driver, 3);
//        wait.until(ExpectedConditions.visibilityOf(createButton));
        System.out.println(createButton);
//        createButton.click();
        return null;
    }
}

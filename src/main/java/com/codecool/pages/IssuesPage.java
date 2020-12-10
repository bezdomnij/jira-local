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

public class IssuesPage {
    WebDriver driver = WebDriverSingleton.getInstance();
    WebDriverWait wait = new WebDriverWait(driver, 3);

    @FindBy(xpath = "//a[@id=\"create_link\"]")
    private WebElement createButton;

    @FindBy(xpath = "//input[@id='project-field']")
    private WebElement projectInputField;

    @FindBy(xpath = "//input[@id='issuetype-field']")
    private WebElement typeInputField;

    @FindBy(id = "summary")
    private WebElement summaryField;

    @FindBy(xpath = "//div[@class=\"aui-message aui-message-success success closeable shadowed aui-will-close\"]")
    private WebElement successMessage;

    public IssuesPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean createIssue() {
        wait.until(ExpectedConditions.elementToBeClickable(createButton));
        createButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(projectInputField));
        projectInputField.click(); // clear field
        projectInputField.sendKeys("Main" + Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(typeInputField));
        typeInputField.click();
        typeInputField.sendKeys("Bug" + Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(summaryField));
        summaryField.click();
        summaryField.sendKeys("szoveg" + Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(successMessage));
        return successMessage.getText().endsWith(" has been successfully created.");
    }
}

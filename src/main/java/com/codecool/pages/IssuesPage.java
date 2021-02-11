package com.codecool.pages;

import com.codecool.util.WebDriverS;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class IssuesPage {
    WebDriver driver = WebDriverS.getInstance();
    WebDriverWait wait = new WebDriverWait(driver, 3);

    @FindBy(xpath = "//a[@id=\"create_link\"]")
    private WebElement createButton;

    public WebElement getCreateButton() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return createButton;
    }

    @FindBy(xpath = "//input[@id='project-field']")
    private WebElement projectInputField;

//    @FindBy(xpath = "//input[@id='issuetype-field']")
    @FindBy(id = "issuetype-field")
    private WebElement typeInputField;

    @FindBy(id = "summary")
    private WebElement summaryField;

    @FindBy(xpath = "//div[@class=\"aui-message aui-message-success success closeable shadowed aui-will-close\"]")
    private WebElement successMessage;

    @FindBy(xpath = "//div[@id=\"aui-flag-container\"]//span[contains(@class,'icon-close')]")
    private WebElement popUpMessageClose;

    public IssuesPage() {
        PageFactory.initElements(driver, this);
    }

    public String createIssue(String project, String issueType, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(projectInputField));
        projectInputField.click(); // clear field (js clears it)
        projectInputField.sendKeys(project);
        projectInputField.sendKeys(Keys.ENTER);
        //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        // typeInputField part
        try {
            wait.until(ExpectedConditions.invisibilityOf(typeInputField));
        } catch (Exception e) {
            System.out.println("Project ENTER utan - type input field visible volt"); //yes
        }

        wait.until(ExpectedConditions.visibilityOf(typeInputField));
        typeInputField.click();
//        // innentol uj --------------------------------------
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            wait.until(ExpectedConditions.invisibilityOf(typeInputField));
        } catch (Exception e) {
            System.out.println("TypeInputField visible volt meg egyszer"); //yes
        }
        System.out.println(issueType);// yes, of course
        //        above idaig uj ------------------------------
        typeInputField.sendKeys(issueType + Keys.TAB);
        //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

        // next one is new ----------------------------
        try {
            wait.until(ExpectedConditions.invisibilityOf(summaryField));
        } catch (TimeoutException e) {
            System.out.println("summaryField not invisible"); // yes
            summaryField.click();
        }
        // above idaig uj ----------------------
        wait.until(ExpectedConditions.visibilityOf(summaryField));
        summaryField.click();
        summaryField.sendKeys(text + Keys.ENTER);

        try {
            wait.until(ExpectedConditions.stalenessOf(successMessage));
        } catch (TimeoutException e) {
            System.out.println("success message not stale"); // yes
        }
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        String id = getCreatedIssueId(successMessage.getText());
        System.out.println(successMessage.getText());// yes
        System.out.println(id);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id=\"aui-flag-container\"]//span[contains(@class,'icon-close')]")));
        popUpMessageClose.click();

        return id;
    }

    public void clickOnTypeInputField() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
        clickOn(typeInputField);
    }

    public void clickOnSummaryField() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
        clickOn(summaryField);
    }

    protected void clickOn(WebElement webElement) {
        waitForClickable(webElement);
        webElement.click();
    }

    protected void waitForClickable(WebElement webElement) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public boolean compare(String result, String project) {
        String [] resultArray = result.split("-");
        return resultArray[0].equals(project);
    }
    private String getCreatedIssueId(String text) {
        System.out.println(text);
        String [] words = text.split(" ");
        return words[1];
    }
}

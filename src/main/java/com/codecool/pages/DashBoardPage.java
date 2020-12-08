package com.codecool.pages;

import com.codecool.util.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DashBoardPage {

    public DashBoardPage() {
        PageFactory.initElements(driver, this);
        //driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
    }

    WebDriver driver = WebDriverSingleton.getInstance();

    @FindBy(xpath="//img[starts-with(@alt, 'User profile')]")
    private WebElement userIcon;

    @FindBy(id="log_out")
    private WebElement logout;

    @FindBy(id="view_profile")
    private WebElement userProfile;

    @FindBy(xpath = "//h1[contains(text(),'Logout')]")
    private WebElement logoutConfirmation;

    @FindBy(id = "browse_link")
    private WebElement browseLink;

    @FindBy(id = "project_view_all_link")
    private WebElement viewAllLink;

    @FindBy(id = "project-filter-text")
    private WebElement projectFilterText;

    public boolean checkLogout() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(userIcon));
        userIcon.click();
        return logout.isDisplayed();
    }

    public String checkUserName() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(userIcon));
        userIcon.click();
        ViewProfilePage viewProfilePage = new ViewProfilePage();
        return viewProfilePage.getUserNameTitle();
    }

    public WebElement logout(){
        userIcon.click();
        logout.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            wait.until(ExpectedConditions.visibilityOf(logoutConfirmation));
        }catch (TimeoutException e){
            return null;
        }
        return logoutConfirmation;
    }

    public String browseProject(String projectName){
        String xpathTerm = String.format("//a[starts-with(@title,'%s')]", projectName);
        browseLink.click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        viewAllLink.click();
        projectFilterText.sendKeys(projectName);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.findElement(By.xpath(xpathTerm)).click();
        String checkIconXpath = String.format("//img[starts-with(@alt, '%s')]", projectName);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(checkIconXpath)));
        WebElement icon = driver.findElement(By.xpath(checkIconXpath));
        return icon.getAttribute("alt");
    }

    public String searchProject(String projectName){
        String locator = String.format("//a[contains(text(),'%s Project')]", projectName);
        String url = String.format("https://jira.codecool.codecanvas.hu/projects/%s", projectName);
        driver.get(url);
        return driver.findElement(By.xpath(locator)).getText();
    }


}

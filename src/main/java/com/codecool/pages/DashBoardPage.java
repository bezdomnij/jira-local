package com.codecool.pages;

import com.codecool.util.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
}

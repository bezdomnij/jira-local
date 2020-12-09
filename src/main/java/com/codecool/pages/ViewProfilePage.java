package com.codecool.pages;

import com.codecool.util.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewProfilePage {

    public ViewProfilePage() {
        PageFactory.initElements(driver, this);
        driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/ViewProfile.jspa");
    }

    WebDriver driver = WebDriverSingleton.getInstance();

    @FindBy(id = "up-user-title-name")
    private WebElement userNameTitle;

    public String getUserNameTitle() {
        return userNameTitle.getText();
    }
}

package com.codecool.pages;

import com.codecool.util.WebDriverS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewProfilePage {
    WebDriver driver = WebDriverS.getInstance();

    public ViewProfilePage() {
        PageFactory.initElements(driver, this);
        driver.navigate().to("https://jira.codecool.codecanvas.hu/secure/ViewProfile.jspa");
    }

    @FindBy(id = "up-user-title-name")
    private WebElement userNameTitle;

    public String getUserNameTitle() {
        return userNameTitle.getText();
    }

    public void setUserNameTitle(WebElement userNameTitle) {
        this.userNameTitle = userNameTitle;
    }
}

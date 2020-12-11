package com.codecool;

import com.codecool.pages.AlternateLogin;
import com.codecool.pages.CreateIssuePage;
import com.codecool.pages.DashBoardPage;
import com.codecool.pages.LoginPage;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage =  new DashBoardPage();
    AlternateLogin alternateLogin = new AlternateLogin();
    CreateIssuePage createIssuePage = new CreateIssuePage();



    @ParameterizedTest
    @CsvSource({"User 10"})
    public void testLoginSuccessful(String userId) {
        loginPage.loginSuccessful();
        boolean isLogOutPresent = dashBoardPage.checkLogout();
        String userName = dashBoardPage.checkUserName();
        assertTrue(isLogOutPresent && userId.equals(userName));
    }

    @Test
    public void testLoginFailedWithIncorrectPassword() throws InterruptedException {
        WebElement loginError = loginPage.loginFailed("incorrectPassword");
        assertNotNull(loginError);
    }

    @Test
    public void testLoginFailedWithIncorrectUserName() throws InterruptedException {
        WebElement loginError = loginPage.loginFailed("incorrectUsername");
        assertNotNull(loginError);
    }

    /*@Test
    public void loginWrongPassword3Times() throws InterruptedException {
        WebElement captcha = loginPage.loginWrongPassword3Times();
        assertNotNull(captcha);
    }*/

    @ParameterizedTest
    @CsvSource({"User 10"})
    public void testAlternateLoginSuccessful(String userId) {
        alternateLogin.loginSuccessfulAlternateLoginPage();
        boolean isLogOutPresent = dashBoardPage.checkLogout();
        String userName = dashBoardPage.checkUserName();
        assertTrue(isLogOutPresent && userId.equals(userName));
    }
}

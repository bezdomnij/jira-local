package com.codecool;

import com.codecool.pages.AlternateLogin;
import com.codecool.pages.DashBoardPage;
import com.codecool.pages.IssuesPage;
import com.codecool.pages.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage =  new DashBoardPage();
    AlternateLogin alternateLogin = new AlternateLogin();
    IssuesPage issuesPage = new IssuesPage();

    @ParameterizedTest
    @CsvSource({"User 10"})
    public void testLoginSuccessful(String userId) {
        loginPage.loginSuccessful();
        boolean isLogOutPresent = dashBoardPage.checkLogout();
        String userName = dashBoardPage.checkUserName();
        assertTrue(isLogOutPresent && userId.equals(userName));
    }

    @Test
    public void testIssuesCheckLoginStatus() {
        assertTrue(issuesPage.checkLoginStatus());
    }

    @Test
    public void testCreateIssue() {
        assertNull(issuesPage.createIssue());
    }

//    @Test
//    public void testLoginFailedWithIncorrectPassword() throws InterruptedException {
//        WebElement loginError = loginPage.loginFailed("incorrectPassword");
//        assertNotNull(loginError);
//    }

//    @Test
//    public void testLoginFailedWithIncorrectUserName() throws InterruptedException {
//        WebElement loginError = loginPage.loginFailed("incorrectUsername");
//        assertNotNull(loginError);
//    }
//
//    @Test
//    public void loginWrongPassword3Times() throws InterruptedException {
//        WebElement captcha = loginPage.loginWrongPassword3Times();
//        assertNotNull(captcha);
//    }
//
//    @ParameterizedTest
//    @CsvSource({"User 10"})
//    public void testAlternateLoginSuccessful(String userId) {
//        alternateLogin.loginSuccessfulAlternateLoginPage();
//        boolean isLogOutPresent = dashBoardPage.checkLogout();
//        String userName = dashBoardPage.checkUserName();
//        assertTrue(isLogOutPresent && userId.equals(userName));
//    }


}

package com.codecool;

import com.codecool.pages.DashBoardPage;
import com.codecool.pages.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Unit test for simple App.
 */
public class AppTest {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage =  new DashBoardPage();

    @ParameterizedTest
    @CsvSource({"User 10"})
    public void testLoginSuccessful(String userId) {
        loginPage.loginSuccessful();
        boolean isLogOutPresent = dashBoardPage.checkLogout();
        String userName = dashBoardPage.checkUserName();
        assertTrue(isLogOutPresent && userId.equals(userName));
    }

    @Test
    public void testLoginFailedWithIncorrectPassword(){
        WebElement loginError = loginPage.loginFailed("incorrectPassword");
        assertNotNull(loginError);
    }

    @Test
    public void testLoginFailedWithIncorrectUserName(){
        WebElement loginError = loginPage.loginFailed("incorrectUsername");
        assertNotNull(loginError);
    }
}

package com.codecool;

import com.codecool.pages.DashBoardPage;
import com.codecool.pages.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit test for simple App.
 */
public class AppTest {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage =  new DashBoardPage();

    @ParameterizedTest
    @CsvSource({"user10"})
    public void testLoginSuccessful(String userId) throws InterruptedException {
        loginPage.loginSuccessful();
        boolean isLogOutPresent = dashBoardPage.checkLogout();
        String userName = dashBoardPage.checkUserName();
        assertEquals(userId, userName);
    }
}

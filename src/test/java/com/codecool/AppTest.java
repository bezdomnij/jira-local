package com.codecool;

import com.codecool.pages.AlternateLogin;
import com.codecool.pages.DashBoardPage;
import com.codecool.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit test for simple App.
 */
public class AppTest {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage =  new DashBoardPage();
    AlternateLogin alternateLogin = new AlternateLogin();

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

    @Test
    public void testLogout(){
        loginPage.loginSuccessful();
        WebElement logoutConfirmation = dashBoardPage.logout();
        Assertions.assertNotNull(logoutConfirmation);
    }

    @ParameterizedTest
    @CsvSource({"TOUCAN projekt, TOUCAN",
                "COALA Project, COALA",
                "JETI Project, JETI"})
    public void testBrowseProject(String expected, String project){
        loginPage.loginSuccessful();
        String projectName = dashBoardPage.browseProject(project);
        assertEquals(expected, projectName);
    }

    @Test
    public void searchProject(){
        loginPage.loginSuccessful();
        String actualProject = dashBoardPage.searchProject("Main Testing", "MTP");
        assertEquals("Main Testing Project", actualProject);
    }


}

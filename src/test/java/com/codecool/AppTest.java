package com.codecool;

import com.codecool.pages.AlternateLogin;
import com.codecool.pages.CreateIssuePage;
import com.codecool.pages.DashBoardPage;
import com.codecool.pages.LoginPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit test for simple App.
 */
public class AppTest {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage =  new DashBoardPage();
    AlternateLogin alternateLogin = new AlternateLogin();
    CreateIssuePage createIssuePage = new CreateIssuePage();

    /*@ParameterizedTest
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

    *//*@Test
    public void loginWrongPassword3Times() throws InterruptedException {
        WebElement captcha = loginPage.loginWrongPassword3Times();
        assertNotNull(captcha);
    }*//*

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
    public void searchProject() {
        loginPage.loginSuccessful();
        String actualProject = dashBoardPage.searchProject("Main Testing", "MTP");
        assertEquals("Main Testing Project", actualProject);
    }*/

    /*@ParameterizedTest
    @CsvSource({"TOUCAN, Task"})
    public void testCreateIssue(String project, String issueType) throws InterruptedException {
        loginPage.loginSuccessful();
        dashBoardPage.getCreateIssueButton().click();
        String result = createIssuePage.createNewIssue(project, issueType, "randomString");
        boolean resultActual = createIssuePage.compare(result, project);
        dashBoardPage.deleteIssue(result);
        assertTrue(resultActual);
    }*/

    /*@ParameterizedTest
    @MethodSource("createStreamOfIssueType")
    public void testCreateIssueWithIssueType(String project, String issueType) throws InterruptedException {
        loginPage.loginSuccessful();
        dashBoardPage.getCreateIssueButton().click();
        String issueId = createIssuePage.createNewIssue(project, issueType, "randomString");
        dashBoardPage.searchForIssueCreatedByMe(issueId);

        //boolean resultActual = createIssuePage.compare(result, project);
        dashBoardPage.deleteIssue(issueId);
        assertTrue(resultActual);
    }*/


    @ParameterizedTest
    @MethodSource("createListOfIssueType")
    public void testIssueTypeOfProject(String project, String issueType) throws InterruptedException {
        loginPage.loginSuccessful();
        dashBoardPage.getCreateIssueButton().click();
        String issueId = createIssuePage.createNewIssue(project, issueType, "randomString");
        String actualIssueType = dashBoardPage.getIssueTypeByIssueId(issueId);
        dashBoardPage.deleteIssueByIssueId(issueId);
        assertEquals(issueType, actualIssueType);
    }


    private static List<Arguments> createListOfIssueType() {
        List<String> issueTypes = Arrays.asList(" Story", "Task", "Bug", "Sub-task");
        List<String> projects = Arrays.asList("TOUCAN", "COALA", "JETI");
        List<Arguments> argumentsList = new ArrayList<>();

        for (String project : projects) {
            for (String type : issueTypes) {
                argumentsList.add(Arguments.of(project, type));
            }
        }
        return argumentsList;
    }
}

package com.codecool;

import com.codecool.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    static LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    AlternateLogin alternateLogin = new AlternateLogin();
    IssuesPage issuesPage = new IssuesPage();
    CreateIssuePage createIssuePage = new CreateIssuePage();


    @BeforeAll
    public static void login(){
        loginPage.loginSuccessful();
    }

    //not working if I comment out loginPage.loginSuccessful(); from the code
    @Test
    public void testLogout() {
        //loginPage.loginSuccessful();
        WebElement logoutConfirmation = dashBoardPage.logout();
        Assertions.assertNotNull(logoutConfirmation);
    }

    @ParameterizedTest
    @CsvSource({"TOUCAN projekt, TOUCAN",
                "COALA Project, COALA",
                "JETI Project, JETI"})
    public void testBrowseProject(String expected, String project){
        String projectName = dashBoardPage.browseProject(project);
        assertEquals(expected, projectName);
    }

    @Test
    public void searchProject() {
        String actualProject = dashBoardPage.searchProject("Main Testing", "MTP");
        assertEquals("Main Testing Project", actualProject);
    }


    /*@ParameterizedTest
    @CsvSource({"TOUCAN, Task",
            "COALA, Sub-task"})
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

}

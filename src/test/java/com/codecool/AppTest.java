package com.codecool;

import com.codecool.pages.AlternateLogin;
import com.codecool.pages.CreateIssuePage;
import com.codecool.pages.DashBoardPage;
import com.codecool.pages.LoginPage;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebElement;

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


    @BeforeEach
    public void login(){
        loginPage.loginSuccessful();
    }

    //not working if I comment out loginPage.loginSuccessful(); from the code
    @Test
    public void testLogout(){
        loginPage.loginSuccessful();
        WebElement logoutConfirmation = dashBoardPage.logout();
        Assertions.assertNotNull(logoutConfirmation);
    }
    /*
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


    @ParameterizedTest
    @MethodSource("createListOfIssueType")
    public void testIssueTypeOfProject(String project, String issueType) throws InterruptedException {
        //loginPage.loginSuccessful();
        dashBoardPage.getCreateIssueButton().click();
        String issueId = createIssuePage.createNewIssue(project, issueType, "randomString");
        String actualIssueType = dashBoardPage.getIssueTypeByIssueId(issueId);
        dashBoardPage.deleteIssueByIssueId(issueId);
        assertEquals(issueType, actualIssueType);
    }


    private static List<Arguments> createListOfIssueType() {
        List<String> issueTypes = Arrays.asList("Story");
        List<String> projects = Arrays.asList("TOUCAN");
        List<Arguments> argumentsList = new ArrayList<>();

        for (String project : projects) {
            for (String type : issueTypes) {
                argumentsList.add(Arguments.of(project, type));
            }
        }
        return argumentsList;
    }
}

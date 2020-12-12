package com.codecool;

import com.codecool.pages.CreateIssuePage;
import com.codecool.pages.DashBoardPage;
import com.codecool.pages.IssuesPage;
import com.codecool.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestIssues {
    private static final LoginPage loginPage = new LoginPage();
    private final DashBoardPage dashBoardPage = new DashBoardPage();
//    private final CreateIssuePage createIssuePage = new CreateIssuePage();
    private final IssuesPage issuesPage = new IssuesPage();

    @BeforeAll
    public static void login() {
        loginPage.loginSuccessful();
    }
    
//    @Test
//    public void createIssue() {
//        Assertions.assertTrue(true);
//    }

    @ParameterizedTest
    @CsvSource({"TOUCAN, Task"})
    public void testCreateIssue(String project, String issueType) throws InterruptedException {
//        dashBoardPage.getCreateIssueButton().click();
//        String result = createIssuePage.createNewIssue(project, issueType, "randomString");
        String result = issuesPage.createIssue(project, issueType, "randomString");
        boolean resultActual = issuesPage.compare(result, project);
        dashBoardPage.deleteIssue(result);
        assertTrue(resultActual);
    }
}

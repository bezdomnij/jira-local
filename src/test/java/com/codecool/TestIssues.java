package com.codecool;

import com.codecool.pages.DashBoardPage;
import com.codecool.pages.IssuesPage;
import com.codecool.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestIssues {
    private static final LoginPage loginPage = new LoginPage();
    private static final DashBoardPage dashBoardPage = new DashBoardPage();
    private final IssuesPage issuesPage = new IssuesPage();

    @BeforeAll
    public static void login() {
        loginPage.loginSuccessful();
    }

    @ParameterizedTest
    @CsvSource({"TOUCAN, Task"})
    public void testCreateIssue(String project, String issueType) throws InterruptedException {
        String result = issuesPage.createIssue(project, issueType, "randomString");
        boolean resultActual = issuesPage.compare(result, project);
        dashBoardPage.deleteIssue(result);
        assertTrue(resultActual);
    }

    @AfterAll
    public static void backToBase() {
        dashBoardPage.logout();
    }
}

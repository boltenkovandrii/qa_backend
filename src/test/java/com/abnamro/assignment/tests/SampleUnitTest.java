package com.abnamro.assignment.tests;

import com.abnamro.assignment.model.Issue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.abnamro.assignment.base.IssuesAPI.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("UnitTest")
public class SampleUnitTest extends BaseTest {

    //Note: This is just a sample - not part of the assignment

    @Test
    @DisplayName("TC1: Sample test case")
    void sampleTestMethod() {
        assertTrue(true);
    }



    @Test
    @Tag("UnitTest")
    @DisplayName("Get issue")
    public void getIssueTest() {
        Issue issue = getIssue(1);
        assertThat(issue).as("Check that issue is not null").isNotNull();
        assertThat(issue).as("Check the issue id").extracting(Issue::id).isEqualTo(200882250L);
    }


    @Test
    @Tag("UnitTest")
    @DisplayName("Check Issue list")
    public void getIssuesTest() {
        List<Issue> issues = getIssues();
        assertThat(issues).as("Check that issues  response is not null").isNotNull();
        assertThat(issues).as("Check the issue id").hasSize(1);
    }



}

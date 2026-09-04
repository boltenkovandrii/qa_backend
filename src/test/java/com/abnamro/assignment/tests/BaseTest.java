package com.abnamro.assignment.tests;

import com.abnamro.assignment.base.TestConfig;
import org.apache.commons.configuration2.CompositeConfiguration;

public class BaseTest {
    private static final CompositeConfiguration config = TestConfig.getConfiguration();
    protected static final String TEST_ISSUE_PREFIX = config.getString("TEST_ISSUE_PREFIX");
    protected static final long PROJECT_ID = config.getLong("GITLAB_PROJECT_ID");


    protected String generateUniqueIssueTitle(String baseTitle) {
        long timestamp = System.currentTimeMillis();
        return TEST_ISSUE_PREFIX + " " + baseTitle + " " + timestamp;
    }
}

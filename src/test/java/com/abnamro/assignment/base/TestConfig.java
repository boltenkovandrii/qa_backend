package com.abnamro.assignment.base;


import org.apache.commons.configuration2.*;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestConfig {
    private final CompositeConfiguration config;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestConfig.class);

    private static class Holder {
        static final TestConfig INSTANCE = new TestConfig();
    }

    public static CompositeConfiguration getConfiguration() {
        return Holder.INSTANCE.config;
    }

    private TestConfig(){
        LOGGER.info("Initializing TestConfig and loading configuration parameters");
        config =  new CompositeConfiguration();

        //general parameters for test execution can be added here, like number of threads, retry attempts, etc.
        try {
            Configurations configs = new Configurations();
            config.addConfiguration(configs.properties("src/test/resources/config.properties"));
        } catch (ConfigurationException e) {
            throw new IllegalStateException("Failed to load test configuration", e);
        }

        // Load gitlab.properties file if it exists, otherwise log a warning
        loadGitlabConfiguration();

        // Add system and environment configurations after loading the properties file
        config.addConfiguration(new SystemConfiguration());
        config.addConfiguration(new EnvironmentConfiguration());
    }


    private void loadGitlabConfiguration(){
        // Load gitlab.properties file if it exists, otherwise log a warning
        try {
            Configurations configs = new Configurations();
            config.addConfiguration(configs.properties("src/test/resources/gitlab.properties"));
        } catch (ConfigurationException e) {
            LOGGER.warn("Failed to load gitlab.properties file. Not a problem for CI setup - environment variables should be used in this case. Make sure that GITLAB_ACCESS_TOKEN variable is configured for the project in GitHub", e);
        }
    }

}
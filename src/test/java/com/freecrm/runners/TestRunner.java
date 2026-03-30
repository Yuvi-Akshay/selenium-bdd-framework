package com.freecrm.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.freecrm.stepdefinitions", "com.freecrm.hooks"},
    plugin = {
                "pretty",
                "html:target/cucumber-reports.html"
        },
    monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests{
    
}

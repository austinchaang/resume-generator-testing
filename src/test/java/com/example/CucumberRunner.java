package com.example;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = "com.example", plugin = { "pretty",
                "html:target/cucumber-reports" })
public class CucumberRunner extends AbstractTestNGCucumberTests {
}

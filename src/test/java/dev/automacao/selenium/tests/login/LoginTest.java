package dev.automacao.selenium.tests.login;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features/Login.feature",
glue = {""},
monochrome = true
,dryRun = true)
public class LoginTest {
    
}

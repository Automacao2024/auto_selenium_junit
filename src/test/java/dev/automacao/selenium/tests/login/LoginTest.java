package dev.automacao.selenium.tests.login;

import dev.automacao.selenium.pages.LoginPage;
import dev.automacao.selenium.utils.TesteRuler;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features/Login.feature",
glue = {""},
monochrome = true
,dryRun = true)
public class LoginTest {

    @Rule
    public TesteRuler testRule = new TesteRuler();

    @Test
    public void testLoginComSucesso() {
        WebDriver driver = testRule.getDriver();
        driver.get("url_do_sistema");

        LoginPage loginPage = new LoginPage(driver);
        LoginPage homePage = loginPage.realizarLogin("username", "password");

        assertTrue(homePage.isPaginaInicialVisible());
    }

}

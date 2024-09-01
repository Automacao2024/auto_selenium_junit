package dev.automacao.selenium;

import dev.automacao.selenium.utils.TesteRuler;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import dev.automacao.selenium.pages.LoginPage;

import static org.junit.Assert.assertTrue;

class LoginTest {

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

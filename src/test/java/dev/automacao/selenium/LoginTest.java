package dev.automacao.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import dev.automacao.selenium.pages.LoginPage;

class LoginTest {

	private WebDriver driver;
	private LoginPage loginPage;

	@BeforeEach
	void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@AfterEach
	void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	void test() {
		
		loginPage.signin();

		WebElement dashboard = driver.findElement(By.tagName("h6"));
		String texto= dashboard.getText();
		
		Assertions.assertTrue(texto.equals("Dashboard"));
	}

}

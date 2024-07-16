package dev.automacao.selenium.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
	//Locators
	private By username = By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input");
	private By password = By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input");
	private By btnLogin = By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button");
	private By dashboad = By.tagName("h6");
	
	
	public void signin() {
		if(super.isDisplayed(username)) {
			super.type("Admin", username);
			super.type("admin123", password);
			super.click(btnLogin);
		} else {
			System.out.println("Usuario inexistente");
		}
	}
	
	public String getMyAccountMessage() {
		return super.getText(dashboad);
	}

}

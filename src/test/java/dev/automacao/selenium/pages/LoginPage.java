package dev.automacao.selenium.pages;

import dev.automacao.selenium.elements.LoginElementMaps;
import dev.automacao.selenium.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends LoginElementMaps {

    private LoginElementMaps loginElements;
    private ConfigReader config;

    public LoginPage(WebDriver driver) {
        super(driver);
        loginElements = new LoginElementMaps();
        PageFactory.initElements(driver, loginElements);
        config = new ConfigReader(); // Inicializa o ConfigReader
    }

    public LoginPage realizarLogin(){
        String username = config.getProperty("usuario.nome"); // Obtém o nome de usuário do arquivo de propriedades
        String password = config.getProperty("usuario.senha"); // Obtém a senha do arquivo de propriedades

        loginElements.username.sendKeys(username); // Preenche o campo de usuário
        loginElements.password.sendKeys(password); // Preenche o campo de senha
        loginElements.btnLogin.click(); // Clica no botão de login

        return new LoginPage(driver); // Retorna uma nova instância da HomePage após o loginv
    }
}

package dev.automacao.selenium.elements;

import dev.automacao.selenium.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginElementMaps extends BasePage {

    @FindBy(name = "username")
    protected WebElement username;

    @FindBy(name = "password")
    protected WebElement password;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")
    protected WebElement btnLogin;

    public LoginElementMaps(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this); // Inicialização dos elementos
    }

    public LoginElementMaps() {
        super();
    }
}

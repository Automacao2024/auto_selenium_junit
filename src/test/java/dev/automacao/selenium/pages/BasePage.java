package dev.automacao.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import java.util.List;

public class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Construtor que inicializa o WebDriver e o WebDriverWait
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10); // Timeout de 10 segundos
    }

    // Encontra um elemento na página
    private WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    // Clica no elemento localizado pelo locator
    public void click(By locator) {
        findElement(locator).click();
    }

    // Envia texto para o campo de entrada localizado pelo locator
    public void sendKeys(By locator, String keysToSend) {
        findElement(locator).sendKeys(keysToSend);
    }

    // Obtém o texto de um elemento localizado pelo locator
    public String getText(By locator) {
        return findElement(locator).getText();
    }

    // Espera até que o elemento esteja visível
    public void waitForElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Espera até que o elemento seja clicável
    public void waitForElementClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Espera até que o elemento desapareça
    public void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Clica no elemento usando JavaScript
    public void clickUsingJavaScript(By locator) {
        WebElement element = findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    // Define o valor de um campo de entrada usando JavaScript
    public void setInputValueByJs(By locator, String value) {
        WebElement element = findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", element, value);
    }

    // Adiciona uma classe CSS a um elemento
    public void addCssClass(By locator, String cssClass) {
        WebElement element = findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].classList.add(arguments[1]);", element, cssClass);
    }

    // Remove uma classe CSS de um elemento
    public void removeCssClass(By locator, String cssClass) {
        WebElement element = findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].classList.remove(arguments[1]);", element, cssClass);
    }

    // Obtém o valor de um estilo CSS diretamente
    public String getCssStyle(By locator, String property) {
        WebElement element = findElement(locator);
        return element.getCssValue(property);
    }

    // Aceita um alerta e retorna o texto
    public String acceptAlertAndGetText() {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    // Rejeita um alerta e retorna o texto
    public String dismissAlertAndGetText() {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.dismiss();
        return alertText;
    }

    // Verifica se um alerta está presente e retorna um booleano
    public boolean isConfirmationDialogPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept(); // Aceitar para garantir que a janela seja fechada
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    // Verifica se o texto de um elemento é igual ao texto esperado
    public void validateText(By locator, String expectedText) {
        String actualText = getText(locator);
        if (!actualText.equals(expectedText)) {
            throw new AssertionError("Texto esperado: '" + expectedText + "' mas foi encontrado: '" + actualText + "'");
        }
    }

    // Verifica se o texto de um elemento contém o texto esperado
    public void validateTextContains(By locator, String expectedText) {
        String actualText = getText(locator);
        if (!actualText.contains(expectedText)) {
            throw new AssertionError("Texto esperado para conter: '" + expectedText + "' mas foi encontrado: '" + actualText + "'");
        }
    }

    // Verifica se o valor de um atributo de um elemento é igual ao valor esperado
    public void validateAttribute(By locator, String attributeName, String expectedValue) {
        WebElement element = findElement(locator);
        String actualValue = element.getAttribute(attributeName);
        if (!actualValue.equals(expectedValue)) {
            throw new AssertionError("Valor do atributo '" + attributeName + "' esperado: '" + expectedValue + "' mas foi encontrado: '" + actualValue + "'");
        }
    }

    // Verifica se o valor de um atributo de um elemento contém o valor esperado
    public void validateAttributeContains(By locator, String attributeName, String expectedValue) {
        WebElement element = findElement(locator);
        String actualValue = element.getAttribute(attributeName);
        if (!actualValue.contains(expectedValue)) {
            throw new AssertionError("Valor do atributo '" + attributeName + "' esperado para conter: '" + expectedValue + "' mas foi encontrado: '" + actualValue + "'");
        }
    }

    // Verifica se um elemento está visível na página
    public void validateElementVisible(By locator) {
        WebElement element = findElement(locator);
        if (!element.isDisplayed()) {
            throw new AssertionError("Elemento localizado por '" + locator + "' não está visível.");
        }
    }

    // Verifica se um elemento está presente na página
    public void validateElementPresent(By locator) {
        try {
            findElement(locator);
        } catch (NoSuchElementException e) {
            throw new AssertionError("Elemento localizado por '" + locator + "' não está presente na página.");
        }
    }

    // Verifica se um elemento não está presente na página
    public void validateElementNotPresent(By locator) {
        try {
            findElement(locator);
            throw new AssertionError("Elemento localizado por '" + locator + "' não deveria estar presente na página.");
        } catch (NoSuchElementException e) {
            // Elemento não está presente, o que é o esperado
        }
    }

    // Verifica se um elemento está habilitado
    public void validateElementEnabled(By locator) {
        WebElement element = findElement(locator);
        if (!element.isEnabled()) {
            throw new AssertionError("Elemento localizado por '" + locator + "' não está habilitado.");
        }
    }

    // Verifica se um elemento está desabilitado
    public void validateElementDisabled(By locator) {
        WebElement element = findElement(locator);
        if (element.isEnabled()) {
            throw new AssertionError("Elemento localizado por '" + locator + "' não está desabilitado.");
        }
    }

    // Verifica se o valor de uma propriedade CSS de um elemento é igual ao valor esperado
    public void validateCssProperty(By locator, String propertyName, String expectedValue) {
        String actualValue = getCssStyle(locator, propertyName);
        if (!actualValue.equals(expectedValue)) {
            throw new AssertionError("Valor da propriedade CSS '" + propertyName + "' esperado: '" + expectedValue + "' mas foi encontrado: '" + actualValue + "'");
        }
    }

    // Verifica se o valor de uma propriedade CSS de um elemento contém o valor esperado
    public void validateCssPropertyContains(By locator, String propertyName, String expectedValue) {
        String actualValue = getCssStyle(locator, propertyName);
        if (!actualValue.contains(expectedValue)) {
            throw new AssertionError("Valor da propriedade CSS '" + propertyName + "' esperado para conter: '" + expectedValue + "' mas foi encontrado: '" + actualValue + "'");
        }
    }

    // Obtém o número de linhas de uma tabela
    public int getTableRowCount(By tableLocator) {
        WebElement table = findElement(tableLocator);
        return table.findElements(By.tagName("tr")).size();
    }

    // Obtém o número de colunas de uma tabela
    public int getTableColumnCount(By tableLocator) {
        WebElement table = findElement(tableLocator);
        WebElement firstRow = table.findElement(By.tagName("tr"));
        return firstRow.findElements(By.tagName("td")).size();
    }

    // Obtém o texto de uma célula específica de uma tabela
    public String getCellText(By tableLocator, int rowIndex, int columnIndex) {
        WebElement table = findElement(tableLocator);
        WebElement row = table.findElements(By.tagName("tr")).get(rowIndex);
        WebElement cell = row.findElements(By.tagName("td")).get(columnIndex);
        return cell.getText();
    }

    // Verifica se um texto está presente em qualquer célula de uma tabela
    public boolean isTextPresentInTable(By tableLocator, String text) {
        WebElement table = findElement(tableLocator);
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                if (cell.getText().equals(text)) {
                    return true;
                }
            }
        }
        return false;
    }
}


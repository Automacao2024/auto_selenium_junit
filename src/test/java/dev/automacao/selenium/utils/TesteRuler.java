package dev.automacao.selenium.utils;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TesteRuler extends TestWatcher {

    private WebDriver driver;
    private EvidenceManager evidenceManager;
    private String screenshotsDirectory;

    public TesteRuler() {
        evidenceManager = new EvidenceManager();
        screenshotsDirectory = evidenceManager.getEvidencePath();
    }

    @Override
    protected void starting(Description description) {
        // Inicializa o WebDriver antes de cada teste
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Inicia a janela maximizada
        driver = new ChromeDriver(options);
    }

    @Override
    protected void failed(Throwable e, Description description) {
        // Captura uma screenshot se o teste falhar
        String screenshotName = description.getMethodName() + "_" + getTimestamp() + ".png";
        takeScreenshot(screenshotName);
    }

    @Override
    protected void finished(Description description) {
        // Fecha o WebDriver após cada teste
        if (driver != null) {
            driver.quit();
        }
    }

    // Método para obter o WebDriver
    public WebDriver getDriver() {
        return driver;
    }

    // Método para capturar screenshots
    private void takeScreenshot(String screenshotName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path destination = Paths.get(screenshotsDirectory, screenshotName);
        try {
            Files.createDirectories(destination.getParent()); // Garante que o diretório exista
            Files.copy(screenshot.toPath(), destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para gerar um timestamp para os nomes das screenshots
    private String getTimestamp() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    }
}

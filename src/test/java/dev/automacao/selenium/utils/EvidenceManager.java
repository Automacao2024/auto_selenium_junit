package dev.automacao.selenium.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EvidenceManager {

    private ConfigReader config;

    public EvidenceManager() {
        config = new ConfigReader();
    }

    // Obtém o caminho das evidências do arquivo de propriedades
    public String getEvidencePath() {
        return config.getProperty("caminho.evidencias");
    }

    // Cria o diretório de evidências se não existir
    public void createEvidenceDirectory() {
        String path = getEvidencePath();
        File evidenceDir = new File(path);
        if (!evidenceDir.exists()) {
            evidenceDir.mkdirs();
        }
    }

    // Salva uma evidência de captura de tela
    public void saveScreenshot(WebDriver driver, String screenshotName) {
        createEvidenceDirectory(); // Garante que o diretório existe
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path destination = Paths.get(getEvidencePath(), screenshotName + ".png");
        try {
            Files.copy(screenshot.toPath(), destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

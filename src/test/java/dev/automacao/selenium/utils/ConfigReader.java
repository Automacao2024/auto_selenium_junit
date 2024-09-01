package dev.automacao.selenium.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;

    // Construtor que carrega o arquivo de propriedades
    public ConfigReader() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Falha ao carregar o arquivo de propriedades");
        }
    }

    // Método para obter o valor de uma propriedade
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

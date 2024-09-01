# Projeto de Automação com Selenium Grid e Testes

Este documento fornece uma visão geral detalhada sobre o desenvolvimento e a configuração de automação de testes usando Selenium Grid, Java, Spring Boot, PostgreSQL e outras tecnologias discutidas. Inclui a configuração do Selenium Grid, a criação de testes automatizados, e a configuração de ambientes de teste.

## Índice

1. [Visão Geral do Projeto](#visão-geral-do-projeto)
2. [Configuração do Selenium Grid](#configuração-do-selenium-grid)
    - [Instalação do Java](#instalação-do-java)
    - [Download do Selenium Server](#download-do-selenium-server)
    - [Configuração do Hub](#configuração-do-hub)
    - [Configuração do Node](#configuração-do-node)
    - [Configuração Avançada do Node (Opcional)](#configuração-avançada-do-node-opcional)
    - [Automatização da Inicialização](#automatização-da-inicialização)
3. [Configuração do Projeto de Testes](#configuração-do-projeto-de-testes)
    - [Estrutura do Projeto](#estrutura-do-projeto)
    - [Testes com Selenium e Java](#testes-com-selenium-e-java)
    - [Criação de Page Objects](#criação-de-page-objects)
    - [Implementação de DSL (Domain-Specific Language)](#implementação-de-dsl-domain-specific-language)
    - [Integração com Cucumber](#integração-com-cucumber)
4. [Configuração do Ambiente de Testes](#configuração-do-ambiente-de-testes)
    - [Arquivo `properties`](#arquivo-properties)
    - [Configuração de Logs e Evidências](#configuração-de-logs-e-evidências)
5. [Referências e Recursos](#referências-e-recursos)

## Visão Geral do Projeto

Este projeto inclui a configuração e execução de testes automatizados utilizando Selenium Grid para distribuição e paralelização dos testes, bem como a configuração de testes para sistemas desenvolvidos com Java, Spring Boot, PostgreSQL e outras tecnologias. A automação inclui a criação de testes para páginas de login, funcionalidades específicas e integração com o Cucumber para testes de aceitação.

## Configuração do Selenium Grid

### Instalação do Java

O Selenium Grid requer o Java para ser executado. Instale o Java com os seguintes comandos:

```bash
sudo apt update
sudo apt install openjdk-11-jdk
```
Download do Selenium Server
Baixe o arquivo JAR do Selenium Server: 

```bash
wget https://selenium-release.storage.googleapis.com/4.20.0/selenium-server-4.20.0.jar
``` 
Configuração do Hub
Inicie o Hub do Selenium Grid com o seguinte comando:

```bash
java -jar selenium-server-4.20.0.jar hub
```
O Hub estará disponível em http://localhost:4444.

Configuração do Node
Para adicionar um Node, execute o seguinte comando (em uma máquina diferente ou no mesmo servidor):

```bash
java -jar selenium-server-4.20.0.jar node --hub http://localhost:4444/grid/register
```
Configuração Avançada do Node (Opcional)
Crie um arquivo nodeConfig.json para personalizar as capacidades do Node:

```bash
{
  "capabilities": [
    {
      "browserName": "chrome",
      "maxInstances": 5,
      "seleniumProtocol": "WebDriver"
    },
    {
      "browserName": "firefox",
      "maxInstances": 5,
      "seleniumProtocol": "WebDriver"
    }
  ],
  "configuration": {
    "nodeTimeout": 120,
    "hub": "http://localhost:4444/grid/register",
    "maxSession": 5,
    "port": 5555,
    "register": true,
    "registerCycle": 5000,
    "unregisterIfStillDownAfter": 10000,
    "hubPort": 4444,
    "hubHost": "localhost"
  }
}
``` 
Inicie o Node com o arquivo de configuração:

````bash
java -jar selenium-server-4.20.0.jar node --node-config nodeConfig.json
```
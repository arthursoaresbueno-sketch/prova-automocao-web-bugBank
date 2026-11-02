# Prova Automação Web – BugBank

Automação do fluxo de transferência bancária do [BugBank](https://bugbank.netlify.app/),
feita com **Java + Selenium + JUnit 5 + Allure**, no mesmo padrão do projeto visto em aula
(Page Objects, Tasks, Validação e TestCases).

## O que o teste faz

1. Acessa `https://bugbank.netlify.app/`
2. Cadastra **duas contas** com a opção "Criar conta com saldo" (dados gerados com Datafaker)
3. Entra na **conta 1** e transfere um valor aleatório para a **conta 2**
4. Valida o saldo da conta 1 na home e no **extrato** (saldo e transferência listada)
5. Sai, entra na **conta 2** e valida no **extrato** que o valor foi recebido

Os dados das contas (e-mail, senha, número da conta, valor, descrição) ficam em
`src/main/resources/Properties/conta.properties` durante a execução.

## Estrutura

```
src/main/java/Framework      -> driver, waits, TestBase, utilitários (Faker, Moeda, screenshot)
src/main/resources/Properties-> url e dados das contas
src/test/java/PageObjects    -> elementos de cada tela
src/test/java/Tasks          -> ações (cadastrar, login, transferir, extrato)
src/test/java/Validacao      -> asserts + prints no Allure
src/test/java/TestCases      -> TransferenciaBancariaTest
```

## Como rodar

Pré-requisitos: JDK (a versão do `pom.xml`, propriedade `maven.compiler.source`), Maven e Google Chrome.

```
mvn clean test
```

Relatório Allure:

```
mvn allure:serve
```

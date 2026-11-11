# Prova de Automação Web – BugBank

Projeto de automação de testes do sistema BugBank, desenvolvido com Java, Selenium, JUnit 5 e Allure.

## O que o teste realiza

1. Acessa o site BugBank.
2. Cadastra duas contas com saldo.
3. Realiza uma transferência entre as contas.
4. Verifica o saldo e o extrato da conta de origem.
5. Verifica o recebimento da transferência na conta de destino.

## Tecnologias utilizadas

- Java
- Selenium
- JUnit 5
- Maven
- Allure
- Datafaker

## Como executar

É necessário ter JDK, Maven e Google Chrome instalados.

Para executar os testes:

```bash
mvn clean test
```

Para visualizar o relatório Allure:

```bash
mvn allure:serve
```

**Site utilizado:** https://bugbank.netlify.app/
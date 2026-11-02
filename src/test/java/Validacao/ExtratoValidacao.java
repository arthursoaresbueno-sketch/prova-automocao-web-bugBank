package Validacao;

import Framework.Utils.Moeda;
import Framework.Utils.TakeScreenshot;
import PageObjects.ExtratoPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class ExtratoValidacao {

    private WebDriver driver;
    private ExtratoPage extratoPage;

    public ExtratoValidacao (WebDriver driver){

        this.driver = driver;
        extratoPage = new ExtratoPage(this.driver);
    }

    public void validaSaldoDisponivel(double saldoEsperado, String nomePrint){

        String esperado = Moeda.formatar(saldoEsperado);

        try {
            String texto = Moeda.normalizar(extratoPage.getTextSaldoDisponivel().getText());
            Assertions.assertTrue(texto.contains(esperado),
                    "Saldo do extrato esperado " + esperado + " mas a tela mostrou: '" + texto + "'");
            TakeScreenshot.screenshotPage(driver,nomePrint);
        } catch (Exception e) {
            TakeScreenshot.screenshotPage(driver,nomePrint);
            throw e;
        }
    }

    // Procura no extrato a transferência pela descrição (única) e pelo valor
    public void validaTransacao(String descricao, double valor, String nomePrint){

        String valorFormatado = Moeda.formatarSemSimbolo(valor);

        try {
            String corpo = Moeda.normalizar(extratoPage.getCorpoExtrato().getText());
            Assertions.assertTrue(corpo.contains(descricao),
                    "A descrição '" + descricao + "' não apareceu no extrato");
            Assertions.assertTrue(corpo.contains(valorFormatado),
                    "O valor " + valorFormatado + " não apareceu no extrato");
            TakeScreenshot.screenshotPage(driver,nomePrint);
        } catch (Exception e) {
            TakeScreenshot.screenshotPage(driver,nomePrint);
            throw e;
        }
    }
}

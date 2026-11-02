package Tasks;

import Framework.Utils.FileOperations;
import PageObjects.GenericPage;
import PageObjects.HomePage;
import Validacao.ExtratoValidacao;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class ExtratoTask {

    private WebDriver driver;
    private HomePage homePage;
    private GenericPage genericPage;
    private ExtratoValidacao extratoValidacao;

    public ExtratoTask (WebDriver driver){

        this.driver = driver;
        homePage = new HomePage(this.driver);
        genericPage = new GenericPage(this.driver);
        extratoValidacao = new ExtratoValidacao(this.driver);
    }

    // Conta que enviou: saldo = 1000 - valor, e a transferência aparece no extrato
    public void validarExtratoContaOrigem(){

        Properties conta = FileOperations.getProperties("conta");
        double valor = Double.parseDouble(conta.getProperty("valor"));

        homePage.getExtratoButton().click();
        extratoValidacao.validaSaldoDisponivel(TransferenciaTask.SALDO_INICIAL - valor, "Extrato conta origem - saldo");
        extratoValidacao.validaTransacao(conta.getProperty("descricao"), valor, "Extrato conta origem - transferencia");
        genericPage.getVoltarButton().click();
    }

    // Conta que recebeu: saldo = 1000 + valor, e a transferência aparece no extrato
    public void validarExtratoContaDestino(){

        Properties conta = FileOperations.getProperties("conta");
        double valor = Double.parseDouble(conta.getProperty("valor"));

        homePage.getExtratoButton().click();
        extratoValidacao.validaSaldoDisponivel(TransferenciaTask.SALDO_INICIAL + valor, "Extrato conta destino - saldo");
        extratoValidacao.validaTransacao(conta.getProperty("descricao"), valor, "Extrato conta destino - transferencia");
        genericPage.getVoltarButton().click();
    }
}

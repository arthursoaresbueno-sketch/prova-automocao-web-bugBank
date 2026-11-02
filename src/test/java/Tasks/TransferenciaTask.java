package Tasks;

import Framework.Utils.FakersGeneration;
import Framework.Utils.FileOperations;
import PageObjects.GenericPage;
import PageObjects.HomePage;
import PageObjects.TransferenciaPage;
import Validacao.GenericValidacao;
import Validacao.HomeValidacao;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class TransferenciaTask {

    // Saldo que o BugBank dá quando a conta é criada "com saldo"
    public static final double SALDO_INICIAL = 1000.00;

    private WebDriver driver;
    private HomePage homePage;
    private TransferenciaPage transferenciaPage;
    private GenericPage genericPage;
    private GenericValidacao genericValidacao;
    private HomeValidacao homeValidacao;
    private FakersGeneration fakersGeneration;

    public TransferenciaTask (WebDriver driver){

        this.driver = driver;
        homePage = new HomePage(this.driver);
        transferenciaPage = new TransferenciaPage(this.driver);
        genericPage = new GenericPage(this.driver);
        genericValidacao = new GenericValidacao(this.driver);
        homeValidacao = new HomeValidacao(this.driver);
        fakersGeneration = new FakersGeneration();
    }

    // Confere o saldo inicial, transfere da conta logada para a conta 2 e confere o saldo final.
    public void transferirParaConta2(){

        Properties conta = FileOperations.getProperties("conta");

        int valor = fakersGeneration.getValorTransferencia();
        String descricao = fakersGeneration.getDescricao();

        FileOperations.setProperties("conta", "valor", String.valueOf(valor));
        FileOperations.setProperties("conta", "descricao", descricao);

        homeValidacao.validaSaldo(SALDO_INICIAL, "Saldo antes da transferencia");

        homePage.getTransferenciaButton().click();

        transferenciaPage.getNumeroContaInput().sendKeys(conta.getProperty("numero2"));
        transferenciaPage.getDigitoInput().sendKeys(conta.getProperty("digito2"));
        transferenciaPage.getValorInput().sendKeys(String.valueOf(valor));
        transferenciaPage.getDescricaoInput().sendKeys(descricao);
        transferenciaPage.getTransferirAgoraButton().click();

        genericValidacao.validationModal("sucesso", "Transferencia realizada");
        genericPage.getModalCloseButton().click();
        genericPage.getVoltarButton().click();

        homeValidacao.validaSaldo(SALDO_INICIAL - valor, "Saldo depois da transferencia");
    }
}

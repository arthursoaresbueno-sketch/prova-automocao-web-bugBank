package Tasks;

import Framework.Browser.Waits;
import Framework.Utils.FakersGeneration;
import Framework.Utils.FileOperations;
import PageObjects.CadastroPage;
import PageObjects.GenericPage;
import PageObjects.LoginPage;
import Validacao.GenericValidacao;
import org.openqa.selenium.WebDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroTask {

    private WebDriver driver;
    private LoginPage loginPage;
    private CadastroPage cadastroPage;
    private GenericPage genericPage;
    private GenericValidacao genericValidacao;
    private FakersGeneration fakersGeneration;
    private Waits waits;

    public CadastroTask (WebDriver driver){

        this.driver = driver;
        loginPage = new LoginPage(this.driver);
        cadastroPage = new CadastroPage(this.driver);
        genericPage = new GenericPage(this.driver);
        genericValidacao = new GenericValidacao(this.driver);
        fakersGeneration = new FakersGeneration();
        waits = new Waits(this.driver);
    }

    // Cadastra uma conta com saldo e guarda os dados no conta.properties.
    // "numero" é 1 ou 2 e serve só para separar os dados das duas contas.
    public void cadastrarContaComSaldo(int numero){

        String email = fakersGeneration.getEmail();
        String nome = fakersGeneration.getNome();
        String senha = fakersGeneration.getSenha();

        loginPage.getRegistrarButton().click();

        waits.digitar(cadastroPage.getEmailInput(), email);
        waits.digitar(cadastroPage.getNomeInput(), nome);
        waits.digitar(cadastroPage.getSenhaInput(), senha);
        waits.digitar(cadastroPage.getConfirmaSenhaInput(), senha);
        if (numero == 1) {
            waits.clicar(cadastroPage.getCriarContaComSaldoToggle());
        }
        waits.clicar(cadastroPage.getCadastrarButton());


        genericValidacao.validationModal("criada com sucesso", "Cadastro conta " + numero);

        // O modal diz: "A conta 123-4 foi criada com sucesso"
        String textoModal = genericPage.getModalText().getText();
        Matcher matcher = Pattern.compile("(\\d+)-(\\d+)").matcher(textoModal);
        if (!matcher.find()) {
            throw new IllegalStateException("Nao encontrei o numero da conta no texto do modal: " + textoModal);
        }

        FileOperations.setProperties("conta", "email" + numero, email);
        FileOperations.setProperties("conta", "nome" + numero, nome);
        FileOperations.setProperties("conta", "senha" + numero, senha);
        FileOperations.setProperties("conta", "numero" + numero, matcher.group(1));
        FileOperations.setProperties("conta", "digito" + numero, matcher.group(2));

        genericPage.getModalCloseButton().click();
    }
}

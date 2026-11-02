package Tasks;

import Framework.Browser.Waits;
import Framework.Utils.FileOperations;
import PageObjects.GenericPage;
import PageObjects.LoginPage;
import Validacao.LoginValidacao;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class LoginTask {

    private WebDriver driver;
    private LoginPage loginPage;
    private GenericPage genericPage;
    private LoginValidacao loginValidacao;
    private Waits waits;

    public LoginTask (WebDriver driver){

        this.driver = driver;
        loginPage = new LoginPage(this.driver);
        genericPage = new GenericPage(this.driver);
        loginValidacao = new LoginValidacao(this.driver);
        waits = new Waits(this.driver);

    }

    // Entra com os dados da conta 1 ou 2 (gravados no cadastro)
    public void efetuarLogin(int numero){

        Properties conta = FileOperations.getProperties("conta");

        waits.digitar(loginPage.getEmailInput(), conta.getProperty("email" + numero));
        waits.digitar(loginPage.getPasswordInput(), conta.getProperty("senha" + numero));
        waits.clicar(loginPage.getAcessarButton());

        loginValidacao.loginComSucesso(conta.getProperty("nome" + numero));
    }

    public void sair(){

        genericPage.getSairButton().click();
        loginValidacao.telaDeLogin();
    }
}

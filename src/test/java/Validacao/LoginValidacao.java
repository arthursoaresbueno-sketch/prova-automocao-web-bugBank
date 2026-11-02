package Validacao;

import Framework.Utils.Moeda;
import Framework.Utils.TakeScreenshot;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class LoginValidacao {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    public LoginValidacao (WebDriver driver){

        this.driver = driver;
        loginPage = new LoginPage(this.driver);
        homePage = new HomePage(this.driver);
    }

    // Depois do login, a home mostra "Olá <nome>,"
    public void loginComSucesso(String nome){

        try {
            String texto = Moeda.normalizar(homePage.getTextNome().getText());
            Assertions.assertTrue(texto.contains(nome),
                    "Esperava ver o nome '" + nome + "' na home, mas veio: '" + texto + "'");
            TakeScreenshot.screenshotPage(driver,"Login " + nome);
        } catch (Exception e) {
            TakeScreenshot.screenshotPage(driver,"Login " + nome);
            throw e;
        }
    }

    // Depois do logout, volta a tela inicial com o botão Acessar
    public void telaDeLogin(){

        try {
            Assertions.assertTrue(loginPage.getEmailInput().isDisplayed());
            TakeScreenshot.screenshotPage(driver,"Tela de Login");
        } catch (Exception e) {
            TakeScreenshot.screenshotPage(driver,"Tela de Login");
            throw e;
        }
    }
}

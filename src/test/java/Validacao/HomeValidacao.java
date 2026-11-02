package Validacao;

import Framework.Utils.Moeda;
import Framework.Utils.TakeScreenshot;
import PageObjects.HomePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class HomeValidacao {

    private WebDriver driver;
    private HomePage homePage;

    public HomeValidacao (WebDriver driver){

        this.driver = driver;
        homePage = new HomePage(this.driver);
    }

    public void validaSaldo(double saldoEsperado, String nomePrint){

        String esperado = Moeda.formatar(saldoEsperado);

        try {
            String texto = Moeda.normalizar(homePage.getTextSaldo().getText());
            Assertions.assertTrue(texto.contains(esperado),
                    "Saldo esperado " + esperado + " mas a tela mostrou: '" + texto + "'");
            TakeScreenshot.screenshotPage(driver,nomePrint);
        } catch (Exception e) {
            TakeScreenshot.screenshotPage(driver,nomePrint);
            throw e;
        }
    }
}

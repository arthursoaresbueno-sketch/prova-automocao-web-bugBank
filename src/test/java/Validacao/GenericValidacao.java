package Validacao;

import Framework.Utils.Moeda;
import Framework.Utils.TakeScreenshot;
import PageObjects.GenericPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class GenericValidacao {

    private WebDriver driver;
    private GenericPage genericPage;

    public GenericValidacao (WebDriver driver)
    {
        this.driver = driver;
        genericPage = new GenericPage(this.driver);
    }

    // Valida o texto que aparece na janela (modal) e tira print
    public void validationModal(String textoEsperado, String nomePrint){

        try {
            String texto = Moeda.normalizar(genericPage.getModalText().getText());
            Assertions.assertTrue(texto.contains(textoEsperado),
                    "Esperava encontrar '" + textoEsperado + "' no modal, mas veio: '" + texto + "'");
            TakeScreenshot.screenshotPage(driver,nomePrint);
        } catch (Exception e) {
            TakeScreenshot.screenshotPage(driver,nomePrint);
            throw e;
        }
    }
}

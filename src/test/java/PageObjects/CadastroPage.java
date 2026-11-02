package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CadastroPage {

    private WebDriver driver;
    private Waits waits;

    public CadastroPage(WebDriver driver){

        this.driver = driver;
        waits = new Waits(this.driver);
    }

    // Os campos do cadastro ficam dentro do cartão card__register

    public WebElement getEmailInput(){

        return waits.visbilityOfElement(By.cssSelector(".card__register input[name='email']"));
    }

    public WebElement getNomeInput(){

        return driver.findElement(By.cssSelector(".card__register input[name='name']"));
    }

    public WebElement getSenhaInput(){

        return driver.findElement(By.cssSelector(".card__register input[name='password']"));
    }

    public WebElement getConfirmaSenhaInput(){

        return driver.findElement(By.cssSelector(".card__register input[name='passwordConfirmation']"));
    }

    // Botão "Criar conta com saldo"
    public WebElement getCriarContaComSaldoToggle(){

        return driver.findElement(By.id("toggleAddBalance"));
    }

    public WebElement getCadastrarButton(){

        return driver.findElement(By.xpath("//div[contains(@class,'card__register')]//button[normalize-space()='Cadastrar']"));
    }
}

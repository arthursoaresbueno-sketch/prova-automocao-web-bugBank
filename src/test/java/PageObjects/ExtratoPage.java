package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExtratoPage {

    private WebDriver driver;
    private Waits waits;

    public ExtratoPage(WebDriver driver){

        this.driver = driver;
        waits = new Waits(this.driver);
    }

    // "Saldo disponível" no topo do extrato
    public WebElement getTextSaldoDisponivel(){

        return waits.visbilityOfElement(By.id("textBalanceAvailable"));
    }

    // Corpo da página inteira: usado para procurar a transferência (descrição + valor) na lista
    public WebElement getCorpoExtrato(){

        return waits.visbilityOfElement(By.tagName("body"));
    }
}

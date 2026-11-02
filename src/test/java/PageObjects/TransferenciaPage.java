package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TransferenciaPage {

    private WebDriver driver;
    private Waits waits;

    public TransferenciaPage(WebDriver driver){

        this.driver = driver;
        waits = new Waits(this.driver);
    }

    public WebElement getNumeroContaInput(){

        return waits.visbilityOfElement(By.name("accountNumber"));
    }

    public WebElement getDigitoInput(){

        return driver.findElement(By.name("digit"));
    }

    public WebElement getValorInput(){

        return driver.findElement(By.name("transferValue"));
    }

    public WebElement getDescricaoInput(){

        return driver.findElement(By.name("description"));
    }

    public WebElement getTransferirAgoraButton(){

        return driver.findElement(By.xpath("//button[normalize-space()='Transferir agora']"));
    }
}

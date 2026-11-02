package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;
    private Waits waits;

    public LoginPage (WebDriver driver){

        this.driver = driver;
        waits = new Waits(this.driver);
    }

    // A tela inicial tem dois formulários (login e registro) com os mesmos "name".
    // Por isso cada campo é buscado dentro do cartão certo (card__login).

    public WebElement getEmailInput(){

        return waits.visbilityOfElement(By.cssSelector(".card__login input[name='email']"));
    }

    public WebElement getPasswordInput(){

        return driver.findElement(By.cssSelector(".card__login input[name='password']"));
    }

    public WebElement getAcessarButton(){

        return driver.findElement(By.xpath("//div[contains(@class,'card__login')]//button[normalize-space()='Acessar']"));
    }

    public WebElement getRegistrarButton(){

        return waits.clickableElement(By.xpath("//button[normalize-space()='Registrar']"));
    }
}

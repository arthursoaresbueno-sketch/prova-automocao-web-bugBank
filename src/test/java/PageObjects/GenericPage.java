package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GenericPage {

    private WebDriver driver;
    private Waits waits;

    public GenericPage(WebDriver driver){

        this.driver = driver;
        waits = new Waits(this.driver);
    }

    // Janela (modal) que o BugBank mostra depois de cadastrar, transferir, etc.
    public WebElement getModalText(){

        return waits.visbilityOfElement(By.id("modalText"));
    }

    public WebElement getModalCloseButton(){

        return waits.clickableElement(By.id("btnCloseModal"));
    }

    public WebElement getVoltarButton(){

        return waits.clickableElement(By.id("btnBack"));
    }

    public WebElement getSairButton(){

        return waits.clickableElement(By.id("btnExit"));
    }
}

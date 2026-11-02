package Framework.Browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;

public class Waits {

    private WebDriver driver;

    public Waits (WebDriver driver){

        this.driver =driver;
    }

    //Metodo usado para espera dentro das classes de pages, ou seja, na busca do elemento
    public WebElement visbilityOfElement(By by){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    //Metodo usado para esperar o elemento estar pronto para clique
    public WebElement clickableElement(By by){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    //Clique mais resistente: rola até o elemento e tenta o clique normal.
    //Se algo estiver na frente (ElementClickInterceptedException), clica via JavaScript.
    public void clicar(WebElement element){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);

        try{

            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(element));
            element.click();

        }catch (WebDriverException e){

            js.executeScript("arguments[0].click();", element);
        }
    }

    //Digita no campo e confere o que ficou escrito. Se o valor não bater com o esperado
    //(por exemplo, "@" digitado duas vezes), preenche via JavaScript avisando o React.
    public void digitar(WebElement element, String texto){

        element.clear();
        element.sendKeys(texto);

        String atual = element.getAttribute("value");

        if (!texto.equals(atual)) {

            ((JavascriptExecutor) driver).executeScript(
                    "var el = arguments[0];" +
                            "var setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                            "setter.call(el, arguments[1]);" +
                            "el.dispatchEvent(new Event('input', { bubbles: true }));",
                    element, texto);
        }
    }

    //Metodo usado para esperar um texto aparecer em um elemento
    public void textInElement(By by, String texto){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(by, texto));
    }

    //Metodo usado para espera dentro das classes de task, ou seja, entre uma ação e outra
    public WebElement loadElement(WebElement element){

        try{

            return new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(element));

        }catch (WebDriverException e){

            return element;
        }

    }
}

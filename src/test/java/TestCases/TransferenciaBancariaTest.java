package TestCases;

import Framework.TestBase;
import Framework.Utils.TakeScreenshot;
import Tasks.CadastroTask;
import Tasks.ExtratoTask;
import Tasks.LoginTask;
import Tasks.TransferenciaTask;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

@Epic("Teste BugBank")
@Feature("Transferência Bancária")
public class TransferenciaBancariaTest extends TestBase {

    private final WebDriver driver = getDriverManager();

    CadastroTask cadastroTask = new CadastroTask(driver);
    LoginTask loginTask = new LoginTask(driver);
    TransferenciaTask transferenciaTask = new TransferenciaTask(driver);
    ExtratoTask extratoTask = new ExtratoTask(driver);

    @Test
    @Story("Usuário transfere valor entre duas contas com saldo")
    @Description("Cadastra duas contas com saldo, transfere da conta 1 para a conta 2 e valida o extrato das duas")
    public void realizarTransferenciaComSucesso() {

        try {
            // 1) Cadastrar as duas contas, ambas com saldo
            cadastroTask.cadastrarContaComSaldo(1);
            cadastroTask.cadastrarContaComSaldo(2);

            // 2) Entrar na conta 1, transferir e validar o extrato (conta que enviou)
            loginTask.efetuarLogin(1);
            transferenciaTask.transferirParaConta2();
            extratoTask.validarExtratoContaOrigem();
            loginTask.sair();

            // 3) Entrar na conta 2 e validar o extrato (conta que recebeu)
            loginTask.efetuarLogin(2);
            extratoTask.validarExtratoContaDestino();
            loginTask.sair();

        } catch (Exception e) {

            TakeScreenshot.screenshotPage(driver, "ERRO");
            throw e;

        }
    }

}

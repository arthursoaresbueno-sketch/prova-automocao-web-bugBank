package Framework.Utils;

import net.datafaker.Faker;

import java.text.Normalizer;
import java.util.Locale;

public class FakersGeneration {

    private Faker faker;

    public FakersGeneration (){

        faker = new Faker(Locale.of("pt", "BR"));
    }

    public static String removerAcentos(String texto) {
        return Normalizer
                .normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }

    public String getNome(){

        return removerAcentos(faker.name().firstName() + " " + faker.name().lastName());
    }

    public String getEmail(){

        // Montado na mão (o gerador do Datafaker em pt-BR chegou a devolver e-mail com dois @)
        return "teste" + faker.number().digits(8) + "@bugbank.com";
    }

    // Senha simples, mas diferente a cada execução
    public String getSenha(){

        return "Senha" + faker.number().digits(6);
    }

    // Descrição única, para conseguir achar a transferência certa no extrato
    public String getDescricao(){

        return "Transferencia prova " + faker.number().digits(6);
    }

    // Valor inteiro entre 10 e 500 (a conta com saldo começa com R$ 1.000,00)
    public int getValorTransferencia(){

        return faker.number().numberBetween(10, 500);
    }
}

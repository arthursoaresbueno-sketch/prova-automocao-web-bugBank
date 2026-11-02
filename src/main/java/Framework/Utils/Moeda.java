package Framework.Utils;

import java.util.Locale;

public class Moeda {

    // Converte 1000 em "R$ 1.000,00" (formato que o BugBank mostra na tela)
    public static String formatar(double valor){

        return String.format(Locale.of("pt", "BR"), "R$ %,.2f", valor);
    }

    // Converte 100 em "100,00" (sem o R$, porque no extrato o sinal pode vir antes ou depois do símbolo)
    public static String formatarSemSimbolo(double valor){

        return String.format(Locale.of("pt", "BR"), "%,.2f", valor);
    }

    // Troca espaço "não quebrável" por espaço normal, para comparar textos sem surpresa
    public static String normalizar(String texto){

        return texto.replace(' ', ' ').replaceAll("\\s+", " ").trim();
    }
}

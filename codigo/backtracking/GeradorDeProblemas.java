package codigo.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeradorDeProblemas {
    
        static final int TAM_BASE = 13;
    
        /**
     * Gerador de lances aleatórios para testes de leilão de energia.
     * @param quantLances A quantidade de lances que será utilizada em um teste. Deve ser um número inteiro positivo.
     * @param tamConjunto O tamanho do conjunto de testes, isto é, quantos conjuntos de lances do tamanho acima serão gerados.
     * @param dispersao A dispersão do tamanho dos lances em %. Por exemplo, se a dispersão for 0.50 (50%), os lances gerados estarão
     * entre 13 e 20. Uma dispersão de 1.0 (100%) gera conjuntos de lances entre 13 e 26.
     * @return Retorna uma lista de conjuntos de lances. Cada conjunto de lances é um vetor de objetos Lance.
     */
    public static List<Lance[]> geracaoDeLances(int quantLances, int tamConjunto) {
        Random aleatorio = new Random(System.currentTimeMillis()); // Gerador de números aleatórios com semente variável
        List<Lance[]> conjuntoDeTeste = new ArrayList<>(tamConjunto);
        for (int i = 0; i < tamConjunto; i++) {
            Lance[] lances = new Lance[quantLances];
            for (int j = 0; j < quantLances; j++) {
                int megawatts = 200 + aleatorio.nextInt(401); 
                int valorMinimo = megawatts * 3; // Valor mínimo é 3 vezes o megawatts
                int valorMaximo = megawatts * 5; // Valor máximo é 5 vezes o megawatts
                int valor = valorMinimo + aleatorio.nextInt(valorMaximo - valorMinimo + 1);
                String empresa = "E" + (j + 1); // Nome da empresa conforme o índice do lance
                lances[j] = new Lance(megawatts, valor, empresa);
            }
            conjuntoDeTeste.add(lances);
        }
        return conjuntoDeTeste;
    }
}

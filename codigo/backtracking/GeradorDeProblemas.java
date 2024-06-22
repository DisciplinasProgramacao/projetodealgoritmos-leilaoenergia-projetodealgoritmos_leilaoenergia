package codigo.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeradorDeProblemas {
    
        static Random aleatorio = new Random(42);
        static final int TAM_BASE = 13;
    
        public static List<Lance[]> geracaoDeLances(int quantLances, int tamConjunto, double dispersao) {
            List<Lance[]> conjuntoDeTeste = new ArrayList<>(tamConjunto);
            int tam_max = (int) Math.ceil(TAM_BASE * (1 + dispersao) * 100); 
            for (int i = 0; i < tamConjunto; i++) {
                Lance[] lances = new Lance[quantLances];
                for (int j = 0; j < quantLances; j++) {
                    int megawatts = 1 + aleatorio.nextInt(tam_max);
                    int valor = 1 + aleatorio.nextInt(tam_max);
                    String empresa = "E" + (j + 1); // Nome da empresa conforme o índice do lance
                    lances[j] = new Lance(megawatts, valor, empresa);
                }
                conjuntoDeTeste.add(lances);
            }
            return conjuntoDeTeste;
        }
}

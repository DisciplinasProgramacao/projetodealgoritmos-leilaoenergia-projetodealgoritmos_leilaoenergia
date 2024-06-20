package codigo.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class GeradorDeProblemas {
    static Random aleatorio = new Random(42);
    static final int TAM_BASE = 13;

    public static List<Lance[]> geracaoDeLances(int quantLances, int tamConjunto, double dispersao){
        List<Lance[]> conjuntoDeTeste = new ArrayList<>(tamConjunto);
        int tam_max = (int)Math.ceil(TAM_BASE * (1 + dispersao) * 100); // Scale appropriately for megawatts and value
        for(int i = 0; i < tamConjunto; i++){
            Lance[] lances = new Lance[quantLances];
            for (int j = 0; j < quantLances; j++) {
                int megawatts = 1 + aleatorio.nextInt(tam_max);
                int valor = 1 + aleatorio.nextInt(tam_max);
                lances[j] = new Lance(megawatts, valor);
            }
            conjuntoDeTeste.add(lances);
        }
        return conjuntoDeTeste;
    }
}

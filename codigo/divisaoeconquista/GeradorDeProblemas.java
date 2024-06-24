package codigo.divisaoeconquista;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeradorDeProblemas {
    
        static final int TAM_BASE = 13;

    public static List<Lance[]> geracaoDeLances(int quantLances, int tamConjunto) {
        Random aleatorio = new Random(System.currentTimeMillis());
        List<Lance[]> conjuntoDeTeste = new ArrayList<>(tamConjunto);
        for (int i = 0; i < tamConjunto; i++) {
            Lance[] lances = new Lance[quantLances];
            for (int j = 0; j < quantLances; j++) {
                int megawatts = 200 + aleatorio.nextInt(401); 
                int valorMinimo = megawatts * 3;
                int valorMaximo = megawatts * 5;
                int valor = valorMinimo + aleatorio.nextInt(valorMaximo - valorMinimo + 1);
                String empresa = "E" + (j + 1);
                lances[j] = new Lance(megawatts, valor, empresa);
            }
            conjuntoDeTeste.add(lances);
        }
        return conjuntoDeTeste;
    }
}

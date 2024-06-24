package codigo.divisaoeconquista;

import java.util.ArrayList;
import java.util.List;

class Resultado {
    List<Lance> lances;
    int valorTotal;

    Resultado(List<Lance> lances, int valorTotal) {
        this.lances = lances;
        this.valorTotal = valorTotal;
    }
}

public class DivisaoEConquista {
    private Lance[] lances;
    private int energiaTotal;

    public DivisaoEConquista(int energiaTotal, Lance[] lances) {
        this.energiaTotal = energiaTotal;
        this.lances = lances;
    }

    public Resultado encontrarMelhorVenda() {
        return encontrarMelhorVenda(0, energiaTotal, new ArrayList<>(), 0);
    }

    private Resultado encontrarMelhorVenda(int i, int energiaRestante, List<Lance> lancesAtuais, int valorTotal) {
        if (i == lances.length) {
            return new Resultado(new ArrayList<>(lancesAtuais), valorTotal);
        }

        if (lances[i].megawatts <= energiaRestante) {
            List<Lance> incluirLances = new ArrayList<>(lancesAtuais);
            incluirLances.add(lances[i]);
            Resultado incluir = encontrarMelhorVenda(i + 1, energiaRestante - lances[i].megawatts, incluirLances, valorTotal + lances[i].valor);
            Resultado naoIncluir = encontrarMelhorVenda(i + 1, energiaRestante, lancesAtuais, valorTotal);
            return incluir.valorTotal > naoIncluir.valorTotal ? incluir : naoIncluir;
        } else {
            return encontrarMelhorVenda(i + 1, energiaRestante, lancesAtuais, valorTotal);
        }
    }
}
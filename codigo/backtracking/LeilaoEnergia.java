package codigo.backtracking;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LeilaoEnergia {
    private int energiaTotal;
    private Lance[] lances;
    private List<Lance> melhorLances;
    private int melhorValor;

    public LeilaoEnergia(int energiaTotal, Lance[] lances) {
        this.energiaTotal = energiaTotal;
        this.lances = lances;
        this.melhorLances = new ArrayList<>();
        this.melhorValor = 0;
    }

    public void encontrarMelhorVenda() {
        encontrarMelhorVenda(new ArrayList<>(), 0, 0, 0);
    }

    private void encontrarMelhorVenda(List<Lance> lancesAtuais, int indiceAtual, int energiaUsada, int valorAtual) {
        if (energiaUsada <= energiaTotal) {
            if (valorAtual > melhorValor) {
                melhorValor = valorAtual;
                melhorLances = new ArrayList<>(lancesAtuais);
            }
            for (int i = indiceAtual; i < lances.length; i++) {
                if (energiaUsada + lances[i].megawatts <= energiaTotal) { // Poda
                    lancesAtuais.add(lances[i]);
                    encontrarMelhorVenda(lancesAtuais, i + 1, energiaUsada + lances[i].megawatts, valorAtual + lances[i].valor);
                    lancesAtuais.remove(lancesAtuais.size() - 1);
                }
            }
        }
    }

    public List<Lance> getMelhorLances() {
        return melhorLances;
    }

    public int getMelhorValor() {
        return melhorValor;
    }

    private static Lance[] gerarConjunto1() {
        return new Lance[]{
                new Lance(430, 1043), new Lance(428, 1188), new Lance(410, 1565), new Lance(385, 1333),
                new Lance(399, 1214), new Lance(382, 1498), new Lance(416, 1540), new Lance(436, 1172),
                new Lance(416, 1386), new Lance(423, 1097), new Lance(400, 1463), new Lance(406, 1353),
                new Lance(403, 1568), new Lance(390, 1228), new Lance(387, 1542), new Lance(390, 1206),
                new Lance(430, 1175), new Lance(397, 1492), new Lance(392, 1293), new Lance(393, 1533),
                new Lance(439, 1149), new Lance(403, 1277), new Lance(415, 1624), new Lance(387, 1280),
                new Lance(417, 1330)
        };
    }

    private static Lance[] gerarConjunto2() {
        return new Lance[]{
                new Lance(430, 1043), new Lance(428, 1188), new Lance(410, 1565), new Lance(385, 1333),
                new Lance(399, 1214), new Lance(382, 1498), new Lance(416, 1540), new Lance(436, 1172),
                new Lance(416, 1386), new Lance(423, 1097), new Lance(400, 1463), new Lance(406, 1353),
                new Lance(403, 1568), new Lance(390, 1228), new Lance(387, 1542), new Lance(390, 1206),
                new Lance(430, 1175), new Lance(397, 1492), new Lance(392, 1293), new Lance(393, 1533),
                new Lance(439, 1149), new Lance(403, 1277), new Lance(415, 1624), new Lance(387, 1280),
                new Lance(417, 1330), new Lance(313, 1496), new Lance(398, 1768), new Lance(240, 1210),
                new Lance(433, 2327), new Lance(301, 1263), new Lance(297, 1499), new Lance(232, 1209),
                new Lance(614, 2342), new Lance(558, 2983), new Lance(495, 2259), new Lance(310, 1381),
                new Lance(213, 961), new Lance(213, 1115), new Lance(346, 1552), new Lance(385, 2023),
                new Lance(240, 1234), new Lance(483, 2828), new Lance(487, 2617), new Lance(709, 2328),
                new Lance(358, 1847), new Lance(467, 2038), new Lance(363, 2007), new Lance(279, 1311),
                new Lance(589, 3164), new Lance(476, 2480)
        };
    }

    public static void main1(String[] args) {
        // Main para o conjunto 1
        int energiaTotalConjunto1 = 8000;
        Lance[] conjunto1 = gerarConjunto1();
        executarTeste("Conjunto 1", conjunto1, energiaTotalConjunto1);
    }

    public static void main2(String[] args) {
        // Main para o conjunto 2
        int energiaTotalConjunto2 = 8000;
        Lance[] conjunto2 = gerarConjunto2();
        executarTeste("Conjunto 2", conjunto2, energiaTotalConjunto2);
    }

    public static void main(String[] args) {

        int energiaTotal = 8000;
        int incremento = 1; 
        long limiteTempoMillis = 30000; 
        int numTestes = 10; 

        // Gerar conjuntos de teste e executar testes incrementando o tamanho do conjunto
        for (int tamanhoConjunto = incremento;; tamanhoConjunto += incremento) {
            List<Lance[]> conjunto = GeradorDeProblemas.geracaoDeLances(tamanhoConjunto, 10, 0.5); // Gerar um conjunto de teste
            long totalDurationMillis = 0;

            // Executar testes para o conjunto atual
            for (int teste = 0; teste < numTestes; teste++) {
                Instant start = Instant.now();
                LeilaoEnergia leilao = new LeilaoEnergia(energiaTotal, conjunto.get(0)); // Usar apenas o primeiro conjunto gerado
                leilao.encontrarMelhorVenda();
                Instant end = Instant.now();
                Duration duration = Duration.between(start, end);
                totalDurationMillis += duration.toMillis();
            }

            long averageDurationMillis = totalDurationMillis / numTestes;

            // Verificar se o tempo médio excede o limite de tempo
            if (averageDurationMillis > limiteTempoMillis) {
                System.out.println("Conjunto com tamanho " + tamanhoConjunto + " não pode ser resolvido em até 30 segundos.");
                break; // Sair do loop se exceder o tempo limite
            }

            // Exibir informações do conjunto atual
            System.out.println("Conjunto com tamanho " + tamanhoConjunto + ", Tempo médio de execução: " + averageDurationMillis + " ms");
        }
    }
    

    // Método utilizado para executar os conjuntos pré determinados
    private static void executarTeste(String nomeConjunto, Lance[] lances, int energiaTotal) {
        long totalDurationMillis = 0;

        for (int i = 0; i < 10; i++) { // Realizar 10 testes para cada conjunto
            Instant start = Instant.now();
            LeilaoEnergia leilao = new LeilaoEnergia(energiaTotal, lances);
            leilao.encontrarMelhorVenda();
            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);
            totalDurationMillis += duration.toMillis();
        }

        long averageDurationMillis = totalDurationMillis / 10;

        System.out.println("Conjunto: " + nomeConjunto + ", Tempo médio de execução: " + averageDurationMillis + " ms");
    }
}

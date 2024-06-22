package codigo.backtracking;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class LeilaoEnergia {
    private int energiaTotal;
    private Lance[] lances;
    private List<Lance> melhorLances;
    private int melhorValor;
    private int energiaUtilizada;

    public LeilaoEnergia(int energiaTotal, Lance[] lances) {
        this.energiaTotal = energiaTotal;
        this.lances = lances;
        this.melhorLances = new ArrayList<>();
        this.melhorValor = 0;
        this.energiaUtilizada = 0;
    }

    public void encontrarMelhorVenda() {
        encontrarMelhorVenda(new ArrayList<>(), 0, 0, 0);
        System.out.println("Melhor solução encontrada:");
        System.out.println("Lances:");
        for (Lance lance : melhorLances) {
            System.out.println("\t(megawatts=" + lance.megawatts + ", valor=" + lance.valor + ", empresa='" + lance.empresa + "')");
        }
        System.out.println("\nCom valor total de " + melhorValor + " dinheiros e energia total utilizada de " + energiaUtilizada + " megawatts");

    }

    private void encontrarMelhorVenda(List<Lance> lancesAtuais, int indiceAtual, int energiaUsada, int valorAtual) {
        if (energiaUsada <= energiaTotal) {
            if (valorAtual > melhorValor) {
                melhorValor = valorAtual;
                melhorLances = new ArrayList<>(lancesAtuais);
                energiaUtilizada = energiaUsada;
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

    public int getEnergiaUtilizada() {
        return energiaUtilizada;
    }

    public static void main(String[] args) {
        // Main para o conjunto 1
        int energiaTotalConjunto1 = 8000;
        Lance[] conjunto1 = DadosDeTeste.gerarConjunto1();
        executarTeste("Conjunto 1", conjunto1, energiaTotalConjunto1, 1); // Testar uma vez
    }

    public static void main2(String[] args) {
        // Main para o conjunto 2
        int energiaTotalConjunto2 = 8000;
        Lance[] conjunto2 = DadosDeTeste.gerarConjunto2();
        executarTeste("Conjunto 2", conjunto2, energiaTotalConjunto2, 1); // Testar uma vez
    }

    public static void main3(String[] args) {
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

    // Método utilizado para executar os conjuntos pré-determinados
    private static void executarTeste(String nomeConjunto, Lance[] lances, int energiaTotal, int numTestes) {
        long totalDurationMillis = 0;

        for (int i = 0; i < numTestes; i++) { // Realizar o número especificado de testes para cada conjunto
            Instant start = Instant.now();
            LeilaoEnergia leilao = new LeilaoEnergia(energiaTotal, lances);
            leilao.encontrarMelhorVenda();
            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);
            totalDurationMillis += duration.toMillis();
        }

        long averageDurationMillis = totalDurationMillis / numTestes;

        System.out.println("Conjunto: " + nomeConjunto + ", Tempo médio de execução: " + averageDurationMillis + " ms");
    }
}

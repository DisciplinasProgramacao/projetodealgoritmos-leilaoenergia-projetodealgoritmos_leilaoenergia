package codigo.divisaoeconquista;

import java.util.List;

public class Main {
    public static void main1(String[] args) {
        int energiaTotal = 8000;
        int incremento = 1;
        long limiteTempoMillis = 30000;
        int numTestes = 10;

        // Conjunto 1
        Lance[] conjunto1 = DadosDeTeste.gerarConjunto1();
        DivisaoEConquista leilao1 = new DivisaoEConquista(energiaTotal, conjunto1);
        Resultado resultado1 = leilao1.encontrarMelhorVenda();
        System.out.println("Melhor venda para o conjunto 1: " + resultado1.valorTotal);
        for (Lance lance : resultado1.lances) {
            System.out.println("Empresa: " + lance.empresa + ", Megawatts: " + lance.megawatts + ", Valor: " + lance.valor);
        }

        // Conjunto 2
        Lance[] conjunto2 = DadosDeTeste.gerarConjunto2();
        DivisaoEConquista leilao2 = new DivisaoEConquista(energiaTotal, conjunto2);
        Resultado resultado2 = leilao2.encontrarMelhorVenda();
        System.out.println("\nMelhor venda para o conjunto 2: " + resultado2.valorTotal);
        for (Lance lance : resultado2.lances) {
            System.out.println("Empresa: " + lance.empresa + ", Megawatts: " + lance.megawatts + ", Valor: " + lance.valor);
        }

        // Gerar conjuntos de teste e executar testes incrementando o tamanho do conjunto
        for (int tamanhoConjunto = 10;; tamanhoConjunto += incremento) {
            System.out.println("\nComeçando testes para o conjunto " + tamanhoConjunto + "...");
            List<Lance[]> conjuntos = GeradorDeProblemas.geracaoDeLances(tamanhoConjunto, numTestes); // Gerar conjuntos de teste
            long totalDurationMillis = 0;

            // Executar testes para o conjunto atual
            for (int teste = 0; teste < numTestes; teste++) {
                System.out.println("Teste " + (teste + 1));
                long start = System.currentTimeMillis();
                DivisaoEConquista leilao = new DivisaoEConquista(energiaTotal, conjuntos.get(teste)); // Usar cada conjunto gerado
                Resultado resultado = leilao.encontrarMelhorVenda();
                long end = System.currentTimeMillis();
                long durationMillis = end - start;
                totalDurationMillis += durationMillis;

                // Imprimir detalhes do teste atual
                System.out.println("Detalhes do teste " + (teste + 1) + ":");
                System.out.println("Melhor venda: " + resultado.valorTotal);
                for (Lance lance : resultado.lances) {
                    System.out.println("Empresa: " + lance.empresa + ", Megawatts: " + lance.megawatts + ", Valor: " + lance.valor);
                }
                System.out.println("Tempo de execução: " + durationMillis + " ms");
            }

            long averageDurationMillis = totalDurationMillis / numTestes;

            // Verificar se o tempo médio excede o limite de tempo
            if (averageDurationMillis > limiteTempoMillis/numTestes) {
                System.out.println("Conjunto com tamanho " + tamanhoConjunto + " não pode ser resolvido em até 30 segundos.");
                System.out.println("Tempo médio excedeu o limite: " + averageDurationMillis + " ms");
                break; // Sair do loop se exceder o tempo limite
            }

            // Exibir informações do conjunto atual
            System.out.println("Conjunto com tamanho " + tamanhoConjunto + ", Tempo médio de execução: " + averageDurationMillis + " ms\n\n");
        }
    }
}

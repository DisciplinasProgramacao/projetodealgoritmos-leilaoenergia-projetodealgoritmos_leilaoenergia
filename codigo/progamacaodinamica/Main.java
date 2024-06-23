package codigo.progamacaodinamica;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // 1 Conjunto de teste com 25 partes interessadas
        Bid[] testSet1 = {
            new Bid("E1", 430, 1043), new Bid("E2", 428, 1188), new Bid("E3", 410, 1565),
            new Bid("E4", 385, 1333), new Bid("E5", 399, 1214), new Bid("E6", 382, 1498),
            new Bid("E7", 416, 1540), new Bid("E8", 436, 1172), new Bid("E9", 416, 1386),
            new Bid("E10", 423, 1097), new Bid("E11", 400, 1463), new Bid("E12", 406, 1353),
            new Bid("E13", 403, 1568), new Bid("E14", 390, 1228), new Bid("E15", 387, 1542),
            new Bid("E16", 390, 1206), new Bid("E17", 430, 1175), new Bid("E18", 397, 1492),
            new Bid("E19", 392, 1293), new Bid("E20", 393, 1533), new Bid("E21", 439, 1149),
            new Bid("E22", 403, 1277), new Bid("E23", 415, 1624), new Bid("E24", 387, 1280),
            new Bid("E25", 417, 1330)
        };

        Bid[] testSet2 = {
            new Bid("E1", 313, 1496), new Bid("E2", 398, 1768), new Bid("E3", 240, 1210),
            new Bid("E4", 433, 2327), new Bid("E5", 301, 1263), new Bid("E6", 297, 1499),
            new Bid("E7", 232, 1209), new Bid("E8", 614, 2342), new Bid("E9", 558, 2983),
            new Bid("E10", 495, 2259), new Bid("E11", 310, 1381), new Bid("E12", 213, 961),
            new Bid("E13", 213, 1115), new Bid("E14", 346, 1552), new Bid("E15", 385, 2023),
            new Bid("E16", 240, 1234), new Bid("E17", 483, 2828), new Bid("E18", 487, 2617),
            new Bid("E19", 709, 2328), new Bid("E20", 358, 1847), new Bid("E21", 467, 2038),
            new Bid("E22", 363, 2007), new Bid("E23", 279, 1311), new Bid("E24", 589, 3164),
            new Bid("E25", 476, 2480)
        };

        int capacity = 8000; // Capacidade total de megawatts

        // Medição de tempo para o conjunto de teste com 10 itens
        List<Bid> selectedBids1 = new ArrayList<>();
        long startTime1 = System.nanoTime();
        int maxValue1 = EnergyAuction.maximizeValue(capacity, testSet1, selectedBids1);
        long endTime1 = System.nanoTime();
        long duration1 = (endTime1 - startTime1) / 1000000; // Convertendo para milissegundos

        // Medição de tempo para o conjunto de teste com 100 itens
        List<Bid> selectedBids2 = new ArrayList<>();
        long startTime2 = System.nanoTime();
        int maxValue2 = EnergyAuction.maximizeValue(capacity, testSet2, selectedBids2);
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2) / 1000000; // Convertendo para milissegundos

        // Resultados
        System.out.println(" Conjunto 1");
        int totalMW1 = selectedBids1.stream().mapToInt(bid -> bid.megawatts).sum();
        int totalValue1 = selectedBids1.stream().mapToInt(bid -> bid.value).sum();
        System.out.println("Ofertas selecionadas:");
        for (Bid bid : selectedBids1) {
            System.out.println(" - " + bid.megawatts + " MW por " + bid.value + " reais");
        }
        System.out.println("Total de MW vendidos do 1 conjunto : " + totalMW1 + " MW");
        System.out.println("Valor total arrecadado do 1 conjunto: " + totalValue1 + " reais");
        System.out.println("tempo = " + duration1 + "ms");
       

        System.out.println("Conjunto 2");
        int totalMW2 = selectedBids2.stream().mapToInt(bid -> bid.megawatts).sum();
        int totalValue2 = selectedBids2.stream().mapToInt(bid -> bid.value).sum();
        System.out.println("Ofertas selecionadas do conjunto 2:");
        for (Bid bid : selectedBids1) {
            System.out.println(" - " + bid.name + ": " + bid.megawatts + " MW por " + bid.value + " reais");
        }
        System.out.println("Total de MW vendidos :  " + totalMW2 + " MW");
        System.out.println("Interessados escolhidos");
        System.out.println("Valor total arrecadado : " + totalValue2 + " reais");
        System.out.println("tempo = " + duration2 + "ms");

        // Exibição dos tempos totais
        System.out.println("Tempo de execução do conjunto 1: " + duration1 + " ms");
        System.out.println("Tempo de execução do conjunto 2: " + duration2 + " ms");

        Bid[] testSet3 = GeradorDeProblemas.generateTestSet(32);
        Bid[] testSet4 = GeradorDeProblemas.generateTestSet(128);
        Bid[] testSet5 = GeradorDeProblemas.generateTestSet(256);
        Bid[] testSet6 = GeradorDeProblemas.generateTestSet(320);


        // Executar e medir o tempo dos conjuntos de teste
        executeAndMeasure(testSet3, capacity, "Conjunto 3");
        executeAndMeasure(testSet4, capacity, "Conjunto 4");
        executeAndMeasure(testSet5, capacity, "Conjunto 5");
        executeAndMeasure(testSet6, capacity, "Conjunto 6");
    }

    private static void executeAndMeasure(Bid[] testSet, int capacity, String setName) {
        List<Bid> selectedBids = new ArrayList<>();
        long startTime = System.nanoTime();
        int maxValue = EnergyAuction.maximizeValue(capacity, testSet, selectedBids);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; // Convertendo para milissegundos

        // Resultados
        System.out.println(setName);
        int totalMW = selectedBids.stream().mapToInt(bid -> bid.megawatts).sum();
        int totalValue = selectedBids.stream().mapToInt(bid -> bid.value).sum();
        System.out.println("Total de MW vendidos: " + totalMW + " MW");
        System.out.println("Valor total arrecadado: " + totalValue + " reais");
        System.out.println("Tempo de execução: " + duration + " ms");
        System.out.println();
    }
}

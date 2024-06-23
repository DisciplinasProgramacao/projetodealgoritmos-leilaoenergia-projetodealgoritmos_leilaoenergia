package codigo.progamacaodinamica;

public class GeradorDeProblemas {

    public static Bid[] generateTestSet(int size) {
        Bid[] testSet = new Bid[size];
        for (int i = 0; i < size; i++) {
            int megawatts = (int) (Math.random() * 451) + 250; // Gera valores inteiros entre 250 e 700
            int value = (int) (Math.random() * 1401) + 700;    // Gera valores inteiros entre 700 e 2100
            testSet[i] = new Bid("E" + (i + 1), megawatts, value);
        }
        return testSet;
    }
}
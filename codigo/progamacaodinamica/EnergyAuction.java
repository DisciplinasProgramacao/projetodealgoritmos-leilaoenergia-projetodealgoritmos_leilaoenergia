package codigo.progamacaodinamica;

import java.util.List;

public class EnergyAuction {

    // Função para resolver o problema utilizando programação dinâmica
    public static int maximizeValue(int capacity, Bid[] bids, List<Bid> selectedBids) {
        int n = bids.length;
        int[][] dp = new int[n + 1][capacity + 1];

        // Construindo a tabela dp de baixo para cima
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (bids[i - 1].megawatts <= w) {
                    dp[i][w] = Math.max(bids[i - 1].value + dp[i - 1][w - bids[i - 1].megawatts], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Encontrar quais itens foram incluídos na solução
        int res = dp[n][capacity];
        int w = capacity;

        for (int i = n; i > 0 && res > 0; i--) {
            if (res != dp[i - 1][w]) {
                selectedBids.add(bids[i - 1]);
                res -= bids[i - 1].value;
                w -= bids[i - 1].megawatts;
            }
        }

        return dp[n][capacity];
    }
}

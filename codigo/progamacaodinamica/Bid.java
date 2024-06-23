package codigo.progamacaodinamica;

public class Bid {
    int megawatts;
    int value;
    String name;

    public Bid(String name, int megawatts, int value) {
        this.name= name;
        this.megawatts = megawatts;
        this.value = value;
    }
}

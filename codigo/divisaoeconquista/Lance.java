package codigo.divisaoeconquista;

public class Lance {
    int megawatts;
    int valor;
    String empresa;

    public Lance(int megawatts, int valor, String empresa) {
        this.megawatts = megawatts;
        this.valor = valor;
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "(" +
                ", empresa='" + empresa + '\'' +
                "megawatts=" + megawatts +
                ", valor=" + valor +
                ')';
    }
}


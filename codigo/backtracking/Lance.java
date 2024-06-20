package codigo.backtracking;

class Lance {
    int megawatts;
    int valor;

    Lance(int megawatts, int valor) {
        this.megawatts = megawatts;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Lance{" +
                "megawatts=" + megawatts +
                ", valor=" + valor +
                '}';
    }
}

public class Numero implements Comparable<Numero> {
    private final int valor;

    public Numero(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public String comparar(Numero otro) {
        if (valor > otro.valor) {
            return "mayor";
        }
        if (valor < otro.valor) {
            return "menor";
        }
        return "igual";
    }

    @Override
    public String toString() {
        return "" + valor;
    }
}

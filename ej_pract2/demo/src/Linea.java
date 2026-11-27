public class Linea implements Comparable<Linea> {
    private final Punto p1;
    private final Punto p2;

    public Linea(Punto p1, Punto p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public double longitud() {
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        return raizCuadrada(dx * dx + dy * dy);
    }

    private double raizCuadrada(double n) {
        if (n == 0) {
            return 0;
        }
        double x = n;
        for (int i = 0; i < 20; i++) {
            x = (x + n / x) / 2;
        }
        return x;
    }

    public String comparar(Linea otra) {
        double a = longitud();
        double b = otra.longitud();
        if (a > b) {
            return "mayor";
        }
        if (a < b) {
            return "menor";
        }
        return "igual";
    }

    @Override
    public String toString() {
        return p1 + " a " + p2;
    }
}

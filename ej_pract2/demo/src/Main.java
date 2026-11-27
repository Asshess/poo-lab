public class Main {
    public static void main(String[] args) {
        Comparable[] elementos = {
            new Numero(8), new Numero(3),
            new Linea(new Punto(0, 0), new Punto(3, 4)),
            new Linea(new Punto(0, 0), new Punto(6, 8))
        };

        for (int i = 0; i < elementos.length; i += 2) {
            Comparable a = elementos[i];
            Comparable b = elementos[i + 1];

            if (a instanceof Numero && b instanceof Numero) {
                Numero n1 = (Numero) a;
                Numero n2 = (Numero) b;
                System.out.println("Comparando numeros: " + n1 + " y " + n2);
                System.out.println("El primer numero es " + n1.comparar(n2) + " al segundo");
            } else if (a instanceof Linea && b instanceof Linea) {
                Linea l1 = (Linea) a;
                Linea l2 = (Linea) b;
                System.out.println("Comparando lineas: " + l1 + " y " + l2);
                System.out.println("La primera linea es " + l1.comparar(l2)
                        + " a la segunda (longitudes " + l1.longitud()
                        + " y " + l2.longitud() + ")");
            }
            System.out.println();
        }
    }
}

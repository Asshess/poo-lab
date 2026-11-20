package teatro;

import teatro.entradas.Entrada_abonado;
import teatro.entradas.Entrada_normal;
import teatro.entradas.Entrada_reducida;

public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Ana", 123456, "estudiante");
        Cliente cliente2 = new Cliente("Luis", 654321, "pensionado");
        Cliente cliente3 = new Cliente("Maria", 112233, "abonado");

        Entrada_normal normal = new Entrada_normal("La Traviata", "Principal", cliente1);
        Entrada_reducida reducida = new Entrada_reducida("La Traviata", "Central", cliente2);
        Entrada_abonado abonado = new Entrada_abonado("La Traviata", "Palco", cliente3);

        System.out.println("Normal: " + normal.calcularPrecio());
        System.out.println("Reducida: " + reducida.calcularPrecio());
        System.out.println("Abonado: " + abonado.calcularPrecio());
    }
}
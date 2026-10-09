package teatro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import teatro.entradas.Entrada_abonado;
import teatro.entradas.Entrada_normal;
import teatro.entradas.Entrada_reducida;

class EntradaTest {

    @Test
    void calculaPrecioNormal() {
        Cliente cliente = new Cliente("Ana", 123456, "estudiante");
        Entrada entrada = new Entrada_normal("La Traviata", "Principal", cliente);

        assertEquals(3500.0, entrada.calcularPrecio());
    }

    @Test
    void calculaPrecioReducido() {
        Cliente cliente = new Cliente("Luis", 654321, "pensionado");
        Entrada entrada = new Entrada_reducida("La Traviata", "Central", cliente);

        assertEquals(2125.0, entrada.calcularPrecio());
    }

    @Test
    void calculaPrecioAbonado() {
        Cliente cliente = new Cliente("Maria", 112233, "abonado");
        Entrada entrada = new Entrada_abonado("La Traviata", "Palco", cliente);

        assertEquals(1800.0, entrada.calcularPrecio());
    }
}

package farmacia;

import farmacia.estructuras.CintaSalida;
import farmacia.estructuras.Tolva;
import farmacia.excepciones.CapacidadInvalidaException;
import farmacia.modelo.Medicamento;
import farmacia.modelo.MedicamentoControlado;
import farmacia.modelo.MedicamentoRefrigerado;

public class Main {

    public static void main(String[] args) {
        try {
            // 1. Inicializar tolva (límite 5) y cinta de salida (límite 2)
            Tolva<Medicamento> tolvaEntrada = new Tolva<>(5);
            CintaSalida<Medicamento> cintaSalida = new CintaSalida<>(2);

            // 2. Cargar lote de 3 medicamentos (2 refrigerados y 1 controlado con receta)
            // Nota: Al ser una pila (LIFO), el último cargado será el primero en extraerse.
            tolvaEntrada.cargar(new MedicamentoRefrigerado("Insulina Glargina", 4.5));
            tolvaEntrada.cargar(new MedicamentoRefrigerado("Vacuna Tetravalente", 2.0));
            tolvaEntrada.cargar(new MedicamentoControlado("Morfina Sulfato", true));

            // 3. Mostrar información inicial de la tolva y de la cinta
            System.out.println("=== ESTADO INICIAL DEL SISTEMA ===");
            System.out.println(tolvaEntrada);
            System.out.println(cintaSalida);

            // 4. Iniciar procesamiento del robot
            BrazoRobotico robot = new BrazoRobotico();
            robot.procesarTransferencia(tolvaEntrada, cintaSalida);

        } catch (CapacidadInvalidaException e) {
            System.err.println("Error al inicializar estructuras: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado en la ejecución: " + e.getMessage());
        }
    }
}

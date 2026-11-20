package farmacia;

import farmacia.estructuras.CintaSalida;
import farmacia.estructuras.Tolva;
import farmacia.excepciones.CintaLlenaException;
import farmacia.excepciones.TolvaVaciaException;
import farmacia.modelo.Medicamento;
import farmacia.modelo.MedicamentoControlado;
import farmacia.modelo.MedicamentoRefrigerado;

public class BrazoRobotico {

    public void procesarTransferencia(Tolva<Medicamento> tolvaEntrada, CintaSalida<Medicamento> cintaSalida) {
        Integer procesadosExitosamente = 0;
        boolean obstruccionDetectada = false;

        System.out.println("\n>>> INICIANDO PROCESO DE TRANSFERENCIA DEL BRAZO ROBÓTICO <<<\n");

        while (!tolvaEntrada.estaVacia() && !obstruccionDetectada) {
            Medicamento medActual = null;
            try {
                // 1. Extraer medicamento del tope de la tolva
                medActual = tolvaEntrada.extraer();
                System.out.println("-> Extraído de tolva: " + medActual);

                // 2. Identificación en tiempo de ejecución y Downcasting seguro
                aplicarPoliticaControl(medActual);

                // 3. Intentar depositar en la cinta de salida
                cintaSalida.depositar(medActual);
                procesadosExitosamente++;
                System.out.println("   [OK] Depositado exitosamente en cinta de salida ("
                        + cintaSalida.getCantidadActual() + "/" + cintaSalida.getCapacidadMaxima() + ").\n");

            } catch (CintaLlenaException e) {
                obstruccionDetectada = true;
                System.err.println("\n[ALERTA CRÍTICA DE FLUJO] " + e.getMessage());
                System.err.println("-> El medicamento " + medActual.getId()
                        + " no pudo ingresar a la cinta. Se detiene la extracción inmediatamente.\n");
            } catch (TolvaVaciaException e) {
                System.err.println("[AVISO] " + e.getMessage());
                break;
            }
        }

        // 4. Reporte final de operación
        emitirReporteFinal(procesadosExitosamente, tolvaEntrada.getCantidadActual(), cintaSalida);
    }

    private void aplicarPoliticaControl(Medicamento medicamento) {
        if (medicamento instanceof MedicamentoRefrigerado) {
            // Downcasting seguro a MedicamentoRefrigerado
            MedicamentoRefrigerado refrigerado = (MedicamentoRefrigerado) medicamento;
            System.out.println("   [POLÍTICA CADENA DE FRÍO] Temperatura requerida: "
                    + refrigerado.getTemperaturaSeguridad() + " °C. Mantener refrigeración estricta.");
        } else if (medicamento instanceof MedicamentoControlado) {
            // Downcasting seguro a MedicamentoControlado
            MedicamentoControlado controlado = (MedicamentoControlado) medicamento;
            if (Boolean.TRUE.equals(controlado.getRequiereReceta())) {
                System.out.println("   [POLÍTICA CONTROLADO] ALERTA AL OPERADOR: Este medicamento EXIGE receta médica física archivada.");
            } else {
                System.out.println("   [POLÍTICA CONTROLADO] Notificación: Medicamento de libre venta.");
            }
        }
    }

    private void emitirReporteFinal(Integer procesados, Integer atrapadosEnTolva, CintaSalida<Medicamento> cinta) {
        System.out.println("============================================================");
        System.out.println("                REPORTE FINAL DE OPERACIÓN                  ");
        System.out.println("============================================================");
        System.out.println(" - Medicamentos procesados exitosamente (en cinta): " + procesados);
        System.out.println(" - Medicamentos atrapados en tolva de entrada:     " + atrapadosEnTolva);
        System.out.println(" - Estado final de cinta:                          " + cinta);
        System.out.println("============================================================\n");
    }
}

package teatro.entradas;

import teatro.Cliente;
import teatro.Entrada;
import teatro.Zona;

public class Entrada_normal extends Entrada {

    public Entrada_normal(String funcion, String zona, Cliente cliente) {
        super(funcion, zona, cliente);
    }
    
    @Override
    public double calcularPrecio() {
        Zona zonaInfo = getZonaInfo();
        if (zonaInfo == null) {
            return 0;
        }
        return zonaInfo.getPrecioNormal();
    }
}

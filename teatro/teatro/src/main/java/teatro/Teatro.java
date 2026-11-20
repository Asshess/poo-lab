package teatro;

public class Teatro {
    
    public String nombre;
    public int id;
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public Teatro(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    
}

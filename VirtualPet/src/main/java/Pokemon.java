package main.java;

public class Pokemon extends Mascota {
    
    enum TipoElemento {
        AGUA,
        FUEGO,
        TIERRA,
        ELECTRICO
    }
    
    private TipoElemento tipo;
    private int puntosPoder;
    private int nivelEvolucion;

    public Pokemon(String name, int age, TipoElemento tipo, int puntosPoder, int nivelEvolucion) {
        super(name, age);
        this.tipo = tipo;
        this.puntosPoder = puntosPoder;
        this.nivelEvolucion = nivelEvolucion;
    }
    public TipoElemento getTipo() {
        return tipo;
    }
    public void setTipo(TipoElemento tipo) {
        this.tipo = tipo;
    }
    public int getPuntosPoder() {
        return puntosPoder;
    }
    public void setPuntosPoder(int puntosPoder) {
        this.puntosPoder = puntosPoder;
    }
    public int getNivelEvolucion() {
        return nivelEvolucion;
    }
    public void setNivelEvolucion(int nivelEvolucion) {
        this.nivelEvolucion = nivelEvolucion;
    }

    @Override
    public String alimentar(int cantidadBayas){
        cantidadBayas = Math.max(0, cantidadBayas);
        hunger = (int) Math.max(0L, hunger - 8L * cantidadBayas);
        happiness = (int) Math.min(100L, happiness + 4L * cantidadBayas);
        String mensaje = name + " consumió " + cantidadBayas + " bayas Arándano.";
        System.out.println(mensaje);
        return mensaje;
    }

    @Override
    public String atacarEspecial(){
        hunger = Math.min(100, hunger + 20);

        String mensajeAtaque;
        switch(this.tipo) {
            case FUEGO:
                mensajeAtaque = name + " usa Lanzallamas!";
                break;
            case AGUA:
                mensajeAtaque = name + " usa Chorro!";
                break;
            case ELECTRICO:
                mensajeAtaque = name + " usa Impactrueno!";
                break;
            case TIERRA:
                mensajeAtaque = name + " usa Terremoto!";
                break;
            default:
                mensajeAtaque = name + " realiza un ataque especial.";
                break;
        }

        int numeroAleatorio = (int) (Math.random() * 100) + 1;
        String resultadoAtaque;
        if (numeroAleatorio <= 50) {
            happiness = Math.min(100, happiness + 30);
            resultadoAtaque = "Ataque exitoso! Felicidad: " + happiness;
        } else {
            happiness = Math.max(0, happiness - 10);
            resultadoAtaque = "El ataque falló. Felicidad: " + happiness;
        }

        String mensaje = mensajeAtaque + " " + resultadoAtaque;
        System.out.println(mensaje);
        return mensaje;
    }

    @Override
    public String expresarEmocion(){
        happiness = Math.max(0, happiness - 15);
        String mensaje;
        switch(this.tipo) {
            case FUEGO:
                mensaje = name + " está más rojo de lo normal. ¿Estará molesto?";
                break;
            case AGUA:
                mensaje = name + " usa chorro (y tú eres el objetivo).";
                break;
            case ELECTRICO:
                mensaje = name + " está molesto y emite chispas.";
                break;
            case TIERRA:
                mensaje = name + " hace temblar la tierra.";
                break;
            default:
                mensaje = name + " expresa su emoción.";
                break;
        }
        System.out.println(mensaje);
        return mensaje;
    }

    @Override
    public String dormir(){
        hunger = Math.min(100, hunger + 10);
        happiness = Math.min(100, happiness + 25);
        String mensaje = name + " se acurruca a descansar... 😴";
        System.out.println(mensaje);
        return mensaje;
    }

    @Override
    public String hacerTruco(){
        hunger = Math.min(100, hunger + 15);
        happiness = Math.min(100, happiness + 20);
        String mensaje;
        switch(this.tipo) {
            case FUEGO:
                mensaje = name + " hace una pirueta y lanza chispas.";
                break;
            case AGUA:
                mensaje = name + " hace un chorro de agua.";
                break;
            case ELECTRICO:
                mensaje = name + " hace un rayo.";
                break;
            case TIERRA:
                mensaje = name + " se revuelca en la tierra.";
                break;
            default:
                mensaje = name + " realiza un truco.";
                break;
        }
        System.out.println(mensaje);
        return mensaje;
    }

}
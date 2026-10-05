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

    public void alimentar(int cantidadBayas){
        hunger = Math.max(0, hunger - (8 * cantidadBayas));
        happiness = Math.min(100, happiness + (4 * cantidadBayas));
        System.out.println("Pikachu consumió " + cantidadBayas + " bayas Arandano.");
    }

    public void atacarEspecial(){
        hunger = Math.min(100, hunger + 20);

        switch(this.tipo) {
            case FUEGO:
                System.out.println(name + " usa Lanzallamas!");
                break;
            case AGUA:
                System.out.println(name + " usa Chorro!");
                break;
            case ELECTRICO:
                System.out.println(name + " usa Impactrueno!");
                break;
            case TIERRA:
                System.out.println(name + " usa Terremoto!");
                break;
        }

        int numeroAleatorio = (int) (Math.random() * 100) + 1;
        if (numeroAleatorio <= 50) {
            happiness = Math.min(100, happiness + 30);
            System.out.println("Ataque exitoso! Felicidad: " + happiness);
        } else {
            happiness = Math.max(0, happiness - 10);
            System.out.println("El ataque falló. Felicidad: " + happiness);
        }

    }

    public void expresarEmocion(){
        happiness = happiness - 15;
        switch(this.tipo) {
            case FUEGO:
                System.out.println(name + " Esta mas rojo de lo normal. Estara molesto?");
                break;
            case AGUA:
                System.out.println(name + " Usa chorro (y tu eres el objetivo)");
                break;
            case ELECTRICO:
                System.out.println(name + " esta molesto y emite chispas");
                break;
            case TIERRA:
                System.out.println(name + " hace temblar la tierra");
                break;
        }
    }
    
    public void hacerTruco(){
        switch(this.tipo) {
            case FUEGO:
                System.out.println(name + " Saca dos cigarros y los enciende *te ofrece uno*");
                break;
            case AGUA:
                System.out.println(name + " hace un chorro");
                break;
            case ELECTRICO:
                System.out.println(name + " hace un rayo");
                break;
            case TIERRA:
                System.out.println(name + " se revuelca en la tierra");
                break;
        }
    }

}
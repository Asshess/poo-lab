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

    public void atacarEspecial(){
        switch(this.tipo) {
            case FUEGO:
                System.out.println(name + " usa Lanzallamas!");
                break;
            case AGUA:
                System.out.println(name + " usa Pistola Agua!");
                break;
            case ELECTRICO:
                System.out.println(name + " usa Impactrueno!");
                break;
            case TIERRA:
                System.out.println(name + " usa Terremoto!");
                break;
        }
    }
    
    public void expresarEmocion(){
        switch(this.tipo) {
            case FUEGO:
                System.out.println(name + " hace chispitas");
                break;
            case AGUA:
                System.out.println(name + " te moja");
                break;
            case ELECTRICO:
                System.out.println(name + " te da un ZAP");
                break;
            case TIERRA:
                System.out.println(name + " hace temblar la tierra");
                break;
        }
    }
    
    public void hacerTruco(){
        switch(this.tipo) {
            case FUEGO:
                System.out.println(name + " procede a sacar dos cigarros y los enciende *te ofrece uno*");
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
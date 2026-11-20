package main.java;

public abstract class Mascota {
    
    protected String name;
    protected int age;
    protected int hunger;
    protected int happiness;

    // constructor
    public Mascota(String name, int age) {
        this.name = name;
        this.age = age;
        this.hunger = 50;
        this.happiness = 50;
    }

    // get & sets
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getHunger() {
        return hunger;
    }

    public int getHappiness() {
        return happiness;
    }

    // override de alimentar
    public String alimentar() {
        hunger = Math.max(0, hunger - 10);
        happiness = Math.min(100, happiness + 5);
        String mensaje = name + " ha comido su ración básica.";
        System.out.println(mensaje);
        return mensaje;
    }

    // contrato abstracto y override para alimentar con bayas
    public abstract String alimentar(int cantidadBayas);

    // acciones polimorficas específicas de la especie (pokemon)
    public abstract String atacarEspecial();

    public abstract String expresarEmocion();

    public abstract String dormir();

    public abstract String hacerTruco();

}

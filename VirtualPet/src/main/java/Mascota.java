package main.java;

public abstract class Mascota {
    
    protected String name;
    protected int age;
    protected int hunger;
    protected int happiness;

    public Mascota(String name, int age) {
        this.name = name;
        this.age = age;
        this.hunger = 50;
        this.happiness = 50;
    }

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

    public String alimentar() {
        hunger = Math.max(0, hunger - 10);
        happiness = Math.min(100, happiness + 5);
        String mensaje = name + " ha comido su ración básica.";
        System.out.println(mensaje);
        return mensaje;
    }

    public abstract String alimentar(int cantidadBayas);

    public abstract String atacarEspecial();

    public abstract String expresarEmocion();

    public abstract String dormir();

    public abstract String hacerTruco();

    public void jugar() {
        happiness = Math.min(100, happiness + 10);
        hunger = Math.min(100, hunger + 5);
    }

}

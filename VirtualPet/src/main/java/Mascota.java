package main.java;

public abstract class Mascota {
    
    private String name;
    private int age;
    private int hunger;
    private int happiness;

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

    public void feed() {
        hunger = Math.max(0, hunger - 10);
        happiness = Math.min(100, happiness + 5);
    }

    public void play() {
        happiness = Math.min(100, happiness + 10);
        hunger = Math.min(100, hunger + 5);
    }
}

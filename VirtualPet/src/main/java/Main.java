package main.java;

public class Main {
    public static void main(String[] args) {
        Mascota pet = new Mascota("Pikachu", 2);
        pet.feed();
        pet.play();

        System.out.println("Pet name: " + pet.getName());
        System.out.println("Age: " + pet.getAge());
        System.out.println("Hunger: " + pet.getHunger());
        System.out.println("Happiness: " + pet.getHappiness());
    }
}


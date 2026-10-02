package main.java;

public class Main {
    public static void main(String[] args) {
        Pet pet = new Pet("Pikachu", 2);
        pet.feed();
        pet.play();

        System.out.println("Pet name: " + pet.getName());
        System.out.println("Age: " + pet.getAge());
        System.out.println("Hunger: " + pet.getHunger());
        System.out.println("Happiness: " + pet.getHappiness());
    }
}


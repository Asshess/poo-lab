package main.java;

public class Main {
    public static void main(String[] args) {
        
        Pokemon pokemon = new Pokemon(
            "Pikachu",
            3,
            Pokemon.TipoElemento.ELECTRICO,
            80,
            1
        );

        System.out.println("Nombre: " + pokemon.getName());
        System.out.println("Tipo: " + pokemon.getTipo());
        System.out.println("Poder: " + pokemon.getPuntosPoder());

        pokemon.atacarEspecial();
        pokemon.expresarEmocion();
        pokemon.hacerTruco();
    }
}


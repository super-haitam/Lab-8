package main.java.example.HashMapChallengeATextBasedAdventure;

public class Main {

    public static void main(String[] args) {
        AdventureGame game = new AdventureGame();

        // Exemple de personnalisation (Bonus) : ajout d'un lieu et d'un passage depuis "lake"
        game.addLocation("cavern", "standing at the entrance of a mysterious glowing cavern");
        game.connect("lake", "E", "cavern");
        game.connect("cavern", "W", "lake");

        game.play();
    }
}

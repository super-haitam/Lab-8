package main.java.example.HashMapChallengeATextBasedAdventure;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdventureGame {

    private static final Map<String, String> DIRECTION_NAMES = Map.of(
            "N", "North",
            "S", "South",
            "E", "East",
            "W", "West"
    );

    // HashMap principale des lieux du plateau, indexée par identifiant court
    private final Map<String, Location> boardLocations = new HashMap<>();
    private String currentLocationKey;

    public AdventureGame() {
        setupDefaultMap();
        this.currentLocationKey = "road";
    }

    private void setupDefaultMap() {
        addLocation("road", "standing at the end of a road before a small brick building");
        addLocation("hill", "standing on top of a gentle hill overlooking the countryside");
        addLocation("well house", "standing inside a well house for a small spring");
        addLocation("valley", "in a pleasant valley beside a small stream");
        addLocation("forest", "in a dense, quiet forest filled with tall trees");
        addLocation("lake", "standing beside a calm, clear lake");
        addLocation("stream", "near a stream with a rocky bed");

        // Connexions de la carte conceptuelle
        connect("road", "N", "forest");
        connect("road", "S", "valley");
        connect("road", "E", "well house");
        connect("road", "W", "hill");

        connect("hill", "E", "road");
        connect("hill", "N", "forest");

        connect("forest", "S", "road");
        connect("forest", "W", "hill");
        connect("forest", "E", "lake");

        connect("lake", "W", "forest");
        connect("lake", "S", "well house");

        connect("well house", "W", "road");
        connect("well house", "N", "lake");
        connect("well house", "S", "stream");

        connect("stream", "N", "well house");
        connect("stream", "W", "valley");

        connect("valley", "N", "road");
        connect("valley", "E", "stream");
    }

    // --- Méthodes de personnalisation (Bonus) ---

    public void addLocation(String key, String description) {
        if (key == null || key.isBlank()) {
            return;
        }
        boardLocations.put(key.trim().toLowerCase(), new Location(description));
    }

    public void updateLocationDescription(String key, String newDescription) {
        Location loc = boardLocations.get(key.trim().toLowerCase());
        if (loc != null) {
            loc.setDescription(newDescription);
        }
    }

    public void connect(String fromKey, String direction, String toKey) {
        String from = fromKey.trim().toLowerCase();
        String to = toKey.trim().toLowerCase();
        Location origin = boardLocations.get(from);
        if (origin != null && boardLocations.containsKey(to)) {
            origin.addExit(direction, to);
        }
    }

    public void disconnect(String fromKey, String direction) {
        Location origin = boardLocations.get(fromKey.trim().toLowerCase());
        if (origin != null) {
            origin.removeExit(direction);
        }
    }

    public void setStartingLocation(String key) {
        String normalized = key.trim().toLowerCase();
        if (boardLocations.containsKey(normalized)) {
            this.currentLocationKey = normalized;
        }
    }

    // --- Boucle de jeu ---

    public void play() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                Location current = boardLocations.get(currentLocationKey);
                if (current == null) {
                    System.out.println("Current location not found. Exiting game.");
                    break;
                }

                System.out.println("***");
                System.out.println("You're " + current.getDescription());
                System.out.println("From here, you can see:");

                for (Map.Entry<String, String> exit : current.getNextPlaces().entrySet()) {
                    String dirCode = exit.getKey();
                    String targetKey = exit.getValue();
                    String dirLabel = DIRECTION_NAMES.getOrDefault(dirCode, dirCode);
                    System.out.println("• A " + targetKey + " to the " + dirLabel + " (" + dirCode + ")");
                }

                System.out.print("Select Your Compass Direction (Q to quit) >> ");
                if (!scanner.hasNextLine()) {
                    break;
                }

                String input = scanner.nextLine().trim().toUpperCase();
                if (input.equals("Q")) {
                    System.out.println("Thanks for playing!");
                    break;
                }

                String nextKey = current.getNextPlaces().get(input);
                if (nextKey != null && boardLocations.containsKey(nextKey)) {
                    currentLocationKey = nextKey;
                } else {
                    System.out.println("You can't go in that direction from here.");
                }
            }
        }
    }
}

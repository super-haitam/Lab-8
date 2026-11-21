package main.java.example.HashMapChallengeATextBasedAdventure;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Location {

    private String description;
    private final Map<String, String> nextPlaces;

    public Location(String description) {
        this.description = description;
        this.nextPlaces = new LinkedHashMap<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getNextPlaces() {
        return nextPlaces;
    }

    public void addExit(String direction, String targetLocationKey) {
        if (direction == null || targetLocationKey == null) {
            return;
        }
        String normalizedDir = direction.trim().toUpperCase();
        String normalizedTarget = targetLocationKey.trim().toLowerCase();
        nextPlaces.put(normalizedDir, normalizedTarget);
    }

    public void removeExit(String direction) {
        if (direction != null) {
            nextPlaces.remove(direction.trim().toUpperCase());
        }
    }
}

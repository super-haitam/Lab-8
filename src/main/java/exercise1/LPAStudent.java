package main.java.exercise1;

import java.util.Comparator;

public class LPAStudent extends Student {

    private final double percentComplete;

    public LPAStudent(String name, String course, int yearStarted, double percentComplete) {
        super(name, course, yearStarted);
        this.percentComplete = percentComplete;
    }

    public double getPercentComplete() {
        return percentComplete;
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        if ("PERCENTCOMPLETE".equalsIgnoreCase(fieldName)) {
            try {
                double maxPercent = Double.parseDouble(value.trim());
                return this.percentComplete <= maxPercent;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return super.matchFieldValue(fieldName, value);
    }

    @Override
    public String toString() {
        return "%s | %.1f%%".formatted(super.toString(), percentComplete);
    }

    public static Comparator<LPAStudent> byIdThenPercentComplete() {
        return Comparator.comparingInt(LPAStudent::getId)
                .thenComparingDouble(LPAStudent::getPercentComplete);
    }
}

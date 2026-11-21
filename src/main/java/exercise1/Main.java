package main.java.exercise1;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Main {

    private static final String[] NAMES = {
            "Alice", "Bob", "Charlie", "Diana", "Ethan", "Fiona", "George", "Hannah"
    };
    private static final String[] COURSES = {
            "Computer Science", "Mathematics", "Data Science", "Software Engineering"
    };

    public static void main(String[] args) {
        Random random = new Random(42);
        QueryList<LPAStudent> students = new QueryList<>();

        for (int i = 0; i < 25; i++) {
            String name = NAMES[random.nextInt(NAMES.length)] + "_" + (i + 1);
            String course = COURSES[random.nextInt(COURSES.length)];
            int year = 2021 + random.nextInt(6);
            double percentComplete = Math.round(random.nextDouble() * 1000.0) / 10.0;
            students.add(new LPAStudent(name, course, year, percentComplete));
        }

        QueryList<LPAStudent> filtered = students.getMatches("PERCENTCOMPLETE", "50");

        System.out.println("--- Filtered (<= 50% complete): Sorted by Natural Order (ID) ---");
        List<LPAStudent> byNaturalOrder = new QueryList<>(filtered);
        byNaturalOrder.sort(Comparator.naturalOrder());
        byNaturalOrder.forEach(System.out::println);

        System.out.println("\n--- Filtered (<= 50% complete): Sorted by Custom Comparator (ID, then % Complete) ---");
        List<LPAStudent> byCustomComparator = new QueryList<>(filtered);
        byCustomComparator.sort(LPAStudent.byIdThenPercentComplete());
        byCustomComparator.forEach(System.out::println);
    }
}

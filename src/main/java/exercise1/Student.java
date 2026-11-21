package main.java.exercise1;

import java.util.Comparator;
import java.util.Objects;

public class Student implements QueryItem, Comparable<Student> {

    private static int lastId = 1000;

    private final int id;
    private final String name;
    private final String course;
    private final int yearStarted;

    public Student(String name, String course, int yearStarted) {
        this.id = ++lastId;
        this.name = name;
        this.course = course;
        this.yearStarted = yearStarted;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public int getYearStarted() {
        return yearStarted;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        if (fieldName == null || value == null) {
            return false;
        }
        return switch (fieldName.toUpperCase()) {
            case "ID" -> String.valueOf(id).equalsIgnoreCase(value.trim());
            case "NAME" -> name.equalsIgnoreCase(value.trim());
            case "COURSE" -> course.equalsIgnoreCase(value.trim());
            case "YEAR" -> String.valueOf(yearStarted).equalsIgnoreCase(value.trim());
            default -> false;
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ID=%d | Name=%s | Course=%s | Year=%d".formatted(id, name, course, yearStarted);
    }

    public static Comparator<Student> byCourseThenId() {
        return Comparator.comparing(Student::getCourse, String.CASE_INSENSITIVE_ORDER)
                .thenComparingInt(Student::getId);
    }
}

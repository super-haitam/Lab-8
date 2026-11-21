package main.java.exercise5;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        UnaryOperator[] operators = new UnaryOperator[3];
        String[] comments = new String[3];

        // Upper Case
        comments[0] = "Upper Case";
        operators[0] = (UnaryOperator<String>) String::toUpperCase;

        // Random Initial and period
        Random random = new Random();
        comments[1] = "Middle Initial";
        operators[1] = (UnaryOperator<String>) name -> {
            char initial = (char) (random.nextInt(26) + 'A');
            return name + " " + initial + ".";
        };

        // Last Name
        comments[2] = "Last Name";
        operators[2] = (UnaryOperator<String>) name -> {
            String firstName = name.split(" ")[0];
            String lastName = new StringBuilder(firstName).reverse().toString();
            return firstName + " " + lastName;
        };

        // Array of names
        String[] fNames = {"hAItAm", "AnAsS", "hIbA", "mohAmeD", "boB"};
        for (int i = 0; i < operators.length; i++) {
            UnaryOperator<String> op = operators[i];
            Arrays.setAll(fNames, j -> op.apply(fNames[j]));

            System.out.println("\n" + (i+1) + ". " + comments[i]);
            Arrays.asList(fNames).forEach(System.out::println);
        }
    }
}

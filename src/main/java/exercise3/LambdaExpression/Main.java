package main.java.exercise3.LambdaExpression;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String[] fNames = {"hAItAm", "AnAsS", "hIbA", "mohAmeD", "boB"};

        Arrays.setAll(fNames, i -> fNames[i].toUpperCase());

        System.out.println("1. All Uppercase:");
        Arrays.asList(fNames).forEach(System.out::println);

        Random random = new Random();
        Arrays.setAll(fNames, i -> {
            char middleInitial = (char) (random.nextInt(26) + 'A');
            return fNames[i] + " " + middleInitial + ".";
        });

        System.out.println("\n2. With Middle Initials:");
        Arrays.asList(fNames).forEach(n -> System.out.println(n));

        Arrays.setAll(fNames, i -> {
            String currentName = fNames[i];

            String firstName = currentName.split(" ")[0];
            String lastName = new StringBuilder(firstName).reverse().toString();

            return firstName + " " + lastName;
        });

        System.out.println("\n3. With Reversed Last Names:");
        Arrays.asList(fNames).forEach(n -> System.out.println(n));
    }
}

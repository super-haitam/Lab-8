package main.java.exercise4;

import java.util.Locale;

@FunctionalInterface
interface StringManipulator {
    // Single abstract method that takes
    // a String and returns a modified String
    String manipulate(String input);
}

public class Main {
    // 3. A method that applies lambda on input
    static String manipulateString(StringManipulator manipulator, String input) {
        return manipulator.manipulate(input);
    }

    public static void main(String[] args) {
        // 1. Converts the input string to uppercase
        StringManipulator toUpperCase = String::toUpperCase;

        // 2. Reverses the input string
        StringManipulator reverse = input -> new StringBuilder(input).reverse().toString();

        // Applying the method
        String originalText = "Hello World";

        String upperResult = manipulateString(toUpperCase, originalText);
        System.out.println("Uppercase: " + upperResult);
        // Output: HELLO WORLD

        String reverseResult = manipulateString(reverse, originalText);
        System.out.println("Reversed: " + reverseResult);
        // Output: dlroW olleH

        String excitedResult = manipulateString(input -> input + "!!!", originalText);
        System.out.println("Excited: " + originalText);
        // Output: Hello World!!!
    }
}

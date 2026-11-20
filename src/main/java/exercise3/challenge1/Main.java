package main.java.exercise3.challenge1;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<String> printTheParts = new Consumer<String>() {
            @Override
            public void accept(String sentence) {
                String[] parts = sentence.split(" ");
                for (String part : parts) {
                    System.out.println(part);
                }
            }
        };

        // Converted to Lambda Expression
        Consumer<String> lambda = sentence -> {
            String[] parts = sentence.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };
    }
}

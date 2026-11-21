package main.java.exercise3.challenge6;

import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Supplier<String> iLoveJava = () -> "I love Java";
        System.out.println(iLoveJava.get());
    }
}

package main.java.exercise3.challenge7;

import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        String supplierResult = "I love Java";

        Supplier<String> iLoveJava = () -> supplierResult;
        System.out.println(iLoveJava.get());
        // Output : I love Java
    }
}

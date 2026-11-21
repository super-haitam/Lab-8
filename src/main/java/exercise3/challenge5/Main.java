package main.java.exercise3.challenge5;

import java.util.function.UnaryOperator;

public class Main {

    public static String everySecondCharacter(UnaryOperator<String> operator, String source) {
        return operator.apply(source);
    }

    public static void main(String[] args) {

        UnaryOperator<String> everySecondCharLambda = source -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };

        String result = everySecondCharacter(everySecondCharLambda, "1234567890");
        System.out.println(result);
    }
}

package main.java.exercise3.challenge4;

import java.util.function.UnaryOperator;

public class Main {
    static String everySecondCharacter(UnaryOperator<String> op, String s) {
        return op.apply(s);
    }

    public static void main(String[] args) {
        UnaryOperator<String> everySecondChar = source -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }
            return returnVal.toString();
        };

        String output = everySecondCharacter(everySecondChar, "1234567890");
        System.out.println(output);
        // Output : 24680
    }
}

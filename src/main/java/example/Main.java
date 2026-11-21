package main.java.example;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Theatre theatre = new Theatre("Grand Théâtre", 4, 20); // 4 rangées (A-D), 5 sièges par rangée

        System.out.println("Réservation A005 : " + theatre.reserveSeat("A005"));
        System.out.println("Réservation A005 à nouveau : " + theatre.reserveSeat("A005"));

        List<String> groupBooking = theatre.reserveSeats(3, 'A', 'C', 1, 4);
        System.out.println("Réservation groupée (A-C, sièges 1 à 4, x3) : " + groupBooking);

        System.out.println();
        theatre.printSeatMap();
    }
}

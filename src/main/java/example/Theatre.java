package main.java.example;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;

public class Theatre {

    private final String theatreName;
    private final int seatsPerRow;
    private final TreeSet<Seat> seats;

    public Theatre(String theatreName, int rows, int totalSeats) {
        if (rows < 1 || rows > 26) {
            throw new IllegalArgumentException("Le nombre de rangées doit être compris entre 1 et 26.");
        }
        if (totalSeats < rows || totalSeats % rows != 0) {
            throw new IllegalArgumentException(
                    "Le nombre total de sièges doit être divisible uniformément par le nombre de rangées."
            );
        }

        this.theatreName = theatreName;
        this.seatsPerRow = totalSeats / rows;
        this.seats = new TreeSet<>();

        for (int r = 0; r < rows; r++) {
            char rowChar = (char) ('A' + r);
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                this.seats.add(new Seat(rowChar, seatNum));
            }
        }
    }

    public void printSeatMap() {
        System.out.println("=== Plan des sièges : " + theatreName + " ===");
        Character currentRow = null;

        for (Seat seat : seats) {
            if (currentRow == null || currentRow != seat.row) {
                if (currentRow != null) {
                    System.out.println();
                }
                currentRow = seat.row;
                System.out.print("Rangée " + currentRow + " : ");
            }
            String status = seat.reserved ? "[X]" : "[ ]";
            System.out.print(seat.seatCode + status + " ");
        }
        if (!seats.isEmpty()) {
            System.out.println();
        }
    }

    public boolean reserveSeat(String seatCode) {
        if (seatCode == null) {
            return false;
        }
        String targetCode = seatCode.trim().toUpperCase();
        for (Seat seat : seats) {
            if (seat.seatCode.equals(targetCode)) {
                if (seat.reserved) {
                    return false;
                }
                seat.reserved = true;
                return true;
            }
        }
        return false;
    }

    /**
     * Réserve un nombre donné de sièges disponibles dans une plage de rangées
     * [minRow, maxRow] et une plage de numéros de sièges [minSeatNumber, maxSeatNumber].
     * Retourne la liste des codes de sièges réservés (vide si impossible d'honorer la demande).
     */
    public List<String> reserveSeats(int count, char minRow, char maxRow,
                                     int minSeatNumber, int maxSeatNumber) {
        char startRow = Character.toUpperCase(minRow);
        char endRow = Character.toUpperCase(maxRow);

        if (count <= 0 || startRow > endRow || minSeatNumber < 1 || maxSeatNumber < minSeatNumber) {
            return List.of();
        }

        List<Seat> candidates = new ArrayList<>();
        for (Seat seat : seats) {
            if (seat.row >= startRow && seat.row <= endRow
                    && !seat.reserved
                    && seat.number >= minSeatNumber && seat.number <= maxSeatNumber) {
                candidates.add(seat);
                if (candidates.size() == count) {
                    break;
                }
            }
        }

        if (candidates.size() < count) {
            return List.of();
        }

        List<String> reservedCodes = new ArrayList<>(count);
        for (Seat seat : candidates) {
            seat.reserved = true;
            reservedCodes.add(seat.seatCode);
        }
        return reservedCodes;
    }

    public NavigableSet<Seat> getSeats() {
        return seats;
    }

    public static class Seat implements Comparable<Seat> {

        private final char row;
        private final int number;
        private final String seatCode;
        private boolean reserved;

        public Seat(char row, int number) {
            this.row = Character.toUpperCase(row);
            this.number = number;
            this.seatCode = "%c%03d".formatted(this.row, this.number);
            this.reserved = false;
        }

        public String getSeatCode() {
            return seatCode;
        }

        public boolean isReserved() {
            return reserved;
        }

        @Override
        public int compareTo(Seat other) {
            return this.seatCode.compareTo(other.seatCode);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Seat seat)) return false;
            return Objects.equals(seatCode, seat.seatCode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(seatCode);
        }

        @Override
        public String toString() {
            return seatCode + (reserved ? " (réservé)" : " (libre)");
        }
    }
}

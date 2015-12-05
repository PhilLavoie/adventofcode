package day3;

public class Main {

    public static void main(String[] args) {

        GiftRecorder giftRecorder = new GiftRecorder();

        int noVisitedHouses = giftRecorder.visitedHousesForDirections(args[0]);

        System.out.println("number of houses visited: " + noVisitedHouses);

    }
}

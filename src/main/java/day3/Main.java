package day3;

public class Main {

    public static void main(String[] args) {

        VisitedHouseRecorder visitedHouseRecorder = new VisitedHouseRecorder();

        int noHousesVisitedBySanta =
            visitedHouseRecorder.visitedHousesBySantaForDirections(args[0]);
        System.out.println("number of houses visited: " + noHousesVisitedBySanta);

        int noHousesVisitedBySantaAndRoboSanta =
            visitedHouseRecorder.visitedHousesBySantaAndRoboSantaForDirections(args[0]);
        System.out.println("number of houses visited by Santa & Robo Santa: "
            + noHousesVisitedBySantaAndRoboSanta);
    }
}

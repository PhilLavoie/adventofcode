package day_one;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("expected only one argument");
        }

        FloorWalker floorWalker = new FloorWalker();
        FloorWalkingResult result = floorWalker.walk(args[0]);

        System.out.println("Floor: " + result.getFloor());
        System.out.println("First basement index: " + result.getFirstBasementIndex());

    }
}

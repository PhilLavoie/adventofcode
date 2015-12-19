package day3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import static util.iterables.StringIterable.iterableOf;

public class VisitedHouseRecorder {

    private static final List<Character> validDirections = Arrays.asList('<', '>', '^', 'v');

    private final Comparator<Coordinates> coordinatesComparator;

    public VisitedHouseRecorder() {
        coordinatesComparator = (coordinates1, coordinates2) -> {
            int x1 = coordinates1.getX();
            int x2 = coordinates2.getX();
            if (x1 != x2) {
                return (x1 - x2);
            }

            return coordinates1.getY() - coordinates2.getY();
        };
    }

    public int visitedHousesBySantaForDirections(String directions) {
        checkDirections(directions);

        TreeSet<Coordinates> visitedCoordinates = new TreeSet<>(coordinatesComparator);

        Coordinates currentPosition = new Coordinates(0, 0);
        visitedCoordinates.add(currentPosition);

        for (char direction : iterableOf(directions)) {
            Coordinates nextPosition = nextCoordinates(currentPosition, direction);
            visitedCoordinates.add(nextPosition);
            currentPosition = nextPosition;
        }

        return visitedCoordinates.size();
    }

    public int visitedHousesBySantaAndRoboSantaForDirections(String directions) {
        checkDirections(directions);

        TreeSet<Coordinates> visitedCoordinates = new TreeSet<>(coordinatesComparator);

        Coordinates santaPosition = new Coordinates(0, 0);
        Coordinates roboSantaPosition = santaPosition;
        visitedCoordinates.add(santaPosition);

        boolean isSantasTurn = true;

        for (char direction : iterableOf(directions)) {
            Coordinates nextPosition;
            if (isSantasTurn) {
                nextPosition = nextCoordinates(santaPosition, direction);
                santaPosition = nextPosition;
            } else {
                nextPosition = nextCoordinates(roboSantaPosition, direction);
                roboSantaPosition = nextPosition;
            }
            visitedCoordinates.add(nextPosition);
            isSantasTurn = !isSantasTurn;
        }

        return visitedCoordinates.size();
    }

    private Coordinates nextCoordinates(Coordinates start, char movement) {
        switch (movement) {
            case '>':
                return new Coordinates(start.getX() + 1, start.getY());
            case '<':
                return new Coordinates(start.getX() - 1, start.getY());
            case '^':
                return new Coordinates(start.getX(), start.getY() + 1);
            case 'v':
                return new Coordinates(start.getX(), start.getY() - 1);
        }
        throw new IllegalArgumentException("unknown character: " + movement);
    }

    private void checkDirection(char direction) {
        if (!validDirections.contains(direction)) {
            throw new IllegalArgumentException("invalid character: " + direction);
        }
    }

    private void checkDirection(int direction) {
        checkDirection((char) direction);
    }

    private void checkDirections(String directions) {
        directions.chars().forEach(this::checkDirection);
    }
}

package day6;

import day6.part1.Part1Grid;
import util.Procedure;

import java.util.Iterator;

public class CommandMapper {

    private final Grid<?> grid;

    public CommandMapper(Grid<?> grid) {
        this.grid = grid;
    }

    public Procedure commandFor(Iterator<String> words) {
        String firstWord = words.next();
        switch (firstWord) {
            case "turn":
                return turnCommandFor(words);
            case "toggle":
                return toggleCommandFor(words);
            default:
                throw new IllegalArgumentException("unknown command: " + firstWord);
        }
    }

    private Procedure toggleCommandFor(Iterator<String> words) {
        Interval interval = intervalOf(words);
        return () -> grid.toggle(interval);
    }

    private Procedure turnCommandFor(Iterator<String> words) {
        String turnDirection = words.next();
        Interval interval;
        switch (turnDirection) {
            case "on":
                interval = intervalOf(words);
                return () -> grid.turnOn(interval);
            case "off":
                interval = intervalOf(words);
                return () -> grid.turnOff(interval);
            default:
                throw new IllegalArgumentException("uknown turn direction: " + turnDirection);
        }
    }

    private Interval intervalOf(Iterator<String> words) {
        String[] firstCoordinate = words.next().split(",");
        int x1 = Integer.parseInt(firstCoordinate[0]);
        int y1 = Integer.parseInt(firstCoordinate[1]);

        String through = words.next();
        if (!"through".equals(through)) {
            throw new IllegalArgumentException("uknown coordinates separator: " + through);
        }

        String[] secondCoordinate = words.next().split(",");
        int x2 = Integer.parseInt(secondCoordinate[0]);
        int y2 = Integer.parseInt(secondCoordinate[1]);

        return Interval.interval(x1, y1, x2, y2);
    }
}

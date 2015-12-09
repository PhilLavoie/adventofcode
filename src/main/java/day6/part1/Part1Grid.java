package day6.part1;

import day6.Grid;
import day6.Interval;

import java.util.function.Function;

import static com.google.common.base.Preconditions.checkElementIndex;

public class Part1Grid extends Grid<Boolean> {

    public Part1Grid(Boolean[][] lights) {
        super(lights);
    }

    public void toggle(Interval interval) {
        applyOver(b -> !b, interval);
    }

    public void turnOn(Interval interval) {
        applyOver(b -> true, interval);
    }

    public void turnOff(Interval interval) {
        applyOver(b -> false, interval);
    }

    public int getNumberOfLightsOn() {
        final int[] noLightsOn = {0};

        applyOver(b -> {
            if (b) {
                noLightsOn[0]++;
            }
            return b;
        }, Interval.interval(0, 0, noRows - 1, noCols - 1));

        return noLightsOn[0];
    }
}


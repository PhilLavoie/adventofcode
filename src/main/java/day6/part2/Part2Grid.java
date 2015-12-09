package day6.part2;

import day6.Grid;
import day6.Interval;

import java.util.function.Function;

import static com.google.common.base.Preconditions.checkElementIndex;

public class Part2Grid extends Grid<Integer> {

    public Part2Grid(Integer[][] lights) {
        super(lights);
    }

    public void toggle(Interval interval) {
        applyOver(i -> i + 2, interval);
    }

    public void turnOn(Interval interval) {
        applyOver(i -> i + 1, interval);
    }

    public void turnOff(Interval interval) {
        applyOver(i -> i > 0 ? i - 1 : 0, interval);
    }

    public int getTotalBrightness() {
        final int[] total = {0};

        applyOver(i -> {
            total[0] += i;
            return i;
        }, Interval.interval(0, 0, noRows - 1, noCols - 1));

        return total[0];
    }
}

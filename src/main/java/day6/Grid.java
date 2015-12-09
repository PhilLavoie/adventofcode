package day6;

import java.util.function.Function;

import static com.google.common.base.Preconditions.checkElementIndex;

public abstract class Grid<T> {

    private final T[][] lights;
    protected final int noCols;
    protected final int noRows;

    public Grid(T[][] lights) {
        this.lights = lights;
        noRows = lights.length;
        noCols = lights[0].length;
    }

    public abstract void toggle(Interval interval);

    public abstract void turnOn(Interval interval);

    public abstract void turnOff(Interval interval);

    public void applyOver(Function<T, T> function, Interval interval) {
        checkInterval(interval);
        for (int i = interval.getX1(); i <= interval.getX2(); i++) {
            for (int j = interval.getY1(); j <= interval.getY2(); j++) {
                lights[i][j] = function.apply(lights[i][j]);
            }
        }
    }

    private void checkInterval(Interval interval) {
        checkElementIndex(interval.getX1(), noRows);
        checkElementIndex(interval.getX2(), noRows);
        checkElementIndex(interval.getY1(), noCols);
        checkElementIndex(interval.getY2(), noCols);
    }
}

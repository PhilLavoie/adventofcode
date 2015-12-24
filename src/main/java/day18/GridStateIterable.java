package day18;

import java.util.Iterator;

public class GridStateIterable implements Iterable<Grid<Light>> {

    private final Grid<Light> initialState;

    public GridStateIterable(Grid<Light> initialState) {
        this.initialState = initialState;
    }

    @Override public Iterator<Grid<Light>> iterator() {
        return new GridStateIterator(initialState);
    }
}

package day18;

import java.util.Iterator;

public class GridStateIterator implements Iterator<Grid<Light>> {
    private Grid<Light> currentState;
    private Grid<Light> nextState;

    public GridStateIterator(Grid<Light> initialState) {
        this.currentState = initialState;
        this.nextState = currentState.deepClone((light) -> light.clone());
    }

    @Override public boolean hasNext() {
        return true;
    }

    @Override public Grid<Light> next() {
        calculateNextState();
        switchStates();
        return currentState;
    }

    private void calculateNextState() {
        int rows = currentState.getNoRows();
        int cols = currentState.getNoColumns();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                nextState.get(i, j).turn(getNextStateFor(i, j));
            }
        }
    }

    private State getNextStateFor(int row, int col) {
        boolean isOn = currentState.get(row, col).isOn();

        int noAdjacentLightsOn = getNoAdjacentLightsOn(currentState, row, col);

        if (noAdjacentLightsOn == 3) {
            return State.ON;
        }

        if (isOn && noAdjacentLightsOn == 2) {
            return State.ON;
        }

        return State.OFF;
    }

    private int getNoAdjacentLightsOn(Grid<Light> currentState, int row, int col) {
        int noAdjacentLightsOn = 0;

        if (isOnAt(currentState, row - 1, col - 1)) {
            noAdjacentLightsOn++;
        }

        if (isOnAt(currentState, row, col - 1)) {
            noAdjacentLightsOn++;
        }

        if (isOnAt(currentState, row + 1, col - 1)) {
            noAdjacentLightsOn++;
        }

        if (isOnAt(currentState, row - 1, col)) {
            noAdjacentLightsOn++;
        }

        if (isOnAt(currentState, row + 1, col)) {
            noAdjacentLightsOn++;
        }

        if (isOnAt(currentState, row - 1, col + 1)) {
            noAdjacentLightsOn++;
        }

        if (isOnAt(currentState, row, col + 1)) {
            noAdjacentLightsOn++;
        }

        if (isOnAt(currentState, row + 1, col + 1)) {
            noAdjacentLightsOn++;
        }

        return noAdjacentLightsOn;
    }

    private boolean isOnAt(Grid<Light> grid, int row, int col) {
        if (row < 0 || row >= grid.getNoRows()) {
            return false;
        }

        if (col < 0 || col >= grid.getNoColumns()) {
            return false;
        }

        return grid.get(row, col).isOn();
    }

    private void switchStates() {
        Grid temp = currentState;
        currentState = nextState;
        nextState = temp;
    }


}

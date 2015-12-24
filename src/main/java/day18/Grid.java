package day18;

import static com.google.common.base.Preconditions.checkElementIndex;

public class Grid<T> {

    private final T[][] content;
    private final int noRows;
    private final int noColumns;

    public Grid(T[][] content) {
        this.content = content;
        this.noRows = content.length;
        this.noColumns = content[0].length;
    }

    public T get(int row, int col) {
        checkIndexes(row, col);

        try {
            return content[row][col];
        } catch (Exception e) {
            throw new RuntimeException("error accessing element at (" + row + ", " + col + ")");
        }
    }

    public void set(int row, int col, T value) {
        checkIndexes(row, col);

        content[row][col] = value;
    }

    private void checkIndexes(int row, int col) {
        checkElementIndex(row, noRows);
        checkElementIndex(col, noColumns);
    }

    public int getNoRows() {
        return noRows;
    }

    public int getNoColumns() {
        return noColumns;
    }

    public Grid<T> deepClone(Cloner<T> cloner) {
        T[][] contentClone = this.content.clone();
        for (int i = 0; i < contentClone.length; i++) {
            contentClone[i] = content[i].clone();
            for (int j = 0; j < contentClone.length; j++) {
                contentClone[i][j] = cloner.apply(content[i][j]);
            }
        }
        return new Grid<T>(contentClone);
    }
}

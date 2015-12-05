package day3;

public class Coordinates {

    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override public boolean equals(Object obj) {
        if (!(obj instanceof Coordinates)) {
            return false;
        }
        Coordinates coordinates = (Coordinates) obj;

        return x == coordinates.x && y == coordinates.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

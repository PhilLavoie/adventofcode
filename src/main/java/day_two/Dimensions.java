package day_two;

import static java.lang.Long.parseLong;

public class Dimensions {

    private final long length;
    private final long width;
    private final long height;

    public Dimensions(long length, long width, long height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public static Dimensions fromString(String input) {
        String[] values = input.split("x");

        if (values.length != 3) {
            throw new IllegalArgumentException("invalid input string: " + input);
        }

        return new Dimensions(parseLong(values[0]), parseLong(values[1]), parseLong(values[2]));
    }

    public long getSurface() {
        return 2 * getFirstSideSurface() + 2 * getSecondSideSurface() + 2 * getThirdSideSurface();
    }

    private long getFirstSideSurface() {
        return length * width;
    }

    private long getSecondSideSurface() {
        return length * height;
    }

    private long getThirdSideSurface() {
        return width * height;
    }

    public long getVolume() {
        return length * width * height;
    }

    public long getLength() {
        return length;
    }

    public long getWidth() {
        return width;
    }

    public long getHeight() {
        return height;
    }
}

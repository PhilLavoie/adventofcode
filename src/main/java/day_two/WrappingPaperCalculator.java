package day_two;

public class WrappingPaperCalculator {

    public long wrap(Dimensions dimensions) {
        long surface = dimensions.getSurface();

        //Get the surface of the smaller side.
        //The small side is made of the two shortest sides.
        long[] smallestSideScalars = dimensions.getSmallestSideScalars();

        return surface + (smallestSideScalars[0] * smallestSideScalars[1]);
    }
}

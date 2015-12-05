package day2;

public class RibbonCalculator {

    public long putSomeRibbonOnMe(Dimensions dimensions) {
        long volume = dimensions.getVolume();

        long[] smallestSideScalar = dimensions.getSmallestSideScalars();
        long smallestSidePerimeter = 2 * smallestSideScalar[0] + 2 * smallestSideScalar[1];

        return volume + smallestSidePerimeter;
    }
}

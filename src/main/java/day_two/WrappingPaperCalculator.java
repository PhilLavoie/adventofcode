package day_two;

import java.util.stream.LongStream;

public class WrappingPaperCalculator {

    public long wrap(Dimensions dimensions) {
        long surface = dimensions.getSurface();

        //Get the surface of the smaller side.
        //The small side is made of the two shortest sides.
        long[] dimensionsScalars =
            {dimensions.getLength(), dimensions.getWidth(), dimensions.getHeight()};
        long max = LongStream.of(dimensionsScalars).max().getAsLong();

        long[] smallestSideScalars = new long[2];
        int scalarsIndex = 0;
        boolean maxFound = false;
        for (int i = 0; i < dimensionsScalars.length; i++) {
            long dimensionsScalar = dimensionsScalars[i];
            if (dimensionsScalar == max && !maxFound) {
                maxFound = true;
                continue;
            }
            smallestSideScalars[scalarsIndex] = dimensionsScalar;
            scalarsIndex++;
        }

        return surface + (smallestSideScalars[0] * smallestSideScalars[1]);
    }
}

package day_two;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        WrappingPaperCalculator calculator = new WrappingPaperCalculator();

        Stream<Dimensions> dimensionsStream = Stream.of(args).map(Dimensions::fromString);
        LongStream wrappingPaperStream = dimensionsStream.mapToLong(calculator::wrap);



        long totalPaperNeeded = wrappingPaperStream.sum();

        System.out.println("total paper needed: " + totalPaperNeeded);
    }
}

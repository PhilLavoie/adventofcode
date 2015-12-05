package day2;

import java.util.function.Supplier;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Supplier<Stream<Dimensions>> dimensionsSupplier = () -> Stream.of(args).map(Dimensions::fromString);

        WrappingPaperCalculator wrappingPaperCalculator = new WrappingPaperCalculator();
        LongStream wrappingPaperStream = dimensionsSupplier.get().mapToLong(wrappingPaperCalculator::wrap);
        long totalPaperNeeded = wrappingPaperStream.sum();
        System.out.println("total paper needed: " + totalPaperNeeded);

        RibbonCalculator ribbonCalculator = new RibbonCalculator();
        LongStream ribbonStream = dimensionsSupplier.get().mapToLong(ribbonCalculator::putSomeRibbonOnMe);
        long ribbonNeeded = ribbonStream.sum();
        System.out.println("total ribbon needed: " + ribbonNeeded);
    }
}

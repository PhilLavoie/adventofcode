package day5;

import java.util.stream.Stream;

public class Main {

    private static void part1(String[] args) {
        System.out.println("part 1");
        day5.part1.GoodStringMatcher matcher = new day5.part1.GoodStringMatcher();

        int stringCount = args.length;
        long goodStringsCount = Stream.of(args).filter(matcher).count();

        System.out.println("Number of strings: " + stringCount);
        System.out.println("Number of good strings: " + goodStringsCount);
    }

    private static void part2(String[] args) {
        System.out.println("part 2");
        day5.part2.GoodStringMatcher matcher = new day5.part2.GoodStringMatcher();

        int stringCount = args.length;
        long goodStringsCount = Stream.of(args).filter(matcher).count();

        System.out.println("Number of strings: " + stringCount);
        System.out.println("Number of good strings: " + goodStringsCount);
    }

    public static void main(String[] args) {
        part1(args);
        part2(args);
    }
}

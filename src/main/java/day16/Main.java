package day16;

import com.google.common.collect.ImmutableMap;

import java.io.*;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static util.iterables.StreamIterable.iterableOf;

public class Main {

    public static void main(String[] args) {

        FileReader reader;

        try {
            reader = new FileReader(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("could not find file: " + args[0]);
            return;
        }

        Iterable<AuntSue> auntsSue =
            new AuntsSuePossessionParser().parse(new BufferedReader(reader).lines()).collect(
                Collectors.toList());

        part1(auntsSue);
        part2(auntsSue);
    }

    private static void part2(Iterable<AuntSue> auntsSue) {
        System.out.println("part 1");

        Map<Possession, Integer> mfcsamResults = getMfcsamResults();

        AmountMatcher amountMatcher = part2Matcher();
        AuntSue theOne = findSueWith(amountMatcher, mfcsamResults, auntsSue);

        System.out.println("Corresponding aunt sue is: " + theOne.getIdentifier());
    }

    private static AmountMatcher part2Matcher() {

        return (possession, mfcsamReading, auntSueAmount) -> {
            switch (possession) {
                case CATS:
                case TREES:
                    return auntSueAmount > mfcsamReading;
                case POMERANIANS:
                case GOLDFISH:
                    return auntSueAmount < mfcsamReading;
                default:
                    return auntSueAmount == mfcsamReading;
            }
        };
    }

    private static void part1(Iterable<AuntSue> auntsSue) {
        System.out.println("part 1");

        Map<Possession, Integer> mfcsamResults = getMfcsamResults();

        AmountMatcher amountMatcher = part1Matcher();
        AuntSue theOne = findSueWith(amountMatcher, mfcsamResults, auntsSue);

        System.out.println("Corresponding aunt sue is: " + theOne.getIdentifier());
    }

    private static AuntSue findSueWith(AmountMatcher matcher, Map<Possession, Integer> mfcsamResults, Iterable<AuntSue> auntsSue) {
        AuntSue theOne = null;

        AuntSueSearch:
        for (AuntSue auntSue : auntsSue) {

            for (PossessionAmount possessionAmount : auntSue.getPossessionsAmounts()) {

                Possession possession = possessionAmount.getPossession();
                if (mfcsamResults.containsKey(possession)) {


                    if (!matcher
                        .areMatchingAmountsForPossession(possession,
                            mfcsamResults.get(possession),
                            possessionAmount.getAmount())) {
                        continue AuntSueSearch;
                    }
                }
            }
            //They all matched.
            theOne = auntSue;
        }

        return theOne;
    }

    private static AmountMatcher part1Matcher() {
        return ((possession, firstAmount, secondAmount) -> firstAmount == secondAmount);
    }

    private static Map<Possession, Integer> getMfcsamResults() {
        return new ImmutableMap.Builder<Possession, Integer>().put(Possession.CHILDREN, 3)
            .put(Possession.CATS, 7).put(Possession.SAMOYEDS, 2).put(Possession.POMERANIANS, 3)
            .put(Possession.AKITAS, 0).put(Possession.VIZSLAS, 0).put(Possession.GOLDFISH, 5)
            .put(Possession.TREES, 3).put(Possession.CARS, 2).put(Possession.PERFUMES, 1).build();
    }
}

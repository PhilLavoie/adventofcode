package day14;

import com.google.common.collect.FluentIterable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final int RACE_TIME = 2503; //in seconds

    private static void part1(Iterable<Reindeer> reindeers) {
        System.out.println("part 1");
        ReindeerDistanceCalculator calculator = new ReindeerDistanceCalculator();

        Reindeer winner = null;
        int max = Integer.MIN_VALUE;

        for (Reindeer reindeer : reindeers) {

            int reindeerDistance = calculator.distanceOver(reindeer, RACE_TIME);
            if (reindeerDistance > max) {
                winner = reindeer;
                max = reindeerDistance;
            }
        }

        System.out.println("And the winner is: " + winner.getName() + " for a distance of: " + max);
    }

    private static void part2(List<Reindeer> reindeers) {
        System.out.println("part 2");
        Iterable<ReindeerScore> scores =
            new ReindeersPart2ScoreCalculator().calculateScoreFor(reindeers, RACE_TIME);

        int maxScore = Integer.MIN_VALUE;
        Reindeer winner = null;

        for (ReindeerScore reindeerScore : scores) {
            int score = reindeerScore.getScore();
            if (score > maxScore) {
                maxScore = score;
                winner = reindeerScore.getReindeer();
            }
        }

        System.out
            .println("And the winner is: " + winner.getName() + " for a score of: " + maxScore);
    }

    public static void main(String[] args) {
        FileReader reader;

        try {
            reader = new FileReader(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("could not find file: " + args[0]);
            return;
        }


        List<Reindeer> reindeers =
            new ReindeerPerformanceDataParser().parse(new BufferedReader(reader).lines())
                .collect(Collectors.toList());

        part1(reindeers);
        part2(reindeers);
    }
}

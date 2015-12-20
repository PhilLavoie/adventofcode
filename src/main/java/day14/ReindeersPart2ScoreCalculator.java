package day14;

import com.google.common.collect.FluentIterable;

public class ReindeersPart2ScoreCalculator {

    public Iterable<ReindeerScore> calculateScoreFor(Iterable<Reindeer> reindeers, int raceTime) {
        Iterable<ReindeerScore> scores =
            FluentIterable.from(reindeers).transform(reindeer -> new ReindeerScore(reindeer))
                .toList();

        for (int i = 0; i < raceTime; i++) {
            //Adjust time.
            adjustTimes(scores, i);

            //Get max distance.
            int max = getMaxDistanceSoFar(scores);

            //Update scores.
            updateScores(scores, max);
        }

        return scores;
    }

    private void updateScores(Iterable<ReindeerScore> scores, int max) {
        for (ReindeerScore score: scores) {
            int distanceSoFar = score.getDistanceSoFar();
            assert distanceSoFar <= max;
            if (distanceSoFar == max) {
                score.incrementScore();
            }
        }
    }

    private int getMaxDistanceSoFar(Iterable<ReindeerScore> scores) {
        int max = Integer.MIN_VALUE;
        for (ReindeerScore score: scores) {
            int distanceSoFar = score.getDistanceSoFar();
            if (distanceSoFar > max) {
                max = distanceSoFar;
            }
        }
        return max;
    }

    private void adjustTimes(Iterable<ReindeerScore> scores, int i) {
        for (ReindeerScore score: scores) {
            Reindeer reindeer = score.getReindeer();
            int reindeerCycleTime = reindeer.getCycleTime();
            int timeRelativeToCycle = i % reindeerCycleTime;

            if (timeRelativeToCycle < reindeer.getBurstTime()) {
                score.incrementDistanceTraveled();
            }
        }
    }
}

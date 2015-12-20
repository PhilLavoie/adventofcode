package day14;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Math.min;

public class ReindeerDistanceCalculator {

    public int distanceOver(Reindeer reindeer, int noSeconds) {
        checkArgument(noSeconds > 0,
            "invalid number of seconds: " + noSeconds + ", should be a positive number");

        int burstTime = reindeer.getBurstTime();
        int restTime = reindeer.getRestTime();

        int cycleTime = burstTime + restTime;
        int noCyclesOverGivenTime = noSeconds / cycleTime;

        int theRemainingTime = noSeconds % cycleTime;

        int topSpeed = reindeer.getTopSpeed();
        return noCyclesOverGivenTime * topSpeed * burstTime
            + topSpeed * min(theRemainingTime, burstTime);
    }
}

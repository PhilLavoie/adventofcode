package day14;

public class Reindeer {

    private final String name;
    private final int topSpeed; //km/s
    private final int burstTime; //In seconds.
    private final int restTime; //In seconds.

    public Reindeer(String name, int topSpeed, int burstTime, int restTime) {
        this.name = name;
        this.topSpeed = topSpeed;
        this.burstTime = burstTime;
        this.restTime = restTime;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getRestTime() {
        return restTime;
    }

    public String getName() {
        return name;
    }

    public int getCycleTime() {
        return getBurstTime() + getRestTime();
    }
}

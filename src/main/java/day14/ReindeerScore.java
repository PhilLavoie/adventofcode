package day14;

public class ReindeerScore {
    private final Reindeer reindeer;
    private int score;
    private int distanceSoFar;

    public ReindeerScore(Reindeer reindeer) {
        this.reindeer = reindeer;
        score = 0;
        distanceSoFar = 0;
    }

    public void incrementScore() {
        score++;
    }

    public void incrementDistanceTraveled() {
        distanceSoFar += reindeer.getTopSpeed();
    }

    public Reindeer getReindeer() {
        return reindeer;
    }

    public int getScore() {
        return score;
    }

    public int getDistanceSoFar() {
        return distanceSoFar;
    }
}

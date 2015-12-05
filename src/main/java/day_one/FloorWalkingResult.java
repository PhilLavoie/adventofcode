package day_one;

public class FloorWalkingResult {
    private final long floor;
    private final long firstBasementIndex;

    public FloorWalkingResult(long floor, long firstBasementIndex) {
        this.floor = floor;
        this.firstBasementIndex = firstBasementIndex;
    }

    public long getFloor() {
        return floor;
    }

    public long getFirstBasementIndex() {
        return firstBasementIndex;
    }
}

package day21;

import static com.google.common.base.Preconditions.checkArgument;

public class Boss implements Character {
    private int hitPoints = 109;

    @Override public int getArmorScore() {
        return 2;
    }

    @Override public int getDamageScore() {
        return 8;
    }

    @Override public int getHitPoints() {
        return hitPoints;
    }

    @Override public void removeHitPoints(int noHitPoints) {
        checkArgument(0 < noHitPoints);
        hitPoints -= noHitPoints;
    }
}

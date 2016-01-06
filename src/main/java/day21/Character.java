package day21;

public interface Character {
    int getArmorScore();
    int getDamageScore();
    int getHitPoints();
    void removeHitPoints(int noHitPoints);
    default boolean isDead() {
        return getHitPoints() <= 0;
    }
}

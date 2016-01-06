package day21;

import day21.gear.Inventory;

import static com.google.common.base.Preconditions.checkArgument;

public class Player implements Character {
    private final Inventory inventory;
    private int hitPoints = 100;

    public Player(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override public int getArmorScore() {
        return inventory.getTotalArmorScore();
    }

    @Override public int getDamageScore() {
        return inventory.getTotalDamageScore();
    }

    @Override public int getHitPoints() {
        return hitPoints;
    }

    @Override public void removeHitPoints(int noHitPoints) {
        checkArgument(0 < noHitPoints);
        hitPoints -= noHitPoints;
    }

    public Inventory getInventory() {
        return inventory;
    }
}

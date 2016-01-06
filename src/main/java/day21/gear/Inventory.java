package day21.gear;

import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

public class Inventory {
    private final Weapon weapon;
    private final Armor armor;
    private final Ring leftRing;
    private final Ring rightRing;
    private final Item[] items; //For convenience.

    public Inventory(Weapon weapon, Armor armor, Ring leftRing, Ring rightRing) {
        this.weapon = weapon;
        this.armor = armor;
        this.leftRing = leftRing;
        this.rightRing = rightRing;
        items = new Item[] {weapon, armor, leftRing, rightRing};
    }

    public int getTotalCost() {
        return Stream.of(items).filter(item -> item != null).mapToInt(item -> item.getCost()).sum();
    }

    public int getTotalDamageScore() {
        return Stream.of(items).filter(item -> item != null).mapToInt(item -> item.getDamageScore())
            .sum();
    }

    public int getTotalArmorScore() {
        return Stream.of(items).filter(item -> item != null).mapToInt(item -> item.getArmorScore())
            .sum();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public Ring getLeftRing() {
        return leftRing;
    }

    public Ring getRightRing() {
        return rightRing;
    }
}

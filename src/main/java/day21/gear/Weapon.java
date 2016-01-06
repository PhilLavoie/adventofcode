package day21.gear;

public enum Weapon implements Item {
    DAGGER(8, 4),
    SHORTSWORD(10, 5),
    WARHAMMER(25, 6),
    LONGSWORD(40, 7),
    GREATAXE(74, 8);

    private final int cost;
    private final int damage;

    Weapon(int cost, int damage) {
        this.cost = cost;
        this.damage = damage;
    }

    @Override public int getCost() {
        return cost;
    }

    @Override public int getDamageScore() {
        return damage;
    }

    @Override public int getArmorScore() {
        return 0;
    }
}

package day21.gear;

public enum Ring implements Item {
    MALICE(25, 1, 0),
    HATRED(50, 2, 0),
    DESTRUCTION(100, 3, 0),
    SOOTHING(20, 0, 1),
    RELAXATION(40, 0, 2),
    TRANQUILITY(80, 0, 3);

    private final int cost;
    private final int damage;
    private final int armor;

    Ring(int cost, int damage, int armor) {
        this.cost = cost;
        this.damage = damage;
        this.armor = armor;
    }

    @Override public int getCost() {
        return cost;
    }

    @Override public int getDamageScore() {
        return damage;
    }

    @Override public int getArmorScore() {
        return armor;
    }
}

package day21.gear;

public enum Armor implements Item {
    LEATHER(13, 1),
    CHAINMAIL(31, 2),
    SPLINTMAIL(53, 3),
    BANDEDMAIL(75, 4),
    PLATEMAIL(102, 5);

    private final int cost;
    private final int armor;

    Armor(int cost, int armor) {
        this.cost = cost;
        this.armor = armor;
    }

    @Override public int getCost() {
        return cost;
    }

    @Override public int getDamageScore() {
        return 0;
    }

    @Override public int getArmorScore() {
        return armor;
    }


}

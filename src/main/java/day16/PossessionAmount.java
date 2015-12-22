package day16;

public class PossessionAmount {
    private final Possession possession;
    private final int amount;

    public PossessionAmount(Possession possession, int amount) {
        this.possession = possession;
        this.amount = amount;
    }

    public Possession getPossession() {
        return possession;
    }

    public int getAmount() {
        return amount;
    }
}

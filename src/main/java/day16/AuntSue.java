package day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newArrayList;

public class AuntSue {
    private final int identifier;
    private final ArrayList<PossessionAmount> possessionsAmounts;

    AuntSue(int identifier, Iterable<PossessionAmount> possessionsAmounts) {
        this.possessionsAmounts = newArrayList(possessionsAmounts);
        this.identifier = identifier;
    }

    public Iterable<PossessionAmount> getPossessionsAmounts() {
        return possessionsAmounts;
    }

    public int getIdentifier() {
        return identifier;
    }

    public static AuntSue withPossessions(int identifier, Iterable<PossessionAmount> possessionsAmounts) {
        Map<Possession, Integer> auntSuesPossessions = new HashMap<>();

        for (PossessionAmount possessionAmount : possessionsAmounts) {
            Possession posession = possessionAmount.getPossession();
            int amount = possessionAmount.getAmount();

            checkArgument(!auntSuesPossessions.containsKey(posession),
                "already provided an amount for: " + posession);
            auntSuesPossessions.put(posession, amount);
        }

        return new AuntSue(identifier, possessionsAmounts);
    }

}

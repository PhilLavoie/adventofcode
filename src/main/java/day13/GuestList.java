package day13;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public class GuestList {
    private final Map<String, Map<String, Integer>> proximityBonuses;

    public GuestList() {
        this.proximityBonuses = new HashMap<>();
    }

    private void addGuest(String guest) {
        checkArgument(!has(guest), "guest already registered: " + guest);
        proximityBonuses.put(guest, new HashMap<>());
    }

    private boolean has(String guest) {
        return proximityBonuses.containsKey(guest);
    }


    public void setProximityBonusBetweenGuests(String guest, String nextToWho, int amount) {
        if (!has(guest)) {
            addGuest(guest);
        }

        Map<String, Integer> proximityBonusForGuest = proximityBonuses.get(guest);

        checkState(!proximityBonusForGuest.containsKey(nextToWho), String
            .format("already set the proximity bonus of %s and %s to %d", guest, nextToWho,
                proximityBonusForGuest.get(nextToWho)));

        proximityBonusForGuest.put(nextToWho, amount);
    }

    public int getProximityBonusBetweenGuests(String guest, String nextToWho) {
        checkState(has(guest), "uknown guest: " + guest);

        Map<String, Integer> proximityBonusForGuest = proximityBonuses.get(guest);

        checkState(proximityBonusForGuest.containsKey(nextToWho),
            String.format("proximity bonus not set between %s and %s", guest, nextToWho));

        return proximityBonusForGuest.get(nextToWho);
    }

    public int getTotalProximityBonusOfGuests(String guest1, String guest2) {
        return getProximityBonusBetweenGuests(guest1, guest2) + getProximityBonusBetweenGuests(
            guest2, guest1);
    }

    public Iterable<String> getGuests() {
        return proximityBonuses.keySet();
    }
}

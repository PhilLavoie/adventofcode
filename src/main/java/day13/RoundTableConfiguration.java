package day13;

public class RoundTableConfiguration {
    private final String[] configuration;
    private final GuestList guestList;

    public RoundTableConfiguration(GuestList guestList, String[] configuration) {
        this.guestList = guestList;
        this.configuration = configuration;
    }

    public int getConfigurationHappiness() {
        int sum = 0;

        //Circular wrap up.
        sum += guestList.getTotalProximityBonusOfGuests(configuration[0],
            configuration[configuration.length - 1]);

        for (int i = 0; i < configuration.length - 1; i++) {
            sum += guestList.getTotalProximityBonusOfGuests(configuration[i], configuration[i + 1]);
        }

        return sum;
    }
}

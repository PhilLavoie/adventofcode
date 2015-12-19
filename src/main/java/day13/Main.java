package day13;

import java.io.*;

import static com.google.common.collect.Lists.newLinkedList;

public class Main {

    public static void main(String[] args) {
        String fileName;
        try {
            fileName = args[0];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("missing file name");
            return;
        }

        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("file not found: " + fileName);
            return;
        }

        GuestList guestList = new GuestListParser()
            .parse(new BufferedReader(new InputStreamReader(inputStream)).lines());

        //For part 2.
        String me = "Phil The Boss";
        for (String guest: newLinkedList(guestList.getGuests())) {
            guestList.setProximityBonusBetweenGuests(me, guest, 0);
            guestList.setProximityBonusBetweenGuests(guest, me, 0);
        }

        int max = Integer.MIN_VALUE;
        for (RoundTableConfiguration configuration: new RoundTableConfigurationIterable(guestList)) {
            int configurationHappiness = configuration.getConfigurationHappiness();
            if (configurationHappiness > max) {
                max = configurationHappiness;
            }
        }

        System.out.println("Maximum happiness: " + max);

    }
}

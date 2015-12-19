package day13;

import com.google.common.collect.Iterables;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.collect.Lists.newLinkedList;
import static com.google.common.collect.Sets.immutableEnumSet;
import static com.google.common.collect.Sets.newHashSet;

public class RoundTableConfigurationIterable implements Iterable<RoundTableConfiguration> {
    private final GuestList guestList;

    public RoundTableConfigurationIterable(GuestList guestList) {
        this.guestList = guestList;
    }

    @Override public Iterator<RoundTableConfiguration> iterator() {
        return new RoundTableConfigurationIterator(guestList);
    }

    private static class RoundTableConfigurationIterator implements Iterator<RoundTableConfiguration> {
        private final LinkedList<String>[] availableGuestsPerPosition;
        private final Iterable<String> guests;
        private final GuestList guestList;

        public RoundTableConfigurationIterator(GuestList guestList) {
            this.guestList = guestList;
            this.guests = guestList.getGuests();
            availableGuestsPerPosition = new LinkedList[Iterables.size(guests)];
            availableGuestsPerPosition[0] = newLinkedList(guests);
        }

        @Override public boolean hasNext() {
            return !availableGuestsPerPosition[0].isEmpty();
        }

        @Override public RoundTableConfiguration next() {
            generateConfiguration();

            String[] configuration = new String[availableGuestsPerPosition.length];

            int i = 0;
            for (LinkedList<String> availableGuestsAtPosition: availableGuestsPerPosition) {
                configuration[i] = availableGuestsAtPosition.getFirst();
                i++;
            }

            clearConfiguration();

            return new RoundTableConfiguration(guestList, configuration);
        }

        private void clearConfiguration() {
            //Remove the just used configuration.
            int lastPost = availableGuestsPerPosition.length - 1;
            availableGuestsPerPosition[lastPost].removeFirst();

            for (int i = lastPost; i > 0; i--) {
                if (availableGuestsPerPosition[i].isEmpty()) {
                    availableGuestsPerPosition[i - 1].removeFirst();
                }
            }
        }

        private void generateConfiguration() {
            HashSet<String> unusedGuests = newHashSet(guests);

            unusedGuests.remove(availableGuestsPerPosition[0].getFirst());

            for (int i = 1; i < availableGuestsPerPosition.length; i++) {
                if (availableGuestsPerPosition[i] == null || availableGuestsPerPosition[i].isEmpty()) {
                    availableGuestsPerPosition[i] = newLinkedList(unusedGuests);
                }
                unusedGuests.remove(availableGuestsPerPosition[i].getFirst());
            }
            assert unusedGuests.isEmpty();
        }
    }
}

package day13;

import java.util.function.Function;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;

public class GuestListParser {

    public GuestList parse(Stream<String> lines) {
        GuestList guestList = new GuestList();

        lines.forEach(line -> parseLine(guestList, line));

        return guestList;
    }

    private void parseLine(GuestList guestList, String line) {
        try {
            parseWords(guestList, line.split("\\s+"));
        } catch (Exception e) {
            throw new IllegalArgumentException("invalid line: " + line, e);
        }
    }

    //Lines are of the type:
    //Eric would lose 89 happiness units by sitting next to Bob.
    private void parseWords(GuestList guestList, String[] words) {
        checkArgument(words.length == 11);

        String name = words[0];
        String method = words[2]; //Lose or gain.
        String amount = words[3];
        String nextToWho = words[10].substring(0, words[10].length() - 1); //Remove dot.

        processAssertion(guestList, name, Method.of(method), Integer.parseInt(amount), nextToWho);
    }

    private void processAssertion(GuestList guestList, String guest, Method method, int amount,
        String nextToWho) {
        guestList.setProximityBonusBetweenGuests(guest, nextToWho, method.apply(amount));
    }

    enum Method {
        GAIN(i -> i),
        LOSE(i -> -i);

        private final Function<Integer, Integer> function;

        Method(Function<Integer, Integer> function) {
            this.function = function;
        }

        int apply(int value) {
            return function.apply(value);
        }

        public static Method of(String string) {
            switch (string) {
                case "gain":
                    return GAIN;
                case "lose":
                    return LOSE;
            }
            checkArgument(false, "unknown method: " + string);
            return null;
        }
    }
}

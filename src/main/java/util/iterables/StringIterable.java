package util.iterables;

import java.util.Iterator;

public class StringIterable implements Iterable<Character> {

    private final String string;

    public StringIterable(String string) {
        this.string = string;
    }

    @Override public Iterator<Character> iterator() {
        return string.chars().mapToObj(a -> (char)a).iterator();
    }

    public static StringIterable iterableOf(String string) {
        return new StringIterable(string);
    }
}

package util.iterables;

import java.util.Iterator;
import java.util.stream.Stream;

public class StreamIterable<T> implements Iterable<T> {
    private final Stream<T> stream;

    public StreamIterable(Stream<T> stream) {
        this.stream = stream;
    }

    @Override public Iterator<T> iterator() {
        return stream.iterator();
    }

    public static <T> StreamIterable<T> iterableOf(Stream<T> stream) {
        return new StreamIterable<>(stream);
    }
}

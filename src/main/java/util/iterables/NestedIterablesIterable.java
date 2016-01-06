package util.iterables;

import com.google.common.collect.PeekingIterator;
import day13.RoundTableConfiguration;
import util.functional.VarargBuilder;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Function;

import static com.google.common.collect.Iterators.peekingIterator;

//TODO: find better name.
public class NestedIterablesIterable<T> implements Iterable<T> {
    private final VarargBuilder<T> builder;
    private final Iterable[] iterables;

    NestedIterablesIterable(VarargBuilder<T> builder, Iterable[] iterables) {
        this.builder = builder;
        this.iterables = iterables;
    }

    public static <T> NestedIterablesIterable fromIterables(VarargBuilder<T> builder, Iterable... iterables) {
        return new NestedIterablesIterable(builder, iterables);
    }

    public static <T> NestedIterablesIterable fromIterables(Iterable... iterables) {
        return new NestedIterablesIterable((args) -> args, iterables);
    }

    @Override public Iterator<T> iterator() {
        return new NestIterablesIterator(builder, iterables);
    }

    private class NestIterablesIterator implements Iterator<T> {
        private final VarargBuilder<T> builder;
        private final Iterable[] iterables;
        private final PeekingIterator[] iterators;
        private final int noValues;

        public NestIterablesIterator(VarargBuilder<T> builder, Iterable[] iterables) {
            this.builder = builder;
            this.iterables = iterables;
            this.noValues = iterables.length;

            iterators = new PeekingIterator[noValues];
            iterators[0] = peekingIterator(this.iterables[0].iterator());
            for (int i = 1; i < noValues; i++) {
                iterators[i] = peekingIterator(Collections.emptyIterator());
            }
        }

        @Override public boolean hasNext() {
            return iterators[0].hasNext();
        }

        @Override public T next() {
            generateConfiguration();

            Object[] configuration = new Object[noValues];

            int i = 0;
            for (PeekingIterator iterator: iterators) {
                configuration[i] = iterator.peek();
                i++;
            }

            clearConfiguration();

            return builder.build(configuration);
        }

        private void clearConfiguration() {
            //Remove the just used configuration.
            int lastPost = noValues - 1;
            iterators[lastPost].next();

            for (int i = lastPost; i > 0; i--) {
                if (!iterators[i].hasNext()) {
                    iterators[i - 1].next();
                }
            }
        }

        private void generateConfiguration() {
            for (int i = 1; i < noValues; i++) {
                if (!iterators[i].hasNext()) {
                    iterators[i] = peekingIterator(iterables[i].iterator());
                }
            }
        }
    }
}

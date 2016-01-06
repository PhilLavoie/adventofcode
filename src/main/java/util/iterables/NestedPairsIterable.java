package util.iterables;

import com.google.common.collect.Iterables;
import util.tuples.Pair;
import util.tuples.PairBuilder;

import java.util.ArrayList;
import java.util.Iterator;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newArrayList;

public class NestedPairsIterable<T> implements Iterable<Pair<T, T>> {
    private final PairBuilder<T, T> builder;
    private final ArrayList<T> values;

    public NestedPairsIterable(PairBuilder<T, T> builder, Iterable<T> values) {
        this.builder = builder;
        this.values = newArrayList(values);
    }

    @Override public Iterator<Pair<T, T>> iterator() {
        return NestedPairIterator.iteratorFor(builder, values);
    }

    private static class NestedPairIterator<T> implements Iterator<Pair<T, T>> {
        private final PairBuilder<T, T> builder;
        private final ArrayList<T> values;
        private int noValues;
        private int leftIndex;
        private int rightIndex;

        NestedPairIterator(PairBuilder<T, T> builder, ArrayList<T> values) {
            this.builder = builder;
            this.values = values;
            noValues = Iterables.size(values);
            leftIndex = 0;
            rightIndex = 1;
        }

        @Override public boolean hasNext() {
            return leftIndex <= noValues - 2;
        }

        @Override public Pair<T, T> next() {
            Pair<T, T> result = builder.build(values.get(leftIndex), values.get(rightIndex));

            incrementIndexes();
            return result;
        }

        private void incrementIndexes() {
            if (rightIndex < noValues - 1) {
                rightIndex++;
                return;
            }

            leftIndex++;
            rightIndex = leftIndex + 1;
        }

        public static <T> NestedPairIterator<T> iteratorFor(PairBuilder<T, T> builder,
            ArrayList<T> values) {
            checkArgument(Iterables.size(values) >= 2);
            return new NestedPairIterator<>(builder,  values);
        }
    }
}

package util.iterables;

import com.google.common.collect.Iterables;
import org.junit.Test;
import util.tuples.Pair;
import util.tuples.PairBuilder;
import util.tuples.ValuePair;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

public class NestedPairsIterableTest {

    private PairBuilder<Integer, Integer> builder = (left, right) -> new ValuePair<>(left, right);
    private NestedPairsIterable<Integer> iterable;

    private void setIterable(Integer... values) {
        iterable = new NestedPairsIterable<>(builder, Arrays.asList(values));
    }

    private Pair<Integer, Integer> makePair(Integer left, Integer right) {
        return builder.build(left, right);
    }


    @Test
    public void testOneValue() {
        setIterable(1, 2);
        assertEquals(1, Iterables.size(iterable));
        assertEquals(new ValuePair<>(1, 2), Iterables.get(iterable, 0));
    }

    @Test
    public void testThreeValues() {
        setIterable(1, 2, 3);
        assertEquals(3, Iterables.size(iterable));
        assertEquals(makePair(1, 2), Iterables.get(iterable, 0));
        assertEquals(makePair(1, 3), Iterables.get(iterable, 1));
        assertEquals(makePair(2, 3), Iterables.get(iterable, 2));
    }

    @Test
    public void testFourValues() {
        setIterable(1, 2, 3, 4);
        assertEquals(6, Iterables.size(iterable));
        assertEquals(makePair(1, 2), Iterables.get(iterable, 0));
        assertEquals(makePair(1, 3), Iterables.get(iterable, 1));
        assertEquals(makePair(1, 4), Iterables.get(iterable, 2));
        assertEquals(makePair(2, 3), Iterables.get(iterable, 3));
        assertEquals(makePair(2, 4), Iterables.get(iterable, 4));
        assertEquals(makePair(3, 4), Iterables.get(iterable, 5));
    }

}


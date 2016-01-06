package util.iterables;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import org.junit.Test;
import util.functional.VarargBuilder;

import static com.google.common.base.Preconditions.checkArgument;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class NestedIterablesIterableTest {

    private NestedIterablesIterable<Integer[]> iterable;

    private static <T> Iterable<T> makeIterable(T... values) {
        return FluentIterable.of(values);
    }

    private void setIterable(Iterable<Integer>... iterables) {
        iterable = NestedIterablesIterable.fromIterables(iterables);
    }

    private void setIterable(VarargBuilder<Integer> builder, Iterable<Integer>... iterables) {
        iterable = NestedIterablesIterable.fromIterables(builder, iterables);
    }

    @Test
    public void testOneIterableGivesTheExactSameResult() {
        Iterable<Integer> ints = makeIterable(1, 2, 3, 4, 5, 6);

        setIterable(objects -> {
            checkArgument(objects.length == 1);
            return (Integer) objects[0];
        }, ints);

        assertEquals(6, Iterables.size(iterable));
        assertEquals(1, Iterables.get(iterable, 0));
        assertEquals(2, Iterables.get(iterable, 1));
        assertEquals(3, Iterables.get(iterable, 2));
        assertEquals(4, Iterables.get(iterable, 3));
        assertEquals(5, Iterables.get(iterable, 4));
        assertEquals(6, Iterables.get(iterable, 5));
    }

    @Test
    public void testTwoIterables() {
        Iterable<Integer> ints1 = makeIterable(1, 2);
        Iterable<Integer> ints2 = makeIterable(3, 4);

        setIterable(ints1, ints2);

        assertEquals(4, Iterables.size(iterable));
        assertArrayEquals(new Integer[]{1, 3}, Iterables.get(iterable, 0));
        assertArrayEquals(new Integer[]{1, 4}, Iterables.get(iterable, 1));
        assertArrayEquals(new Integer[]{2, 3}, Iterables.get(iterable, 2));
        assertArrayEquals(new Integer[]{2, 4}, Iterables.get(iterable, 3));
    }

    @Test
    public void testBuilderGetsCalled() {
        VarargBuilder<Integer> mockedBuilder = mock(VarargBuilder.class);
        Iterable<Integer> ints = makeIterable(1);

        setIterable(mockedBuilder, ints);
        iterable.iterator().next();

        verify(mockedBuilder, times(1)).build(anyObject());
    }

}

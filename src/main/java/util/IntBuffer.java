package util;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;

public class IntBuffer implements Iterable<Integer> {

    private int[] data;
    private int length;

    public IntBuffer() {
        data = new int[10];
        length = 0;
    }

    public int get(int index) {
        Preconditions.checkElementIndex(index, length);

        return data[index];
    }

    private void grow() {
        data = Arrays.copyOf(data, capacity() * 2);
    }

    public void append(int value) {
        if (length() == capacity()) {
            grow();
        }

        data[length] = value;
        length++;
    }

    public void append(int... values) {
        //Should be that we first get the right capacity but I'm lazy right now.
        for (int value: values) {
            append(value);
        }
    }

    public void clear() {
        length = 0;
    }

    public int length() {
        return length;
    }

    public int capacity() {
        return data.length;
    }

    @Override public Iterator<Integer> iterator() {
        return IntStream.of(data).limit(length()).iterator();
    }
}

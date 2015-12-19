package util.buffers;

import com.google.common.base.Preconditions;
import com.google.common.io.CharStreams;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;

public class CharBuffer {

    private char[] data;
    private int length;

    public CharBuffer() {
        data = new char[10];
        length = 0;
    }

    public char get(int index) {
        Preconditions.checkElementIndex(index, length);

        return data[index];
    }

    private void grow() {
        data = Arrays.copyOf(data, capacity() * 2);
    }

    public void append(char value) {
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

    public boolean isEmpty() {
        return length == 0;
    }

    public int length() {
        return length;
    }

    public int capacity() {
        return data.length;
    }

    public char[] getData() {
        return Arrays.copyOf(data, length);
    }

}

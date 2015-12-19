package util;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamCharIterator implements PeekableCharIterator {
    private final InputStream inputStream;

    private int currentChar;
    private boolean haveJustPeeked;

    public InputStreamCharIterator(InputStream inputStream) {
        this.inputStream = inputStream;
        haveJustPeeked = false;
    }

    @Override public char peek() {
        return (char) peekInt();
    }

    private int peekInt() {
        if (haveJustPeeked) {
            return currentChar;
        }
        haveJustPeeked = true;
        currentChar = readNextChar();
        return currentChar;
    }

    private int readNextChar() {
        try {
            return inputStream.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public char next() {
        if (haveJustPeeked) {
            haveJustPeeked = false;
        } else {
            currentChar = readNextChar();
        }
        return (char) currentChar;
    }

    @Override public boolean hasNext() {
        return peekInt() != -1;
    }
}

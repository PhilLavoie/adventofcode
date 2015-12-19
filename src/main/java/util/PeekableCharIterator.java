package util;

public interface PeekableCharIterator extends CharIterator {
    char peek();

    static PeekableCharIterator from(CharIterator iterator) {
        return new PeekableCharIterator() {
            private char currentChar;
            private boolean haveJustPeeked;

            @Override public char peek() {
                if (haveJustPeeked) {
                    return currentChar;
                }
                haveJustPeeked = true;
                currentChar = nextChar();
                return currentChar;
            }

            private char nextChar() {
                return iterator.next();
            }

            @Override public char next() {
                if (haveJustPeeked) {
                    haveJustPeeked = false;
                } else {
                    currentChar = nextChar();
                }
                return currentChar;
            }

            @Override public boolean hasNext() {
                return iterator.hasNext();
            }
        };
    }
}

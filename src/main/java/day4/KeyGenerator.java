package day4;

import java.util.Arrays;
import java.util.Iterator;

public class KeyGenerator implements Iterable<byte[]> {

    private final byte[] prefix;

    public KeyGenerator(byte[] prefix) {
        this.prefix = prefix;
    }

    @Override public Iterator<byte[]> iterator() {
        return new KeyGeneratorIterator(prefix);
    }

    private static class KeyGeneratorIterator implements Iterator<byte[]> {

        private static final byte ZERO = '0';
        private static final byte ONE = '1';
        private static final byte NINE = '9';
        
        private byte[] buffer;
        private byte[] prefixBytes;

        private int prefixLastIndex;
        private int currentIndex;

        public KeyGeneratorIterator(byte[] prefixBytes) {
            this.prefixBytes = prefixBytes;
            prefixLastIndex = prefixBytes.length - 1;
            buffer = prefixBytes;
            growBuffer(true);
        }

        private void growBuffer(boolean initial) {
            buffer = Arrays.copyOf(prefixBytes, buffer.length + 1);
            if (!initial) {
                buffer[prefixLastIndex + 1] = ONE;
                for (int i = prefixLastIndex + 2; i < buffer.length; i++) {
                    buffer[i] = ZERO;
                }
            } else {
                buffer[prefixLastIndex + 1] = ZERO;
            }
            currentIndex = buffer.length - 1;
        }

        @Override public boolean hasNext() {
            return true;
        }

        private int getCurrentIndexValue() {
            return buffer[currentIndex];
        }

        private boolean canPropagateCarry() {
            int i = currentIndex - 1;
            while (i > prefixLastIndex) {
                if (buffer[i] < NINE) {
                    return true;
                }
                i--;
            }
            return false;
        }

        private void incrementDecimalSuffix() {
            if (getCurrentIndexValue() < NINE) {
                buffer[currentIndex]++;
            } else {
                if (canPropagateCarry()) {
                    buffer[currentIndex] = ZERO;
                    int i = currentIndex - 1;
                    boolean carryPropagated = false;
                    while (i > prefixLastIndex && !carryPropagated) {
                        if (buffer[i] == NINE) {
                            buffer[i] = ZERO;
                        } else {
                            buffer[i]++;
                            carryPropagated = true;
                        }
                        i--;
                    }
                } else {
                    growBuffer(false);
                }
            }
        }

        /**
         * Don't store the reference returned, make a copy if needed.
         * @return
         */
        @Override public byte[] next() {
            incrementDecimalSuffix();
            return buffer;
        }
    }

    public static KeyGenerator generateWithPrefix(byte[] prefix) {
        return new KeyGenerator(prefix);
    }
}

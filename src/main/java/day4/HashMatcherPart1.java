package day4;

import java.util.function.Predicate;

public class HashMatcherPart1 implements Predicate<byte[]> {
    @Override public boolean test(byte[] hash) {
        return hash[0] == 0 &&
            hash[1] == 0 &&
            hash[2] >>> 4 == 0;
    }
}

package day4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HashMatcherPart1Test {

    private HashMatcherPart1 hashMatcherPart1;

    @Before
    public void setUp() {
        hashMatcherPart1 = new HashMatcherPart1();
    }

    @Test
    public void testMatchWithZeroInitializedArray() {
        byte[] hash = new byte[50];

        assertTrue(hashMatcherPart1.test(hash));
    }

    @Test
    public void testMatchWithNonZeroInitializedArray() {
        byte[] hash = new byte[50];
        for (int i = 0; i < hash.length; i++) {
            hash[i] = 1;
        }
        assertFalse(hashMatcherPart1.test(hash));
    }

    @Test
    public void testMatchStrict() {
        byte[] hash = {0, 0, 0x0F};

        assertTrue(hashMatcherPart1.test(hash));
    }

    @Test
    public void testNegativeMatchStrict() {
        byte[] hash = {0, 0, 0x10};
        assertFalse(hashMatcherPart1.test(hash));
    }

}

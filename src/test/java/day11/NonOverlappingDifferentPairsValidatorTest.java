package day11;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NonOverlappingDifferentPairsValidatorTest {

    private NonOverlappingDifferentPairsValidator validator;

    @Before
    public void setUp() {
        validator = new NonOverlappingDifferentPairsValidator();
    }

    @Test
    public void testEmptyString() {
        assertFalse(validator.test("".toCharArray()));
    }

    @Test
    public void testTwoPairsInARow() {
        assertFalse(validator.test("aaaa".toCharArray()));
    }

    @Test
    public void testTwoPairsInTheSameWord() {
        assertFalse(validator.test("aabcdefghijkaalmnop".toCharArray()));
    }

    @Test
    public void testTwoAdjacentDifferentPairs() {
        assertTrue(validator.test("aabb".toCharArray()));
    }

    @Test
    public void testTwoDifferentPairsInTheSameWord() {
        assertTrue(validator.test("aaabcdefghijaaklmnokk".toCharArray()));
    }

    @Test
    public void testOnlyOnePair() {
        assertFalse(validator.test("abcdefghhijklmnop".toCharArray()));
    }

    @Test
    public void testNoPair() {
        assertFalse(validator.test("abcdefghijklmnopqrstuvwxyz".toCharArray()));
    }

    @Test
    public void testExample1() {
        assertTrue(validator.test("abbceffg".toCharArray()));
    }

    @Test
    public void testExample2() {
        assertFalse(validator.test("abbcegjk".toCharArray()));
    }

}

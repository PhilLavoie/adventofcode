package day5.part2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NonOverlappingPairsMatcherTest {

    NonOverlappingPairsMatcher matcher;

    @Before
    public void setUp() {
        matcher = new NonOverlappingPairsMatcher();
    }

    @Test
    public void testNullString() {
        assertFalse(matcher.test(null));
    }

    @Test
    public void testEmptyString() {
        assertFalse(matcher.test(""));
    }

    @Test
    public void testOneLetterString() {
        assertFalse(matcher.test("a"));
    }

    @Test
    public void testTwoLettersString() {
        assertFalse(matcher.test("aa"));
    }

    @Test
    public void testThreeLettersString() {
        assertFalse(matcher.test("aaa"));
    }

    @Test
    public void test2PairsInARow() {
        assertTrue(matcher.test("abab"));
    }

    @Test
    public void testExample1() {
        assertTrue(matcher.test("aabcdefgaa"));
    }

    @Test
    public void testExample2() {
        assertTrue(matcher.test("qjhvhtzxzqqjkmpb"));
    }

    @Test
    public void testExample3() {
        assertTrue(matcher.test("xxyxx"));
    }

    @Test
    public void testExample4() {
        assertTrue(matcher.test("uurcxstgmygtbstg"));
    }

    @Test
    public void testExample5() {
        assertFalse(matcher.test("ieodomkazucvgmuy"));
    }
}

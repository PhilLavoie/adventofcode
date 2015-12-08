package day5;

import day5.part1.DoubledLetterMatcher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoubledLetterMatcherTest {

    private DoubledLetterMatcher matcher;

    @Before
    public void setUp() {
        matcher = new DoubledLetterMatcher();
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
    public void testDoubledLetterString() {
        assertTrue(matcher.test("dd"));
    }

    @Test
    public void test2DifferentLetters() {
        assertFalse(matcher.test("ab"));
    }

    @Test
    public void testWordWithDoubledLetters() {
        assertTrue(matcher.test("abeille"));
    }

    @Test
    public void testWordWithMoreThanDoubledLetters() {
        assertTrue(matcher.test("enchanteee"));
    }

    @Test
    public void testWordWithouDoubledLetters() {
        assertFalse(matcher.test("abcdefghijklmnopqrstuvwxyz"));
    }

}

package day5.part2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SameLettersSeperatedByOneMatcherTest {

    SameLettersSeperatedByOneMatcher matcher;

    @Before
    public void setUp() {
        matcher = new SameLettersSeperatedByOneMatcher();
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
    public void test3LettersMatch() {
        assertTrue(matcher.test("xyx"));
    }

    @Test
    public void testExample1() {
        assertTrue(matcher.test("aaa"));
    }

    @Test
    public void testExample2() {
        assertTrue(matcher.test("abcdefeghi"));
    }

    @Test
    public void testExample3() {
        assertTrue(matcher.test("qjhvhtzxzqqjkmpb"));
    }

    @Test
    public void testExample4() {
        assertTrue(matcher.test("xxyxx"));
    }

    @Test
    public void testExample5() {
        assertTrue(matcher.test("ieodomkazucvgmuy"));
    }

    @Test
    public void testExample6() {
        assertFalse(matcher.test("uurcxstgmygtbstg"));
    }

}

package day5;

import day5.part1.VowelCountMatcher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VowelCountMatcherTest {

    VowelCountMatcher matcher;

    @Before
    public void setUp() {
        matcher = new VowelCountMatcher();
    }

    @Test
    public void testNullString() {
        String nullString = null;
        assertFalse(matcher.test(nullString));
    }

    @Test
    public void testEmptyString() {
        String empty = "";
        assertFalse(matcher.test(empty));
    }

    @Test
    public void test3SameVowels() {
        String word = "aaa";
        assertTrue(matcher.test(word));
    }

    @Test
    public void test3DifferentVowels() {
        String word = "oue";
        assertTrue(matcher.test(word));
    }

    @Test
    public void testOnlyConsonents() {
        String word = "kdmnctr";
        assertFalse(matcher.test(word));
    }

    @Test
    public void testWordWith2Vowels() {
        String word = "ete";
        assertFalse(matcher.test(word));
    }

    @Test
    public void testWordWithMoreThan3Vowels() {
        String word = "anticonstitutionnellement";
        assertTrue(matcher.test(word));
    }

}

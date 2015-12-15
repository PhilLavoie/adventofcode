package day11;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ThreeLettersStraightValidatorTest {

    private ThreeLettersStraightValidator validator;

    @Before
    public void setUp() {
        validator = new ThreeLettersStraightValidator();
    }

    @Test
    public void testEmptyString() {
        assertFalse(validator.test(new char[0]));
    }

    @Test
    public void testNonStraight3LettersMissLast() {
        assertFalse(validator.test(new char[] {'a', 'b', 'd'}));
    }

    @Test
    public void testNonStraight3LettersMissSecond() {
        assertFalse(validator.test(new char[] {'a', 'c', 'c'}));
    }

    @Test
    public void testNonStraight3LettersMissFirst() {
        assertFalse(validator.test(new char[] {'z', 'b', 'c'}));
    }

    @Test
    public void testStraight3Letters() {
        assertTrue(validator.test(new char[] {'a', 'b', 'c'}));
    }

    @Test
    public void testNonStraightPhrase() {
        assertFalse(validator.test("Hi my name is: boom!".toCharArray()));
    }

    @Test
    public void testStraightPhrase() {
        assertTrue(validator.test("Hi your name is: xyz".toCharArray()));
    }

}

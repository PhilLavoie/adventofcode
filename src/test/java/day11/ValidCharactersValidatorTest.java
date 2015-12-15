package day11;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidCharactersValidatorTest {

    ValidCharactersValidator validator;

    private final char[] invalidCharacters = {'i', 'o', 'l'};

    @Before
    public void setUp() {
        validator = new ValidCharactersValidator(invalidCharacters);
    }

    @Test
    public void testEmptyString() {
        assertTrue(validator.test(new char[0]));
    }

    @Test
    public void testValidString() {
        assertTrue(validator.test("tutu pupu acke ymntres".toCharArray()));
    }

    @Test
    public void testInvalidString() {
        assertFalse(validator.test("tutu pupu acke ymntros".toCharArray()));
    }

    @Test
    public void testExampleString1() {
        assertFalse(validator.test("hijklmmn".toCharArray()));
    }

    @Test
    public void testExampleString2() {
        assertTrue(validator.test("abbceffg".toCharArray()));
    }

    @Test
    public void testExampleString3() {
        assertTrue(validator.test("abbcegjk".toCharArray()));
    }

}

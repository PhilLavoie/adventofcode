package day5.part2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GoodStringMatcherTest {

    private GoodStringMatcher matcher;

    @Before
    public void setUp() {
        matcher = new GoodStringMatcher();
    }

    @Test
    public void testExample1() {
        assertTrue(matcher.test("qjhvhtzxzqqjkmpb"));
    }

    @Test
    public void testExample2() {
        assertTrue(matcher.test("xxyxx"));
    }

    @Test
    public void testExample3() {
        assertFalse(matcher.test("uurcxstgmygtbstg"));
    }

    @Test
    public void testExample4() {
        assertFalse(matcher.test("ieodomkazucvgmuy"));
    }

}

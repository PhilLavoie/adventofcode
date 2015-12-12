package day7.operators;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OperatorFactoryTest {

    private OperatorFactory factory;

    private BinaryOperator rShift;


    @Before
    public void setUp() {
        factory = new OperatorFactory();
        rShift = factory.rShift();
    }

    private void assertShortEquals(int expected, short effective) {
        assertEquals(expected, effective);
    }

    @Test
    public void testRShift1() {
        assertShortEquals(0, rShift.apply((short)0, Short.MAX_VALUE));
    }

    @Test
    public void testRShift2() {
        assertShortEquals(0b0111_1111_1111_1111, rShift.apply((short)0b1111_1111_1111_1111, (short)1));
    }

}

package day7;

import day7.operators.OperatorFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CircuitTest {

    private Circuit circuit;

    @Before
    public void setUp() {
        circuit = new Circuit();
    }

    @Test public void testExample() {
        OperatorFactory factory = new OperatorFactory();

        Wire x = new Wire("x", () -> (short) 123);
        Wire y = new Wire("y", () -> (short) 456);
        Wire d = new Wire("d", () -> factory.and()
            .apply(circuit.getWireById("x").getValue(), circuit.getWireById("y").getValue()));
        Wire e = new Wire("e", () -> factory.or()
            .apply(circuit.getWireById("x").getValue(), circuit.getWireById("y").getValue()));
        Wire f = new Wire("f",
            () -> factory.lShift().apply(circuit.getWireById("x").getValue(), (short) 2));
        Wire g = new Wire("g",
            () -> factory.rShift().apply(circuit.getWireById("y").getValue(), (short) 2));;
        Wire h = new Wire("h", () -> factory.not().apply(circuit.getWireById("x").getValue()));
        Wire i = new Wire("i", () -> factory.not().apply(circuit.getWireById("y").getValue()));;

        circuit.putWire(x);
        circuit.putWire(y);
        circuit.putWire(d);
        circuit.putWire(e);
        circuit.putWire(f);
        circuit.putWire(g);
        circuit.putWire(h);
        circuit.putWire(i);

        assertWireValue(72, "d");
        assertWireValue(507, "e");
        assertWireValue(492, "f");
        assertWireValue(114, "g");
        assertWireValue(65412, "h");
        assertWireValue(65079, "i");
        assertWireValue(123, "x");
        assertWireValue(456, "y");


    }

    private void assertWireValue(int expected, String wire) {
        assertEquals((short)expected, (short) circuit.getWireById(wire).getValue());
    }

}

package day7.part2;

import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;
import day7.operators.BinaryOperator;
import day7.Circuit;
import day7.operators.UnaryOperator;
import day7.Wire;

import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Supplier;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.lang.model.SourceVersion.isIdentifier;

public class Part2WireFactory {

    private final Circuit circuit;
    private final HashMap<String, BinaryOperator> binaryOperators;
    private final HashMap<String, UnaryOperator> unaryOperators;

    public Part2WireFactory(Circuit circuit) {
        this.circuit = circuit;
        this.binaryOperators = new HashMap<>();
        this.binaryOperators.put("AND", (a, b) -> (short) (a & b));
        this.binaryOperators.put("OR", (a, b) -> (short) (a | b));
        this.binaryOperators.put("LSHIFT", (a, b) -> (short) (a << b));
        this.binaryOperators.put("RSHIFT", (a, b) -> (short) (a >>> b));

        this.unaryOperators = new HashMap<>();
        this.unaryOperators.put("NOT", (a) -> ((short) ~a));
    }

    public Wire fromFormalDefinition(Iterator<String> words) {
        return fromFormalDefinition(Iterators.peekingIterator(words));
    }

    private Wire fromFormalDefinition(PeekingIterator<String> words) {
        //Can start with source, identifier or unary operator.
        String firstWord = words.peek();

        if (isUnaryOperator(firstWord)) {
            return startsWithUnaryOperator(words);
        } else if (isOperand(firstWord)) {
            return startsWithOperand(words);
        } else {
            throw new IllegalArgumentException(
                "don't know what to do with this token: " + firstWord);
        }
    }

    private boolean isOperand(String firstWord) {
        return isIdentifier(firstWord) || isShort(firstWord);
    }

    private Wire startsWithUnaryOperator(PeekingIterator<String> words) {
        String firstWord = words.next();
        UnaryOperator unaryOperator = unaryOperatorOf(firstWord);

        String firstIdentifier = words.next();
        checkWireIdentifier(firstIdentifier);

        return wireFor(getFinalWireIdentifier(words),
            () -> unaryOperator.apply(circuit.getWireById(firstIdentifier).getValue()));
    }

    private boolean isArrow(String word) {
        return "->".equals(word);
    }

    private Wire startsWithOperand(PeekingIterator<String> words) {
        Operand leftHandSide = operandOf(words.next());

        String eitherArrowOrBinaryOperator = words.peek();

        if (isArrow(eitherArrowOrBinaryOperator)) {
            return wireFor(getFinalWireIdentifier(words), () -> leftHandSide.getValue());
        }

        String binaryOperatorString = words.next();
        BinaryOperator binaryOperator = binaryOperatorOf(binaryOperatorString);
        Operand rightHandSide = operandOf(words.next());
        return wireFor(getFinalWireIdentifier(words),
            () -> binaryOperator.apply(leftHandSide.getValue(), rightHandSide.getValue()));
    }

    private Wire wireFor(String identifier, Supplier<Short> valueSupplier) {
        if (!"b".equals(identifier)) {
            return new Wire(identifier, valueSupplier);
        } else {
            //Wire b overridden by value of wire a in part1.
            return new Wire(identifier, () -> (short)956);
        }
    }

    private Operand operandOf(String operand) {
        checkArgument(isOperand(operand), "invalid operand: " + operand);
        return new Operand(operand);
    }

    private void checkWireIdentifier(String identifier) {
        checkArgument(isIdentifier(identifier), "invalid wire identifier: " + identifier);
    }

    private boolean isUnaryOperator(String word) {
        return unaryOperators.containsKey(word);
    }

    private UnaryOperator unaryOperatorOf(String word) {
        return unaryOperators.get(word);
    }

    private short shortOf(String secondOperand) {
        return Short.parseShort(secondOperand);
    }

    private BinaryOperator binaryOperatorOf(String binaryOperatorString) {
        checkArgument(isBinaryOperator(binaryOperatorString),
            "invalid binary operator: " + binaryOperatorString);
        return binaryOperators.get(binaryOperatorString);
    }

    private boolean isBinaryOperator(String word) {
        return binaryOperators.containsKey(word);
    }

    private boolean isShort(String word) {
        try {
            shortOf(word);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String getFinalWireIdentifier(Iterator<String> words) {
        //Next word should be an arrow like that "->".
        String arrow = words.next();
        checkArgument("->".equals(arrow), "invalid token: " + arrow);

        //Next one is the resulting wire.
        String identifier = words.next();
        checkWireIdentifier(identifier);

        return identifier;
    }


    private class Operand {
        private final String operandString;

        public Operand(String operandString) {
            this.operandString = operandString;
        }

        public Short getValue() {
            if (isShort(operandString)) {
                return shortOf(operandString);
            } else {
                return circuit.getWireById(operandString).getValue();
            }
        }
    }
}

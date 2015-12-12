package day7.operators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperatorFactory {

    private static final Logger logger = LoggerFactory.getLogger("Operator");

    private final BinaryOperator andOperator =
        (a, b) -> {
            short result = (short) (a & b);
            logger.debug("{} and {} = {}", a, b, result);
            return result;
        };

    private final BinaryOperator orOperator =
        (a, b) -> {
            short result = (short) (a | b);
            logger.debug("{} or {} = {}", a, b, result);
            return result;
        };
    private final BinaryOperator lShiftOperator =
        (a, b) -> {
            short result = (short) (a << b);
            logger.debug("{} lshift {} = {}", a, b, result);
            return result;
        };
    private final BinaryOperator rShiftOperator =
        (a, b) -> {
            //Mask left hand side to circumvent int promotion.
            short result = (short) ((a &0xFFFF) >>> b);
            logger.debug("{} rshift {} = {}", a, b, result);
            return result;
        };
    private final UnaryOperator notOperator =
        (a) -> {
            short result = ((short) ~a);
            logger.debug("not {} = {}", a, result);
            return result;
        };

    public BinaryOperator and() {
        return andOperator;
    }

    public BinaryOperator or() {
        return orOperator;
    }

    public BinaryOperator lShift() {
        return lShiftOperator;
    }

    public BinaryOperator rShift() {
        return rShiftOperator;
    }

    public UnaryOperator not() {
        return notOperator;
    }
}

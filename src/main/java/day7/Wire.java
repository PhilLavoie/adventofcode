package day7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class Wire {
    private static final Logger logger = LoggerFactory.getLogger(Wire.class);

    private final String identifier;
    private final Supplier<Short> valueSupplier;
    private Short cachedValue;

    public Wire(String identifier, Supplier<Short> valueSupplier) {
        this.identifier = identifier;
        this.valueSupplier = valueSupplier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Short getValue() {
        if (cachedValue == null) {
            cachedValue = valueSupplier.get();
            logger.debug("extracted value {} for wire \"{}\"", cachedValue, identifier);
            if (null == cachedValue) {
                throw new NullPointerException("cannot supply a null value");
            }
        }
        return cachedValue;
    }
}

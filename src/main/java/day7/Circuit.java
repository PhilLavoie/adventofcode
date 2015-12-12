package day7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Circuit {

    private static Logger logger = LoggerFactory.getLogger(Circuit.class);

    private final Map<String, Wire> wiresById;

    public Circuit() {
        this.wiresById = new HashMap<>();
    }

    public void putWire(Wire wire) {
        String identifier = wire.getIdentifier();
        logger.debug("putting wire \"{}\"", identifier);

        if (isKnownId(identifier)) {
            throw new IllegalArgumentException(
                "already put the wire \"" + identifier + "\" in!");
        }
        this.wiresById.put(identifier, wire);
    }

    public Wire getWireById(String id) {
        logger.debug("getting wire \"{}\"", id);

        if (!isKnownId(id)) {
            throw new IllegalArgumentException("unknown identifier: " + id);
        }

        return this.wiresById.get(id);
    }

    public boolean isKnownId(String id) {
        return this.wiresById.containsKey(id);
    }

}

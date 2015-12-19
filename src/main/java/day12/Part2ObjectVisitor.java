package day12;

import day12.json.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Part2ObjectVisitor extends BasicVisitor {

    private static final Logger logger = LoggerFactory.getLogger(Part2ObjectVisitor.class);

    private double sum = 0;
    private boolean hasRedValue = false;

    public Part2ObjectVisitor() {
        logger.debug("constructing instance");
    }

    @Override protected void visiting(JsonObject object) {
        Part2ObjectVisitor visitor = new Part2ObjectVisitor();
        object.visit(visitor);

        sum += visitor.getSum();
    }

    @Override protected void visiting(JsonArray array) {
        Part2ArrayVisitor visitor = new Part2ArrayVisitor();
        array.visit(visitor);
        sum += visitor.getSum();
    }

    @Override protected void visiting(JsonString string) {
        if (string.getValue().equals("red")) {
            hasRedValue = true;
        }
    }

    @Override protected void visiting(JsonNumber number) {
        sum += number.getValue();
    }

    public double getSum() {
        if (hasRedValue()) {
            return 0;
        }
        return sum;
    }

    public boolean hasRedValue() {
        return hasRedValue;
    }
}

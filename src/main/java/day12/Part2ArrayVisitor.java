package day12;

import day12.json.BasicVisitor;
import day12.json.JsonArray;
import day12.json.JsonNumber;
import day12.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Part2ArrayVisitor extends BasicVisitor {

    private static final Logger logger = LoggerFactory.getLogger(Part2ArrayVisitor.class);

    private double sum = 0;

    public Part2ArrayVisitor() {
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

    @Override protected void visiting(JsonNumber number) {
        sum += number.getValue();
    }

    public double getSum() {
        return sum;
    }
}

package day12;

import day12.json.*;

import static com.google.common.base.Preconditions.checkState;

public class Part1Visitor extends BasicVisitor {

    private double sum = 0;

    @Override protected void visiting(JsonNumber number) {
        sum += number.getValue();
    }

    public double getSum() {
        return sum;
    }
}

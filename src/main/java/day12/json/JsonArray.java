package day12.json;

import java.util.ArrayList;

public class JsonArray implements JsonValue, JsonVisitable {
    private final ArrayList<JsonValue> values;

    public JsonArray() {
        this.values = new ArrayList<>();
    }

    public void add(JsonValue value) {
        values.add(value);
    }

    @Override public void visit(JsonVisitor visitor) {
        for (JsonValue value: values) {
            visitor.visiting(value);
        }
    }
}

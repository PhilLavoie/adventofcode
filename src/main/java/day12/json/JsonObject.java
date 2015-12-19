package day12.json;

import java.util.LinkedList;
import java.util.List;

public class JsonObject implements JsonValue, JsonVisitable {

    private final List<JsonObjectField> entries;

    public JsonObject() {
        this.entries = new LinkedList<>();
    }

    public void put(JsonObjectField field) {
        entries.add(field);
    }

    @Override
    public void visit(JsonVisitor visitor) {
        for (JsonObjectField entry: entries) {
            visitor.visiting(entry);
        }
    }
}

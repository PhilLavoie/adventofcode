package day12.json;

public abstract class BasicVisitor implements JsonVisitor {
    @Override public void visiting(JsonComponent value) {
        if (value instanceof JsonObject) {
            visiting((JsonObject)value);
        } else if (value instanceof JsonArray) {
            visiting((JsonArray)value);
        } else if (value instanceof JsonString) {
            visiting((JsonString)value);
        } else if (value instanceof JsonNumber) {
            visiting((JsonNumber)value);
        } else if (value instanceof JsonObjectField) {
            visiting((JsonObjectField)value);
        } else {
            throw new IllegalArgumentException("unknown Json component class: " + value.getClass());
        }
    }

    protected void visiting(JsonObject object) {
        object.visit(this);
    }

    protected void visiting(JsonArray array) {
        array.visit(this);
    }

    protected void visiting(JsonString string) {}
    protected void visiting(JsonNumber number) {}
    protected void visiting(String key) {}

    protected void visiting(JsonObjectField field) {
        visiting(field.getKey());
        visiting(field.getValue());
    }
}


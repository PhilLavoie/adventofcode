package day12.json.serialization;

import day12.json.*;

class SerializerObjectVisitor extends SerializerVisitor {
    private boolean firstAttributeSeen = false;

    public SerializerObjectVisitor(Appendable appendable) {
        super(appendable);
    }

    public Appendable getAppendable() {
        return appendable;
    }

    @Override protected void visiting(JsonObject object) {
        append('{');
        object.visit(new SerializerObjectVisitor(appendable));
        append('}');
    }

    @Override protected void visiting(JsonArray array) {
        append('[');
        array.visit(new SerializerArrayVisitor(appendable));
        append(']');
    }


    @Override protected void visiting(JsonString string) {
        append('\"');
        append(string.getValue());
        append('\"');
    }

    @Override protected void visiting(JsonNumber number) {
        appendNumber(number);
    }

    @Override protected void visiting(String key) {
        append('\"');
        append(key);
        append('\"');
    }

    @Override protected void visiting(JsonObjectField field) {
        if (firstAttributeSeen) {
            append(',');
        } else {
            firstAttributeSeen = true;
        }
        visiting(field.getKey());
        append(':');
        visiting(field.getValue());
    }
}

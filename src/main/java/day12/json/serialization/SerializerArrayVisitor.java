package day12.json.serialization;

import day12.json.*;

public class SerializerArrayVisitor extends SerializerVisitor {
    private boolean firstValueSeen = false;

    public SerializerArrayVisitor(Appendable appendable) {
        super(appendable);
    }

    public Appendable getAppendable() {
        return appendable;
    }

    @Override protected void visiting(JsonObject object) {
        appendDelimiterIfNeeded();
        append('{');
        object.visit(new SerializerObjectVisitor(appendable));
        append('}');
    }

    @Override protected void visiting(JsonArray array) {
        appendDelimiterIfNeeded();
        append('[');
        array.visit(new SerializerArrayVisitor(appendable));
        append(']');
    }

    @Override protected void visiting(JsonString string) {
        appendDelimiterIfNeeded();
        append('\"');
        append(string.getValue());
        append('\"');
    }

    @Override protected void visiting(JsonNumber number) {
        appendDelimiterIfNeeded();
        appendNumber(number);
    }

    private void appendDelimiterIfNeeded() {
        if (firstValueSeen) {
            append(',');
        } else {
            firstValueSeen = true;
        }
    }

}

package day12.json.serialization;

import day12.json.BasicVisitor;
import day12.json.JsonNumber;
import day12.json.JsonString;

import java.io.IOException;

public abstract class SerializerVisitor extends BasicVisitor {

    protected final Appendable appendable;

    public SerializerVisitor(Appendable appendable) {
        this.appendable = appendable;
    }

    public Appendable append(char character) {
        try {
            return appendable.append(character);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Appendable append(CharSequence charSequence) {
        try {
            return appendable.append(charSequence);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Appendable appendNumber(JsonNumber number) {
        try {
            return appendable.append(String.format("%.0f", number.getValue()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Appendable appendString(String string) {
        try {
            append('\"');
            appendable.append(string);
            append('\"');
            return appendable;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Appendable appendString(JsonString string) {
        return appendString(string.getValue());
    }
}

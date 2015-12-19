package day12.json;

public class JsonString implements JsonValue {
    private final String value;

    public JsonString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package day12.json;

public class JsonObjectField implements JsonComponent {
    private final String key;
    private final JsonValue value;

    public JsonObjectField(String key, JsonValue value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public JsonValue getValue() {
        return value;
    }
}

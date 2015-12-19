package day12.json.serialization;

import day12.json.JsonObject;

public class JsonSerializer {

    public Appendable serialize(Appendable appendable, JsonObject object) {
        SerializerObjectVisitor visitor = new SerializerObjectVisitor(appendable);
        visitor.visiting(object);
        return visitor.getAppendable();
    }
}

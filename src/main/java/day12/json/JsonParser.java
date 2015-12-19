package day12.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.CharBuffer;
import util.PeekableCharIterator;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.lang.model.SourceVersion.isIdentifier;

public class JsonParser {

    private static final Logger logger = LoggerFactory.getLogger(JsonParser.class);

    //expects no whitespace.
    public JsonObject parse(PeekableCharIterator iterator) {
        if (!iterator.hasNext()) {
            return new JsonObject();
        }

        char firstChar = iterator.peek();
        checkArgument(firstChar == '{', "unexpected first character: " + firstChar);

        CharBuffer buffer = new CharBuffer();
        return parseObject(buffer, iterator);
    }

    private JsonObject parseObject(CharBuffer buffer, PeekableCharIterator iterator) {
        //Consume first character.
        iterator.next();

        JsonObject object = new JsonObject();
        boolean foundClosingBracket = false;

        while (iterator.hasNext()) {
            char currentChar = iterator.peek();
            if (currentChar == '}') {
                foundClosingBracket = true;
                iterator.next();
                break;
            }

            if (currentChar == ',') {
                iterator.next();
            }
            JsonObjectField field = parseObjectField(buffer, iterator);
            object.put(field);
        }

        checkArgument(foundClosingBracket, "missing closing bracket");
        return object;
    }

    private JsonObjectField parseObjectField(CharBuffer buffer, PeekableCharIterator iterator) {
        String key = parseKey(buffer, iterator);

        char delimiter = iterator.next();
        checkArgument(delimiter == ':', "invalid key/value delimiter: " + delimiter);

        JsonValue value = parseValue(buffer, iterator);
        return new JsonObjectField(key, value);
    }

    private JsonValue parseValue(CharBuffer buffer, PeekableCharIterator iterator) {
        char firstChar = iterator.peek();

        try {
            if (firstChar == '{') {
                return parseObject(buffer, iterator);
            } else if (firstChar == '[') {
                return parseArray(buffer, iterator);
            } else if (firstChar == '\"') {
                return new JsonString(parseString(buffer, iterator));
            } else {
                //Try to parse number.
                return parseNumber(buffer, iterator);
            }
        } finally {
        }
    }

    private JsonValue parseArray(CharBuffer buffer, PeekableCharIterator iterator) {
        //Skip first character.
        iterator.next();

        JsonArray array = new JsonArray();

        boolean foundClosingBracket = true;

        while (iterator.hasNext()) {
            char currentChar = iterator.peek();

            if (currentChar == ']') {
                foundClosingBracket = true;
                iterator.next();
                break;
            }

            if (currentChar == ',') {
                iterator.next();
            }
            array.add(parseValue(buffer, iterator));
        }

        checkArgument(foundClosingBracket, "missing closing bracket for array value");
        return array;
    }

    private JsonValue parseNumber(CharBuffer buffer, PeekableCharIterator iterator) {
        buffer.clear();
        while (iterator.hasNext()) {
            char currentChar = iterator.peek();

            if (currentChar == ',' || currentChar == '}' || currentChar == ']') {
                break;
            }

            iterator.next();
            buffer.append(currentChar);
        }

        String numberString = new String(buffer.getData());
        return new JsonNumber(Double.parseDouble(numberString));
    }

    private String parseString(CharBuffer buffer, PeekableCharIterator iterator) {
        buffer.clear();

        //Skip first quote character.
        iterator.next();

        while (iterator.hasNext()) {
            char currentChar = iterator.next();

            if (currentChar == '\"') {
                break;
            }

            buffer.append(currentChar);
        }

        String string = new String(buffer.getData());
        return string;
    }

    private String parseKey(CharBuffer buffer, PeekableCharIterator iterator) {
        String key = parseString(buffer, iterator);
        checkArgument(isIdentifier(key), "invalid identifier: " + key);

        return key;
    }

}

package day12;

import day12.json.JsonObject;
import day12.json.JsonParser;
import day12.json.serialization.JsonSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.InputStreamCharIterator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        String fileName = args[0];
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("file not found: " + fileName);
            return;
        }

        JsonObject rootObject = new JsonParser().parse(new InputStreamCharIterator(inputStream));

        if (logger.isDebugEnabled()) {
            StringBuilder builder = new StringBuilder();
            JsonSerializer serializer = new JsonSerializer();
            serializer.serialize(builder, rootObject);
            logger.debug("parsed: \n{}\n", builder.toString());
        }

        Part1Visitor visitor = new Part1Visitor();

        rootObject.visit(visitor);

        System.out.println("Sum: " + visitor.getSum());

        Part2ObjectVisitor part2Visitor = new Part2ObjectVisitor();

        rootObject.visit(part2Visitor);

        System.out.println("Sum without reds: " + part2Visitor.getSum());
    }
}

package day18;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.LinkedList;

import static com.google.common.base.Preconditions.checkArgument;

public class GridParser {

    private static Logger logger = LoggerFactory.getLogger(GridParser.class);


    public Grid<Light> parse(Iterator<String> lines) {
        LinkedList<Light[]> content = new LinkedList<>();

        Light[] firstRow = parseLine(lines.next());
        int noCols = firstRow.length;
        content.add(firstRow);

        while (lines.hasNext()) {
            String line = lines.next();
            Light[] currentRow = parseLine(line);
            checkArgument(currentRow.length == noCols,
                "expected the number of columns to be: " + noCols + " for line: " + line);
            content.add(currentRow);
        }

        int noRows = content.size();
        logger.debug("creating grid of size {} X {}", noCols, noRows);
        Light[][] lights = new Light[noRows][];

        int i = 0;
        for (Light[] row: content) {
            lights[i] = row;
            i++;
        }

        return new Grid<>(lights);
    }

    private Light[] parseLine(String line) {
        char[] chars = line.toCharArray();

        //Avoiding the newline character
        Light[] lights = new Light[chars.length];
        for (int i = 0; i < chars.length; i++) {
            lights[i] = parseLight(chars[i]);
        }

        return lights;
    }

    private Light parseLight(char lightCharacter) {
        switch (lightCharacter) {
            case '#':
                return new WorkingLight(State.ON);
            case '.':
                return new WorkingLight(State.OFF);
            default:
                throw new IllegalArgumentException("unknown light character: " + lightCharacter);
        }
    }
}

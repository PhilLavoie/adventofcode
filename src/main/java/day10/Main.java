package day10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.IntBuffer;

import java.util.Iterator;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        IntBuffer buffer1 = new IntBuffer();
        IntBuffer buffer2 = new IntBuffer();

        //Initial input
        fillBufferWithQuestionInput(buffer1);

        System.out.println("Part 1:");
        IntBuffer result = part1(buffer1, buffer2);
        System.out.println("Result: " + bufferString(result));
        System.out.println("Result length: " + result.length());

        buffer1.clear();
        buffer2.clear();

        fillBufferWithQuestionInput(buffer1);

        System.out.println("Part 2:");
        result = part2(buffer1, buffer2);
        System.out.println("Result: " + bufferString(result));
        System.out.println("Result length: " + result.length());
    }

    private static void fillBufferWithQuestionInput(IntBuffer buffer) {
        buffer.append(1, 1, 1, 3, 1, 2, 2, 1, 1, 3);
    }

    private static IntBuffer part1(IntBuffer input, IntBuffer result) {
        for (int i = 0; i < 40; i++) {
            lookAndSay(input, result);

            if (logger.isDebugEnabled()) {
                logger.debug("Look and say:");
                logger.debug("    input:  {}", bufferString(input));
                logger.debug("    result: {}", bufferString(result));
            }

            //The result becomes the input.
            IntBuffer tmp = input;
            input = result;
            result = tmp;
            result.clear();
        }

        //When we finish the result is in input.
        result = input;
        return result;
    }

    private static IntBuffer part2(IntBuffer input, IntBuffer result) {
        for (int i = 0; i < 50; i++) {
            lookAndSay(input, result);

            if (logger.isDebugEnabled()) {
                logger.debug("Look and say:");
                logger.debug("    input:  {}", bufferString(input));
                logger.debug("    result: {}", bufferString(result));
            }

            //The result becomes the input.
            IntBuffer tmp = input;
            input = result;
            result = tmp;
            result.clear();
        }

        //When we finish the result is in input.
        result = input;
        return result;
    }

    private static String bufferString(IntBuffer buffer) {
        StringBuilder builder = new StringBuilder(buffer.length());

        buffer.forEach(value -> builder.append(value));

        return builder.toString();
    }

    private static void lookAndSay(IntBuffer input, IntBuffer result) {
        result.clear();
        Iterator<Integer> iterator = input.iterator();

        int toMatch = iterator.next();
        int count = 1;

        while (iterator.hasNext()) {
            int next = iterator.next();
            if (next == toMatch) {
                count++;
            } else {
                result.append(count);
                result.append(toMatch);

                toMatch = next;
                count = 1;
            }
        }
        //The last one.
        result.append(count);
        result.append(toMatch);
    }
}

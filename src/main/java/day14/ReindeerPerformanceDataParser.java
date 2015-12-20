package day14;

import java.util.stream.Stream;

import static com.google.gson.internal.$Gson$Preconditions.checkArgument;

public class ReindeerPerformanceDataParser {

    public Stream<Reindeer> parse(Stream<String> lines) {
        return lines.map(this::parseLine);
    }

    private Reindeer parseLine(String line) {
        return parseWords(line.split("\\s+"));
    }

    //Input of type:
    //Dasher can fly 10 km/s for 4 seconds, but then must rest for 37 seconds.
    private Reindeer parseWords(String[] words) {
        checkArgument(words.length == 15);
        return reindeerFor(words[0], Integer.parseInt(words[3]), Integer.parseInt(words[6]),
            Integer.parseInt(words[13]));
    }

    private Reindeer reindeerFor(String name, int topSpeed, int burstTime, int sleepTime) {
        return new Reindeer(name, topSpeed, burstTime, sleepTime);
    }
}

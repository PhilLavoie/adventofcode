package day7;

import day7.part1.Part1WireFactory;
import day7.part2.Part2WireFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Main {

    private static void part1(Iterable<String> words) {
        Circuit circuit = new Circuit();
        Part1WireFactory part1WireFactory = new Part1WireFactory(circuit);

        Iterator<String> wordsIter = words.iterator();
        while (wordsIter.hasNext()) {
            Wire wire = part1WireFactory.fromFormalDefinition(wordsIter);
            circuit.putWire(wire);
        }

        System.out.println("Wire a value: " + circuit.getWireById("a").getValue());
    }

    public static void part2(String[] args) {
        Circuit circuit = new Circuit();
        Part2WireFactory wireFactory = new Part2WireFactory(circuit);

        List<String> words = newArrayList(args);
        Iterator<String> wordsIter = words.iterator();

        while (wordsIter.hasNext()) {
            Wire wire = wireFactory.fromFormalDefinition(wordsIter);
            circuit.putWire(wire);
        }

        System.out.println("Wire a value: " + circuit.getWireById("a").getValue());
    }

    public static void main(String[] args) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(args[0]));
        } catch (FileNotFoundException e) {
            System.out.println("unable to find file: " + args[0]);
            return;
        }

        ArrayList<String> words = new ArrayList<>();

        bufferedReader.lines().forEach(line -> {
            for (String word : line.split("\\s+")) {
                words.add(word);
            }
        });

        part1(words);
        //part2(args);
    }
}

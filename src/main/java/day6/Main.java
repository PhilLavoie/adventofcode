package day6;

import day6.part1.Part1Grid;
import day6.part2.Part2Grid;
import util.Procedure;

import java.util.Iterator;

import static com.google.common.collect.Lists.newArrayList;

public class Main {

    private static void part1(Iterable<String> words) {
        System.out.println("part 1");

        Iterator<String> wordsIter = words.iterator();

        Boolean[][] lights = new Boolean[1000][1000];

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                lights[i][j] = Boolean.FALSE;
            }
        }

        Part1Grid theGrid = new Part1Grid(lights);
        CommandMapper commandMapper = new CommandMapper(theGrid);

        while (wordsIter.hasNext()) {
            Procedure procedure = commandMapper.commandFor(wordsIter);
            procedure.execute();
        }

        System.out.println("number of lights lit: " + theGrid.getNumberOfLightsOn());
    }

    private static void part2(Iterable<String> words) {
        System.out.println("part 2");

        Iterator<String> wordsIter = words.iterator();

        Integer[][] lights = new Integer[1000][1000];

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                lights[i][j] = new Integer(0);
            }
        }

        Part2Grid theGrid = new Part2Grid(lights);
        CommandMapper commandMapper = new CommandMapper(theGrid);

        while (wordsIter.hasNext()) {
            Procedure procedure = commandMapper.commandFor(wordsIter);
            procedure.execute();
        }

        System.out.println("total brightness: " + theGrid.getTotalBrightness());
    }

    public static void main(String[] args) {

        Iterable<String> words = newArrayList(args);
        part1(words);
        part2(words);
    }
}

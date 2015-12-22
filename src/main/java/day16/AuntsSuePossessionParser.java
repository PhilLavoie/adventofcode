package day16;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;



public class AuntsSuePossessionParser {

    private static Logger logger = LoggerFactory.getLogger(AuntsSuePossessionParser.class);

    private Map<String, Possession> compoundsAsStrings;

    public AuntsSuePossessionParser() {
        compoundsAsStrings = new HashMap<>();
        compoundsAsStrings.put("children", Possession.CHILDREN);
        compoundsAsStrings.put("cats", Possession.CATS);
        compoundsAsStrings.put("samoyeds", Possession.SAMOYEDS);
        compoundsAsStrings.put("pomeranians", Possession.POMERANIANS);
        compoundsAsStrings.put("akitas", Possession.AKITAS);
        compoundsAsStrings.put("vizslas", Possession.VIZSLAS);
        compoundsAsStrings.put("goldfish", Possession.GOLDFISH);
        compoundsAsStrings.put("trees", Possession.TREES);
        compoundsAsStrings.put("cars", Possession.CARS);
        compoundsAsStrings.put("perfumes", Possession.PERFUMES);
    }

    public Stream<AuntSue> parse(Stream<String> lines) {
        return lines.map(this::parseLine);
    }

    //Input of type:
    //Sue 260: goldfish: 0, vizslas: 0, samoyeds: 2
    private AuntSue parseLine(String line) {
        String[] words = line.split("\\s+");
        String identifier = words[1].substring(0, words[1].length() - 1);

        List<String> possessionData = Arrays.asList(words).subList(2, words.length);
        try {
            return AuntSue
                .withPossessions(Integer.parseInt(identifier), parsePossessions(possessionData));
        } catch (Exception e) {
            throw new RuntimeException("could not parse aunt sue data: " + line, e);
        }
    }

    private Iterable<PossessionAmount> parsePossessions(List<String> possessionData) {
        int dataSize = possessionData.size();
        checkArgument(dataSize % 2 == 0);

        ArrayList<PossessionAmount> possessionsAmounts = new ArrayList<>();

        Iterator<String> wordsIterator = possessionData.iterator();
        while (wordsIterator.hasNext()) {
            String firstWord = wordsIterator.next();
            String secondWord = wordsIterator.next();
            possessionsAmounts.add(parsePossessionAmount(firstWord, secondWord));
        }

        return possessionsAmounts;
    }

    private PossessionAmount parsePossessionAmount(String firstWord, String secondWord) {
        //Remove the ':'
        String possessionString = firstWord.substring(0, firstWord.length() - 1);

        //Match it.
        checkArgument(compoundsAsStrings.containsKey(possessionString),
            "unknown possession: " + possessionString);

        String amountString = secondWord.endsWith(",") ?
            secondWord.substring(0, secondWord.length() - 1) :
            secondWord;

        return new PossessionAmount(compoundsAsStrings.get(possessionString),
            Integer.parseInt(amountString));
    }


}



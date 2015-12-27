package day19;

import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;
import day19.molecules.*;

import java.util.ArrayList;
import java.util.Iterator;

import static com.google.common.base.Preconditions.checkArgument;

public class MoleculesParser {

    public class ParsingResult {
        public final Molecules molecules;
        public final ImmutableCompositeMolecule initialMolecule;

        public ParsingResult(Molecules molecules, ImmutableCompositeMolecule initialMolecule) {
            this.molecules = molecules;
            this.initialMolecule = initialMolecule;
        }
    }

    private final CompositeMoleculeStringParser parser;

    public MoleculesParser(CompositeMoleculeStringParser parser) {
        this.parser = parser;
    }

    public ParsingResult parse(Iterator<String> lines) {
        return parse(Iterators.peekingIterator(lines));
    }

    public ParsingResult parse(PeekingIterator<String> lines) {
        Molecules molecules = parseMolecules(lines);

        //Skip the empty line.
        checkArgument(lines.hasNext(), "missing initial molecule composition");
        lines.next();
        //Check still has a line.
        checkArgument(lines.hasNext(), "missing initial molecule composition");

        ImmutableCompositeMolecule initialMolecule = parseInitialMolecule(lines, molecules);

        return new ParsingResult(molecules, initialMolecule);
    }

    private Molecules parseMolecules(PeekingIterator<String> lines) {
        Molecules molecules = new Molecules(parser);

        while (lines.hasNext()) {
            String line = lines.peek().trim();
            if (line.isEmpty()) {
                return molecules;
            }
            lines.next();
            parseMolecule(line, molecules);
        }

        throw new IllegalArgumentException("missing terminating empty line");
    }

    //Input of type: F => CaF
    private void parseMolecule(String line, Molecules molecules) {
        String[] words = line.split("=>");
        checkArgument(words.length == 2, "invalid molecule replacement specification: " + line);
        String atomicMolecule = words[0].trim();
        String replacementMolecule = words[1].trim();
        molecules.addReplacementForMolecule(atomicMolecule, replacementMolecule);
    }

    private ImmutableCompositeMolecule parseInitialMolecule(PeekingIterator<String> lines, Molecules molecules) {
        String moleculeComposition = lines.next();

        ArrayList<Molecule> composition = new ArrayList<>();
        //Enforce all molecules are known already.
        for (String atomicMolecule: parser.atomicMoleculesOf(moleculeComposition)) {
            if (!molecules.hasAtomicMolecule(atomicMolecule)) {
                molecules.addMolecule(atomicMolecule);
            }
            composition.add(molecules.getAtomicMolecule(atomicMolecule));
        }

        return new ImmutableCompositeMolecule(composition);
    }
}

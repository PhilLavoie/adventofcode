package day19;

import day19.molecules.CompositeMolecule;
import day19.molecules.Molecule;
import day19.molecules.MutableCompositeMolecule;
import util.functional.Procedure;
import util.functional.SequencedProcedures;

import java.util.Iterator;
import java.util.LinkedList;

public class OneReplacementMoleculeIterable implements Iterable<CompositeMolecule> {

    private final CompositeMolecule molecule;

    public OneReplacementMoleculeIterable(CompositeMolecule molecule) {
        this.molecule = molecule;
    }

    @Override public Iterator<CompositeMolecule> iterator() {
        return new OneReplacementMoleculeIterator(
            new MutableCompositeMolecule(molecule.getComposition()));
    }

    private static class OneReplacementMoleculeIterator implements Iterator<CompositeMolecule> {
        private final MutableCompositeMolecule molecule;
        private final LinkedList<Procedure> replacements;

        public OneReplacementMoleculeIterator(MutableCompositeMolecule molecule) {
            this.molecule = molecule;
            this.replacements = new LinkedList<>();

            fillReplacements();
        }

        private void fillReplacements() {
            int index = 0;
            Procedure previouslyReplacedMoleculeResetter = null;
            for (Molecule composite : molecule.getComposition()) {
                if (composite.hasReplacements()) {
                    Iterator<Molecule> replacementMoleculesIterator =
                        composite.getReplacements().iterator();

                    Molecule replacementMolecule = replacementMoleculesIterator.next();
                    Replacement replacementProcedure =
                        new Replacement(index, replacementMolecule, molecule);

                    if (previouslyReplacedMoleculeResetter != null) {
                        replacements.add(new SequencedProcedures(
                            previouslyReplacedMoleculeResetter,
                            replacementProcedure));
                    } else {
                        replacements.add(replacementProcedure);
                    }

                    while (replacementMoleculesIterator.hasNext()) {
                        replacementMolecule = replacementMoleculesIterator.next();
                        replacementProcedure = new Replacement(index, replacementMolecule, molecule);
                        replacements.add(replacementProcedure);
                    }

                    previouslyReplacedMoleculeResetter =
                        new Replacement(index, composite, molecule);
                }
                index++;
            }
        }

        @Override public boolean hasNext() {
            return !replacements.isEmpty();
        }

        @Override public CompositeMolecule next() {
            replacements.getFirst().execute();
            replacements.removeFirst();
            return molecule;
        }
    }
}

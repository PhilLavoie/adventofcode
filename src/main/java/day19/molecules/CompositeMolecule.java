package day19.molecules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CompositeMolecule implements Molecule {

    public abstract List<? extends Molecule> getComposition();

    @Override public Iterable<Molecule> getReplacements() {
        return Collections.emptyList();
    }

    @Override public String getRepresentation() {
        StringBuilder builder = new StringBuilder();

        for (Molecule molecule: getComposition()) {
            builder.append(molecule.getRepresentation());
        }

        return builder.toString();
    }

    public List<AtomicMolecule> getAtomicComposition() {
        ArrayList<AtomicMolecule> atomicMolecules = new ArrayList<>();

        for (Molecule molecule: getComposition()) {
            if (molecule instanceof AtomicMolecule) {
                atomicMolecules.add((AtomicMolecule) molecule);
            } else {
                atomicMolecules.addAll(((CompositeMolecule) molecule).getAtomicComposition());
            }
        }

        return atomicMolecules;
    }

    @Override public boolean hasReplacements() {
        return false;
    }
}

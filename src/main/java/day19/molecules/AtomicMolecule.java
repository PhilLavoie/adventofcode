package day19.molecules;

import java.util.ArrayList;
import java.util.List;

public class AtomicMolecule implements Molecule {

    private final String representation;

    private final List<Molecule> replacements;

    public AtomicMolecule(String representation) {
        this.representation = representation;
        this.replacements = new ArrayList<>();
    }

    void addReplacements(Molecule replacement) {
        replacements.add(replacement);
    }

    @Override
    public List<Molecule> getReplacements() {
        return replacements;
    }

    @Override
    public String getRepresentation() {
        return representation;
    }

    @Override public boolean hasReplacements() {
        return !replacements.isEmpty();
    }
}

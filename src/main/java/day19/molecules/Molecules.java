package day19.molecules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkState;

public class Molecules {
    private final Map<String, AtomicMolecule> atomicMolecules;
    private final Map<String, CompositeMolecule> compositeMolecules;
    private final CompositeMoleculeStringParser parser;

    public Molecules(CompositeMoleculeStringParser parser) {
        this.parser = parser;
        this.atomicMolecules = new HashMap<>();
        this.compositeMolecules = new HashMap<>();
    }

    public void addReplacementForMolecule(String molecule, String replacement) {
        createAndAddAtomicMoleculeIfNecessary(molecule);
        ArrayList<Molecule> replacementAtomicMolecules = new ArrayList<>();
        for (String atomicMolecule : parser.atomicMoleculesOf(replacement)) {
            createAndAddAtomicMoleculeIfNecessary(atomicMolecule);
            replacementAtomicMolecules.add(atomicMolecules.get(atomicMolecule));
        }

        //If replacement is an atomic molecule
        if (hasAtomicMolecule(replacement)) {
            atomicMolecules.get(molecule).addReplacements(atomicMolecules.get(replacement));
        } else {
            if (!compositeMolecules.containsKey(replacement)) {
                compositeMolecules
                    .put(replacement, new ImmutableCompositeMolecule(replacementAtomicMolecules));
            }
            atomicMolecules.get(molecule).addReplacements(compositeMolecules.get(replacement));
        }
    }

    private void createAndAddAtomicMoleculeIfNecessary(String moleculeName) {
        if (atomicMolecules.containsKey(moleculeName)) {
            return;
        }
        atomicMolecules.put(moleculeName, new AtomicMolecule(moleculeName));
    }

    public boolean hasAtomicMolecule(String molecule) {
        return atomicMolecules.containsKey(molecule);
    }

    public AtomicMolecule getAtomicMolecule(String atomicMolecule) {
        return atomicMolecules.get(atomicMolecule);
    }

    public void addMolecule(String atomicMolecule) {
        checkState(!hasAtomicMolecule(atomicMolecule), "already added molecule: " + atomicMolecule);

        atomicMolecules.put(atomicMolecule, new AtomicMolecule(atomicMolecule));
    }
}

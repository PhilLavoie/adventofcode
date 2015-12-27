package day19;

import day19.molecules.Molecule;
import day19.molecules.MutableCompositeMolecule;
import util.functional.Procedure;

public class Replacement implements Procedure {
    private final int index;
    private final Molecule replacementMolecule;
    private final MutableCompositeMolecule compositeMolecule;

    public Replacement(int index, Molecule replacementMolecule,
        MutableCompositeMolecule compositeMolecule) {
        this.index = index;
        this.replacementMolecule = replacementMolecule;
        this.compositeMolecule = compositeMolecule;
    }

    @Override public void execute() {
        compositeMolecule.replaceAt(index, replacementMolecule);
    }
}

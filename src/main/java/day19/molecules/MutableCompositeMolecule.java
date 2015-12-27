package day19.molecules;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkElementIndex;

public class MutableCompositeMolecule extends CompositeMolecule {
    private final ArrayList<Molecule> composition;

    public MutableCompositeMolecule(List<? extends Molecule> composition) {
        this.composition = new ArrayList<>(composition);
    }

    public void replaceAt(int index, Molecule newMolecule) {
        checkElementIndex(index, composition.size());
        composition.set(index, newMolecule);
    }


    @Override public List<Molecule> getComposition() {
        return composition;
    }
}

package day19.molecules;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImmutableCompositeMolecule extends CompositeMolecule {
    private final List<Molecule> composition;

    public ImmutableCompositeMolecule(Iterable<? extends Molecule> composition) {
        this.composition = ImmutableList.copyOf(composition);
    }

    public ImmutableCompositeMolecule(Molecule... composition) {
        this(Arrays.asList(composition));
    }

    @Override
    public List<Molecule> getComposition() {
        return composition;
    }
}

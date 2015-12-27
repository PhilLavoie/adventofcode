package day19.molecules;

import util.iterables.StringIterable;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Character.isLowerCase;
import static util.iterables.StringIterable.iterableOf;

public class CompositeMoleculeStringParser {

    //An atomic molecule is either a single capital letter or
    //a capital letter followed by a lower case letter.
    public Iterable<String> atomicMoleculesOf(String molecule) {
        ArrayList<String> atomicMolecules = new ArrayList<>();

        StringBuilder builder = new StringBuilder();

        for (Character character: iterableOf(molecule)) {
            int length = builder.length();

            if (length == 0) {
                builder.append(character);
            } else {
                boolean lowerCase = isLowerCase(character);
                if (lowerCase) {
                    builder.append(character);
                }
                atomicMolecules.add(builder.toString());
                builder.delete(0, builder.length());

                if (!lowerCase) {
                    builder.append(character);
                }
            }
        }

        if (builder.length() > 0) {
            atomicMolecules.add(builder.toString());
            builder.delete(0, builder.length());
        }
        checkArgument(builder.length() == 0);

        return atomicMolecules;
    }
}

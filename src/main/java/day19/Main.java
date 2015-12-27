package day19;

import day19.molecules.CompositeMolecule;
import day19.molecules.CompositeMoleculeStringParser;
import day19.molecules.ImmutableCompositeMolecule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        FileReader reader;
        String fileName = args[0];

        try {
            reader = new FileReader(fileName);
        } catch (FileNotFoundException exception) {
            System.out.println("could not find file: " + fileName);
            return;
        }

        CompositeMoleculeStringParser parser = new CompositeMoleculeStringParser();
        MoleculesParser moleculesParser = new MoleculesParser(parser);

        MoleculesParser.ParsingResult result =
            moleculesParser.parse(new BufferedReader(reader).lines().iterator());
        ImmutableCompositeMolecule initialMolecule = result.initialMolecule;

        //calibration(initialMolecule);
        moleculeGeneration(new ImmutableCompositeMolecule(result.molecules.getAtomicMolecule("e")),
            initialMolecule);
    }

    private static void calibration(ImmutableCompositeMolecule initialMolecule) {
        System.out.println("part 1");

        Set<String> generatedMolecules = new HashSet<>();
        for (CompositeMolecule compositeMolecule : new OneReplacementMoleculeIterable(
            initialMolecule)) {
            String representation = compositeMolecule.getRepresentation();
            generatedMolecules.add(representation);
            if (logger.isDebugEnabled()) {
                logger.debug("generated molecule: {}", representation);
            }
        }

        System.out.println(
            "Number of different molecules generated after one replacement: " + generatedMolecules
                .size());
    }

    private static void moleculeGeneration(ImmutableCompositeMolecule startMolecule,
        ImmutableCompositeMolecule stopMolecule) {
        System.out.println("part 2");

        //We will compare, at every step, if we were able generate the molecule.
        String stopMoleculeString = stopMolecule.getRepresentation();

        if (logger.isDebugEnabled()) {
            String startMoleculeString = startMolecule.getRepresentation();
            logger.debug("starting molecule generation\nStart molecule: {}\nStop molecule: {}",
                startMoleculeString, stopMoleculeString);
        }

        HashSet<String> alreadyGeneratedMolecule = new HashSet<>();
        LinkedList<ImmutableCompositeMolecule> currentMolecules = new LinkedList<>();
        currentMolecules.add(startMolecule);
        LinkedList<ImmutableCompositeMolecule> nextStepMolecules = new LinkedList<>();
        int currentStep = 0;

        //Until we get there.
        all:
        for (; ; ) {
            //We generate and copy all possible molecule for every step.
            for (ImmutableCompositeMolecule molecule : currentMolecules) {
                String moleculeRepresentation = molecule.getRepresentation();

                if (alreadyGeneratedMolecule.contains(moleculeRepresentation)) {
                    continue;
                } else {
                    alreadyGeneratedMolecule.add(moleculeRepresentation);
                }

                logger.debug("comparing molecule: {}", moleculeRepresentation);
                if (moleculeRepresentation.equals(stopMoleculeString)) {
                    break all;
                }

                for (CompositeMolecule compositeMolecule : new OneReplacementMoleculeIterable(
                    molecule)) {
                    nextStepMolecules
                        .add(new ImmutableCompositeMolecule(compositeMolecule.getAtomicComposition()));
                }

            }

            currentMolecules.clear();
            LinkedList<ImmutableCompositeMolecule> swap = currentMolecules;
            currentMolecules = nextStepMolecules;
            nextStepMolecules = swap;

            currentStep++;
        }

        System.out.println("Made it in : " + currentStep + " steps");
    }
}

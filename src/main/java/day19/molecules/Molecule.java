package day19.molecules;

public interface Molecule {
    String getRepresentation();
    boolean hasReplacements();
    Iterable<Molecule> getReplacements();
}

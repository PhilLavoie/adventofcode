package util.functional;

public class SequencedProcedures implements Procedure {
    private final Procedure[] procedures;

    public SequencedProcedures(Procedure... procedures) {
        this.procedures = procedures;
    }

    @Override public void execute() {
        for (Procedure procedure: procedures) {
            procedure.execute();
        }
    }
}

package day18;

public class WorkingLight implements Light {
    private State state;

    public WorkingLight(State initialState) {
        this.state = initialState;
    }

    @Override public boolean isOn() {
        return state == State.ON;
    }

    @Override public void turn(State newState) {
        state = newState;
    }

    @Override
    public WorkingLight clone() {
        return new WorkingLight(state);
    }

}

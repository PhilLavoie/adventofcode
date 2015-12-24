package day18;

public class AlwaysOnLight implements Light {
    @Override public boolean isOn() {
        return true;
    }

    @Override public void turn(State onOrOff) {}

    @Override public Light clone() {
        return new AlwaysOnLight();
    }
}

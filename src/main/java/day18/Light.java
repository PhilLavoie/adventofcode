package day18;

public interface Light {

    boolean isOn();
    void turn(State onOrOff);
    Light clone();
}

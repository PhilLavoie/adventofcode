package day20;

import java.util.Iterator;

public class HouseGenerator implements Iterable<House>{

    @Override public Iterator<House> iterator() {
        return new HouseGeneratorIterator();
    }

    private static class HouseGeneratorIterator implements Iterator<House> {
        private int currentNumber = 0;

        @Override public boolean hasNext() {
            return true;
        }

        @Override public House next() {
            currentNumber++;
            return new House(currentNumber);
        }
    }
}

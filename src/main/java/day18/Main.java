package day18;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
        FileReader reader;
        String fileName = args[0];
        try {
            reader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("could not find file: " + fileName);
            return;
        }

        Grid<Light> grid = new GridParser().parse(new BufferedReader(reader).lines().iterator());

        //Setting the broken lights for part 2.
        AlwaysOnLight alwaysOnLight = new AlwaysOnLight();
        grid.set(0, 0, alwaysOnLight);
        grid.set(0, grid.getNoColumns() - 1, alwaysOnLight);
        grid.set(grid.getNoRows() - 1, 0, alwaysOnLight);
        grid.set(grid.getNoRows() - 1, grid.getNoColumns() - 1, alwaysOnLight);

        GridStateIterator iterator = new GridStateIterator(grid);

        Grid<Light> currentGrid = null;
        for (int i = 0; i < 100; i++) {
            currentGrid = iterator.next();
        }

        int noLightsOn = 0;
        for (int i = 0; i < currentGrid.getNoRows(); i++) {
            for (int j = 0; j < currentGrid.getNoColumns(); j++) {
                if (currentGrid.get(i, j).isOn()) {
                    noLightsOn++;
                }
            }
        }

        System.out.println("there are: " + noLightsOn + " lights on at the final state.");

    }
}

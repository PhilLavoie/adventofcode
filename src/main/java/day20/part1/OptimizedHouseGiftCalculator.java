package day20.part1;

import day20.House;
import day20.HouseGiftCalculator;

public class OptimizedHouseGiftCalculator implements HouseGiftCalculator {
    @Override public int numberOfGiftsForHouse(House house) {
        int houseNumber = house.getNumber();
        if (houseNumber == 1) {
            return 10;
        }

        int squareRootUpperBound = (int) Math.sqrt(houseNumber) + 1;
        int sum = 0;
        for (int i = 1; i < squareRootUpperBound; i ++) {
            if (houseNumber % i == 0) {
                sum += i;
                int divisor = houseNumber / i;
                if (divisor != i) {
                    sum += divisor;
                }
            }
        }
        return sum * 10;
    }
}

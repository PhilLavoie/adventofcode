package day20.part1;

import day20.House;
import day20.HouseGiftCalculator;

public class NaiveHouseGiftCalculator implements HouseGiftCalculator {

    @Override
    public int numberOfGiftsForHouse(House house) {
        int houseNumber = house.getNumber();
        int noGifts = 0;
        for (int i = 1; i <= houseNumber; i++) {
            if (houseNumber % i == 0) {
                noGifts += i;
            }
        }
        return 10 * noGifts;
    }
}

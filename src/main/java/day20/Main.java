package day20;

import day20.part2.OptimizedHouseGiftCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        final int input = 36000000;

        HouseGiftCalculator calculator = new OptimizedHouseGiftCalculator();

        int firstHouseWithAtLeastInputGifts = -1;
        for (House house : new HouseGenerator()) {
            int numberOfGiftsForHouse = calculator.numberOfGiftsForHouse(house);
            int houseNumber = house.getNumber();

            if (logger.isTraceEnabled()) {
                logger.trace("number of gifts for house {} is {}", houseNumber,
                    numberOfGiftsForHouse);
            } else {
                if (houseNumber % 1000 == 0) {
                    logger.debug("number of gifts for house {} is {}", houseNumber,
                        numberOfGiftsForHouse);
                }
            }
            if (numberOfGiftsForHouse >= input) {
                firstHouseWithAtLeastInputGifts = houseNumber;
                break;
            }
        }

        System.out.println(
            "First house with at least " + input + " gifts is: " + firstHouseWithAtLeastInputGifts);
    }
}

package day21;

import com.google.common.collect.FluentIterable;
import day21.gear.Armor;
import day21.gear.Inventory;
import day21.gear.Ring;
import day21.gear.Weapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.iterables.NestedPairsIterable;
import util.tuples.Pair;
import util.tuples.ValuePair;

import java.util.Arrays;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Iterable<Weapon> weapons = Arrays.asList(Weapon.values());
        Iterable<Armor> armors =
            FluentIterable.from(Arrays.asList(Armor.values())).append((Armor) null);
        Iterable<Pair<Ring, Ring>> rings = FluentIterable.from(
            new NestedPairsIterable<>(((left, right) -> new ValuePair<>(left, right)),
                FluentIterable.from(Arrays.asList(Ring.values())).append((Ring) null)))
            .append(new ValuePair<>(null, null));

        part1(weapons, armors, rings);
        part2(weapons, armors, rings);
    }

    private static void part2(Iterable<Weapon> weapons, Iterable<Armor> armors,
        Iterable<Pair<Ring, Ring>> rings) {
        System.out.println("Part 2");

        FightWinnerCalculator calculator = new FightWinnerCalculator();
        Boss boss = new Boss();
        Player player;

        int mostExpensiveLosingInventoryCost = Integer.MIN_VALUE;
        Inventory mostExpensiveLosingInventory = null;

        for (Inventory inventory : InventoryEnumerator.forItems(weapons, armors, rings)) {
            logger.debug(
                "generated inventory:\n  weapon: {}, armor: {}, left hand ring: {}, right hand ring: {}",
                inventory.getWeapon(), inventory.getArmor(), inventory.getLeftRing(),
                inventory.getRightRing());

            player = new Player(inventory);

            boolean playerWins = calculator.winnerBetween(player, boss) == player;

            if (playerWins) {
                logger.debug("player wins");
            } else {
                logger.debug("boss wins");
            }

            if (!playerWins && inventory.getTotalCost() > mostExpensiveLosingInventoryCost) {
                mostExpensiveLosingInventory = inventory;
                mostExpensiveLosingInventoryCost = inventory.getTotalCost();
            }
        }

        System.out.println(
            "Most expensive losing inventory cost is: " + mostExpensiveLosingInventoryCost);
        System.out.println(
            "Inventory is:\n  " + "weapon: " + mostExpensiveLosingInventory.getWeapon() + " armor: "
                + mostExpensiveLosingInventory.getArmor() + " left hand ring: "
                + mostExpensiveLosingInventory.getLeftRing() + " right hand ring: "
                + mostExpensiveLosingInventory.getRightRing());
    }

    private static void part1(Iterable<Weapon> weapons, Iterable<Armor> armors,
        Iterable<Pair<Ring, Ring>> rings) {
        System.out.println("Part 1");
        FightWinnerCalculator calculator = new FightWinnerCalculator();
        Boss boss = new Boss();
        Player player;

        int leastExpensiveInventoryCost = Integer.MAX_VALUE;
        Inventory leastExpensiveWinningInventory = null;

        for (Inventory inventory : InventoryEnumerator.forItems(weapons, armors, rings)) {
            logger.debug(
                "generated inventory:\n  weapon: {}, armor: {}, left hand ring: {}, right hand ring: {}",
                inventory.getWeapon(), inventory.getArmor(), inventory.getLeftRing(),
                inventory.getRightRing());

            player = new Player(inventory);

            boolean playerWins = calculator.winnerBetween(player, boss) == player;

            if (playerWins) {
                logger.debug("player wins");
            } else {
                logger.debug("boss wins");
            }

            if (playerWins && inventory.getTotalCost() < leastExpensiveInventoryCost) {
                leastExpensiveWinningInventory = inventory;
                leastExpensiveInventoryCost = inventory.getTotalCost();
            }
        }

        System.out
            .println("Least expensive winning inventory cost is: " + leastExpensiveInventoryCost);
        System.out.println(
            "Inventory is:\n  " + "weapon: " + leastExpensiveWinningInventory.getWeapon()
                + " armor: " + leastExpensiveWinningInventory.getArmor() + " left hand ring: "
                + leastExpensiveWinningInventory.getLeftRing() + " right hand ring: "
                + leastExpensiveWinningInventory.getRightRing());
    }
}

package day21;

import day21.gear.Armor;
import day21.gear.Inventory;
import day21.gear.Ring;
import day21.gear.Weapon;
import util.iterables.NestedIterablesIterable;
import util.tuples.Pair;

import java.util.Iterator;

public class InventoryEnumerator implements Iterable<Inventory> {

    private final NestedIterablesIterable<Inventory> inventoryGenerator;

    InventoryEnumerator(Iterable<Weapon> weapons, Iterable<Armor> armors,
        Iterable<Pair<Ring, Ring>> rings) {
        inventoryGenerator = NestedIterablesIterable.fromIterables(objects -> {
            Weapon weapon = (Weapon) objects[0];
            Armor armor = (Armor) objects[1];
            Pair<Ring, Ring> myPrecious = (Pair<Ring, Ring>) objects[2];

            return new Inventory(weapon, armor, myPrecious.getLeft(), myPrecious.getRight());
        }, weapons, armors, rings);
    }

    public static InventoryEnumerator forItems(Iterable<Weapon> weapons, Iterable<Armor> armors,
        Iterable<Pair<Ring, Ring>> rings) {
        return new InventoryEnumerator(weapons, armors, rings);
    }

    @Override public Iterator<Inventory> iterator() {
        return inventoryGenerator.iterator();
    }


}

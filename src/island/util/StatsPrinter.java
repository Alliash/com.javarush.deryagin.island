package island.util;

import island.model.Island;
import island.model.Location;
import island.model.animals.*;

import java.util.HashMap;
import java.util.Map;

public class StatsPrinter {

    public static void print(Island island, int tick) {
        int plantsTotal = 0;
        int animalsTotal = 0;

        Map<Class<?>, Integer> counts = new HashMap<>();

        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Location loc = island.getLocation(x, y);

                plantsTotal += loc.getPlants();

                for (Animal a : loc.getAnimals()) {
                    animalsTotal++;
                    counts.put(a.getClass(), counts.getOrDefault(a.getClass(), 0) + 1);
                }
            }
        }

        System.out.println(" День #" + tick );
        System.out.println("🌿 Растений всего: " + plantsTotal);
        System.out.println("🐾 Животных всего: " + animalsTotal);
        System.out.println();

        printLine(counts, Wolf.class, new Wolf());
        printLine(counts, Boa.class, new Boa());
        printLine(counts, Fox.class, new Fox());
        printLine(counts, Bear.class, new Bear());
        printLine(counts, Eagle.class, new Eagle());

        System.out.println("----------------------------------------");

        printLine(counts, Horse.class, new Horse());
        printLine(counts, Deer.class, new Deer());
        printLine(counts, Rabbit.class, new Rabbit());
        printLine(counts, Mouse.class, new Mouse());
        printLine(counts, Goat.class, new Goat());
        printLine(counts, Sheep.class, new Sheep());
        printLine(counts, Boar.class, new Boar());
        printLine(counts, Buffalo.class, new Buffalo());
        printLine(counts, Duck.class, new Duck());
        printLine(counts, Caterpillar.class, new Caterpillar());

        System.out.println();
    }

    private static void printLine(Map<Class<?>, Integer> counts, Class<?> clazz, Animal example) {
        int c = counts.getOrDefault(clazz, 0);
        System.out.println(example.getIcon() + " " + example.getName() + ": " + c);
    }

    private StatsPrinter() {
    }
}

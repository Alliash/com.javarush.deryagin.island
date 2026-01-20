package island.util;
import island.model.animals.*;

import java.util.HashMap;
import java.util.Map;

public class FoodChance {

    public static final Class<?> PLANTS = PlantsKey.class;

    private static final Map<Class<?>, Map<Class<?>, Integer>> table = new HashMap<>();

    static {
        put(Wolf.class, Boa.class, 0);
        put(Wolf.class, Fox.class, 0);
        put(Wolf.class, Bear.class, 0);
        put(Wolf.class, Eagle.class, 0);
        put(Wolf.class, Horse.class, 10);
        put(Wolf.class, Deer.class, 15);
        put(Wolf.class, Rabbit.class, 60);
        put(Wolf.class, Mouse.class, 80);
        put(Wolf.class, Goat.class, 60);
        put(Wolf.class, Sheep.class, 70);
        put(Wolf.class, Boar.class, 15);
        put(Wolf.class, Buffalo.class, 10);
        put(Wolf.class, Duck.class, 40);
        put(Wolf.class, Caterpillar.class, 0);
        put(Wolf.class, PLANTS, 0);

        put(Boa.class, Wolf.class, 0);
        put(Boa.class, Fox.class, 15);
        put(Boa.class, Bear.class, 0);
        put(Boa.class, Eagle.class, 0);
        put(Boa.class, Horse.class, 0);
        put(Boa.class, Deer.class, 0);
        put(Boa.class, Rabbit.class, 20);
        put(Boa.class, Mouse.class, 40);
        put(Boa.class, Goat.class, 0);
        put(Boa.class, Sheep.class, 0);
        put(Boa.class, Boar.class, 0);
        put(Boa.class, Buffalo.class, 0);
        put(Boa.class, Duck.class, 10);
        put(Boa.class, Caterpillar.class, 0);
        put(Boa.class, PLANTS, 0);

        put(Fox.class, Wolf.class, 0);
        put(Fox.class, Boa.class, 0);
        put(Fox.class, Bear.class, 0);
        put(Fox.class, Eagle.class, 0);
        put(Fox.class, Horse.class, 0);
        put(Fox.class, Deer.class, 0);
        put(Fox.class, Rabbit.class, 70);
        put(Fox.class, Mouse.class, 90);
        put(Fox.class, Goat.class, 0);
        put(Fox.class, Sheep.class, 0);
        put(Fox.class, Boar.class, 0);
        put(Fox.class, Buffalo.class, 0);
        put(Fox.class, Duck.class, 60);
        put(Fox.class, Caterpillar.class, 40);
        put(Fox.class, PLANTS, 0);

        put(Bear.class, Wolf.class, 0);
        put(Bear.class, Boa.class, 80);
        put(Bear.class, Fox.class, 0);
        put(Bear.class, Eagle.class, 0);
        put(Bear.class, Horse.class, 40);
        put(Bear.class, Deer.class, 80);
        put(Bear.class, Rabbit.class, 80);
        put(Bear.class, Mouse.class, 90);
        put(Bear.class, Goat.class, 70);
        put(Bear.class, Sheep.class, 70);
        put(Bear.class, Boar.class, 50);
        put(Bear.class, Buffalo.class, 20);
        put(Bear.class, Duck.class, 10);
        put(Bear.class, Caterpillar.class, 0);
        put(Bear.class, PLANTS, 0);

        put(Eagle.class, Wolf.class, 0);
        put(Eagle.class, Boa.class, 0);
        put(Eagle.class, Fox.class, 10);
        put(Eagle.class, Bear.class, 0);
        put(Eagle.class, Horse.class, 0);
        put(Eagle.class, Deer.class, 0);
        put(Eagle.class, Rabbit.class, 90);
        put(Eagle.class, Mouse.class, 90);
        put(Eagle.class, Goat.class, 0);
        put(Eagle.class, Sheep.class, 0);
        put(Eagle.class, Boar.class, 0);
        put(Eagle.class, Buffalo.class, 0);
        put(Eagle.class, Duck.class, 80);
        put(Eagle.class, Caterpillar.class, 0);
        put(Eagle.class, PLANTS, 0);

        put(Horse.class, PLANTS, 100);
        put(Deer.class, PLANTS, 100);
        put(Rabbit.class, PLANTS, 100);
        put(Mouse.class, PLANTS, 100);
        put(Goat.class, PLANTS, 100);
        put(Sheep.class, PLANTS, 100);
        put(Boar.class, PLANTS, 100);
        put(Buffalo.class, PLANTS, 100);
        put(Caterpillar.class, PLANTS, 100);

        put(Duck.class, Caterpillar.class, 90);
        put(Duck.class, PLANTS, 100);

        put(Mouse.class, Caterpillar.class, 90);

        put(Boar.class, Mouse.class, 50);
        put(Boar.class, Caterpillar.class, 90);
    }

    private static void put(Class<?> eater, Class<?> food, int chance) {
        Map<Class<?>, Integer> row = table.get(eater);
        if (row == null) {
            row = new HashMap<>();
            table.put(eater, row);
        }
        row.put(food, chance);
    }

    public static int getChance(Class<?> eater, Class<?> food) {
        Map<Class<?>, Integer> row = table.get(eater);
        if (row == null) return 0;
        Integer chance = row.get(food);
        if (chance == null) return 0;
        return chance;
    }

    // подсказка гпт что бы не создавать класс Plant, ключ для таблицы вероятностей
    private static class PlantsKey {
    }
}

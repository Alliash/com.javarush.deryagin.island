package island.model.animals;

import island.model.Island;
import island.model.Location;
import island.util.FoodChance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {

    private final String name;
    private final String icon;

    private final double weight;
    private final int maxOnCell;
    private final int speed;
    private final double foodNeed;

    private double satiety; // текущая сытость (0..foodNeed)

    protected Animal(String name, String icon, double weight, int maxOnCell, int speed, double foodNeed) {
        this.name = name;
        this.icon = icon;
        this.weight = weight;
        this.maxOnCell = maxOnCell;
        this.speed = speed;
        this.foodNeed = foodNeed;
        this.satiety = foodNeed * 0.6; // стартуем "слегка сытыми"
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxOnCell() {
        return maxOnCell;
    }

    public int getSpeed() {
        return speed;
    }

    public double getFoodNeed() {
        return foodNeed;
    }

    public double getSatiety() {
        return satiety;
    }

    public boolean isDead() {
        if (foodNeed <= 0) return false;

        return satiety <= 0.0;
    }

    public MoveDecision act(Location location, int x, int y, Island island) {

        // сытость уменьшается только у тех, кому реально нужна еда
        if (foodNeed > 0) {
            satiety -= foodNeed * 0.15;
        }

        eat(location);
        reproduce(location);

        return chooseMove(x, y, island);
    }
    public void eat(Location location) {
        if (isDead()) return;


        int plantChance = FoodChance.getChance(this.getClass(), FoodChance.PLANTS);
        if (plantChance > 0 && location.getPlants() > 0) {
            int roll = ThreadLocalRandom.current().nextInt(100);
            if (roll < plantChance) {
                double need = foodNeed - satiety;
                if (need < 0) need = 0;

                int canEatKg = (int) Math.ceil(need);
                int eaten = Math.min(canEatKg, location.getPlants());
                location.addPlants(-eaten);

                satiety += eaten;
                if (satiety > foodNeed) satiety = foodNeed;
                return;
            }
        }

        List<Animal> animals = location.getAnimals();
        if (animals.size() <= 1) return;

        List<Animal> copy = new ArrayList<>(animals);
        for (Animal victim : copy) {
            if (victim == this) continue;
            if (victim.isDead()) continue;

            int chance = FoodChance.getChance(this.getClass(), victim.getClass());
            if (chance <= 0) continue;

            int roll = ThreadLocalRandom.current().nextInt(100);
            if (roll < chance) {
                location.removeAnimal(victim);

                satiety += victim.getWeight();
                if (satiety > foodNeed) satiety = foodNeed;
                break;
            }
        }
    }

    public void reproduce(Location location) {
        if (isDead()) return;

        int same = 0;
        for (Animal a : location.getAnimals()) {
            if (a.getClass() == this.getClass() && !a.isDead()) {
                same++;
            }
        }

        if (same < 2) return;
        if (same >= maxOnCell) return;

        int roll = ThreadLocalRandom.current().nextInt(100);
        if (roll < 20) {
            Animal baby = createChild();

            int nowSame = 0;
            for (Animal a : location.getAnimals()) {
                if (a.getClass() == this.getClass() && !a.isDead()) {
                    nowSame++;
                }
            }
            if (nowSame < maxOnCell) {
                location.addAnimal(baby);
            }
        }
    }

    protected abstract Animal createChild();

    public MoveDecision chooseMove(int x, int y, Island island) {
        if (isDead()) return MoveDecision.noMove();
        if (speed <= 0) return MoveDecision.noMove();

        int roll = ThreadLocalRandom.current().nextInt(100);
        if (roll >= 50) return MoveDecision.noMove();

        int dx = ThreadLocalRandom.current().nextInt(-speed, speed + 1);
        int dy = ThreadLocalRandom.current().nextInt(-speed, speed + 1);

        if (dx == 0 && dy == 0) return MoveDecision.noMove();

        int nx = x + dx;
        int ny = y + dy;

        if (nx < 0) nx = 0;
        if (ny < 0) ny = 0;
        if (nx >= island.getWidth()) nx = island.getWidth() - 1;
        if (ny >= island.getHeight()) ny = island.getHeight() - 1;

        if (nx == x && ny == y) return MoveDecision.noMove();

        return new MoveDecision(this, x, y, nx, ny);
    }
}

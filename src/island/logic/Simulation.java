package island.logic;

import island.model.Island;
import island.model.Location;
import island.model.animals.Animal;
import island.model.animals.MoveDecision;
import island.settings.Config;
import island.util.AnimalFactory;
import island.util.StatsPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Simulation {

    private final Island island;
    private final ScheduledExecutorService scheduler;
    private final ExecutorService locationPool;
    private volatile int tick;

    public Simulation() {
        this.island = new Island();
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.locationPool = Executors.newFixedThreadPool(Config.LOCATION_THREADS);
        this.tick = 0;

        initIsland();
    }

    private void initIsland() {

        for (int y = 0; y < island.getHeight(); y++) {

            for (int x = 0; x < island.getWidth(); x++) {
                Location loc = island.getLocation(x, y);
                loc.setPlants(Config.START_PLANTS_PER_CELL);

                for (int i = 0; i < Config.START_ANIMALS_PER_CELL; i++) {
                    Animal a = AnimalFactory.randomAnimal();

                    // проверяем лимит вида на клетке
                    int same = 0;

                    for (Animal other : loc.getAnimals()) {
                        if (other.getClass() == a.getClass()) {
                            same++;
                        }
                    }

                    if (same < a.getMaxOnCell()) {
                        loc.addAnimal(a);
                    }
                }
            }
        }
    }

    public void start() {
        scheduler.scheduleAtFixedRate(this::growPlants, 0, Config.TICK_MS, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(this::animalsTick, 0, Config.TICK_MS, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(this::printStats, 0, Config.TICK_MS, TimeUnit.MILLISECONDS);
    }

    private void growPlants() {
        for (int y = 0; y < island.getHeight(); y++) {

            for (int x = 0; x < island.getWidth(); x++) {
                Location loc = island.getLocation(x, y);

                synchronized (loc) {
                    loc.addPlants(Config.PLANT_GROW_PER_TICK);

                    if (loc.getPlants() > Config.MAX_PLANTS_ON_CELL) {
                        loc.setPlants(Config.MAX_PLANTS_ON_CELL);
                    }
                }
            }
        }
    }

    private void animalsTick() {
        int currentTick = ++tick;

        List<MoveDecision> moves = new CopyOnWriteArrayList<>();

        List<Future<?>> futures = new ArrayList<>();

        for (int y = 0; y < island.getHeight(); y++) {

            for (int x = 0; x < island.getWidth(); x++) {
                final int fx = x;
                final int fy = y;

                Future<?> f = locationPool.submit(() -> processLocation(fx, fy, moves));
                futures.add(f);
            }
        }

        for (Future<?> f : futures) {

            try {
                f.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        applyMoves(moves);
        cleanupDead();


   //     if (currentTick >= Config.MAX_TICKS) {
   //         stop();
  //          return;
  //      }

        if (Config.STOP_IF_NO_ANIMALS) {
            int totalAnimals = countAllAnimals();

            if (totalAnimals == 0) {
                System.out.println("Все животные умерли. Остановка симуляции.");
                stop();
            }
        }
    }

    private void processLocation(int x, int y, List<MoveDecision> moves) {
        Location loc = island.getLocation(x, y);

        synchronized (loc) {
            List<Animal> animalsCopy = new ArrayList<>(loc.getAnimals());

            for (Animal a : animalsCopy) {
                if (a.isDead()) continue;

                MoveDecision decision = a.act(loc, x, y, island);
                if (decision.isMove()) {
                    moves.add(decision);
                }
            }
        }
    }

    private void applyMoves(List<MoveDecision> moves) {
        for (MoveDecision m : moves) {
            Animal a = m.getAnimal();

            Location from = island.getLocation(m.getFromX(), m.getFromY());
            Location to = island.getLocation(m.getToX(), m.getToY());

            Object first = from;
            Object second = to;

            if (System.identityHashCode(first) > System.identityHashCode(second)) {
                first = to;
                second = from;
            }

            synchronized (first) {

                synchronized (second) {

                    if (!from.getAnimals().contains(a)) continue;

                    int same = 0;

                    for (Animal other : to.getAnimals()) {
                        if (other.getClass() == a.getClass() && !other.isDead()) {
                            same++;
                        }
                    }
                    if (same >= a.getMaxOnCell()) continue;

                    from.removeAnimal(a);
                    to.addAnimal(a);
                }
            }
        }
    }

    private void cleanupDead() {

        for (int y = 0; y < island.getHeight(); y++) {

            for (int x = 0; x < island.getWidth(); x++) {
                Location loc = island.getLocation(x, y);
                synchronized (loc) {
                    loc.getAnimals().removeIf(Animal::isDead);
                }
            }
        }
    }

    private int countAllAnimals() {

        int total = 0;

        for (int y = 0; y < island.getHeight(); y++) {
            for (int x = 0; x < island.getWidth(); x++) {
                Location loc = island.getLocation(x, y);

                synchronized (loc) {
                    total += loc.getAnimals().size();
                }
            }
        }
        return total;
    }

    private void printStats() {

        if (tick % Config.PRINT_EVERY_TICKS == 0) {
            StatsPrinter.print(island, tick);
        }
    }

    private void stop() {
        System.out.println("Симуляция остановлена на такте: " + tick);
        scheduler.shutdownNow();
        locationPool.shutdownNow();
    }
}

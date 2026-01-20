package island.util;

import island.model.animals.*;

import java.util.concurrent.ThreadLocalRandom;

public class AnimalFactory {

    public static Animal randomAnimal() {
        int r = ThreadLocalRandom.current().nextInt(15);
        switch (r) {
            case 0: return new Wolf();
            case 1: return new Boa();
            case 2: return new Fox();
            case 3: return new Bear();
            case 4: return new Eagle();
            case 5: return new Horse();
            case 6: return new Deer();
            case 7: return new Rabbit();
            case 8: return new Mouse();
            case 9: return new Goat();
            case 10: return new Sheep();
            case 11: return new Boar();
            case 12: return new Buffalo();
            case 13: return new Duck();
            default: return new Caterpillar();
        }
    }

    private AnimalFactory() {
    }
}


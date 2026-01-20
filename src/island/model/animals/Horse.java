package island.model.animals;

public class Horse extends Herbivore {
    public Horse() {
        super("Лошадь", "🐎", 400, 20, 4, 60);
    }

    @Override
    protected Animal createChild() {
        return new Horse();
    }
}

package island.model.animals;

public class Duck extends Herbivore {
    public Duck() {
        super("Утка", "🦆", 1, 200, 4, 0.15);
    }

    @Override
    protected Animal createChild() {
        return new Duck();
    }
}
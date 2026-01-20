package island.model.animals;

public class Goat extends Herbivore {
    public Goat() {
        super("Коза", "🐐", 60, 140, 3, 10);
    }

    @Override
    protected Animal createChild() {
        return new Goat();
    }
}

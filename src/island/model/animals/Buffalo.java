package island.model.animals;

public class Buffalo extends Herbivore {
    public Buffalo() {
        super("Буйвол", "🐃", 700, 10, 3, 100);
    }

    @Override
    protected Animal createChild() {
        return new Buffalo();
    }
}
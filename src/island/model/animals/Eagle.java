package island.model.animals;


public class Eagle extends Predator {
    public Eagle() {
        super("Орел", "🦅", 6, 20, 3, 1);
    }

    @Override
    protected Animal createChild() {
        return new Eagle();
    }
}

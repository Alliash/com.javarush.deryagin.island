package island.model.animals;

public class Bear extends Predator {
    public Bear() {
        super("Медведь", "🐻", 500, 5, 2, 80);
    }

    @Override
    protected Animal createChild() {
        return new Bear();
    }
}

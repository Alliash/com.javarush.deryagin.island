package island.model.animals;

public class Fox extends Predator {
    public Fox() {
        super("Лиса", "🦊", 8, 30, 2, 2);
    }

    @Override
    protected Animal createChild() {
        return new Fox();
    }
}

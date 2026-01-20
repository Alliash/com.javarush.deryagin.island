package island.model.animals;

public class Mouse extends Herbivore {
    public Mouse() {
        super("Мышь", "🐁", 0.05, 500, 1, 0.01);
    }

    @Override
    protected Animal createChild() {
        return new Mouse();
    }
}
package island.model.animals;

public class Caterpillar extends Herbivore {
    public Caterpillar() {
        super("Гусеница", "🐛", 0.01, 1000, 0, 0);
    }

    @Override
    protected Animal createChild() {
        return new Caterpillar();
    }
}
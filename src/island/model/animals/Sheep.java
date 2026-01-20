package island.model.animals;

public class Sheep extends Herbivore {
    public Sheep() {
        super("Овца", "🐑", 70, 140, 3, 15);
    }

    @Override
    protected Animal createChild() {
        return new Sheep();
    }
}

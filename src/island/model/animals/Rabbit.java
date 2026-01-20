package island.model.animals;

public class Rabbit extends Herbivore {
    public Rabbit() {
        super("Кролик", "🐇", 2, 150, 2, 0.45);
    }

    @Override
    protected Animal createChild() {
        return new Rabbit();
    }
}

package island.model.animals;

public class Wolf extends Predator {
    public Wolf() {
        super("Волк", "🐺", 50, 30, 3, 8);
    }

    @Override
    protected Animal createChild() {
        return new Wolf();
    }
}



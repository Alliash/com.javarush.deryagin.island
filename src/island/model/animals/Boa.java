package island.model.animals;

public class Boa extends Predator {
    public Boa() {
        super("Удав", "🐍", 15, 30, 1, 3);
    }

    @Override
    protected Animal createChild() {
        return new Boa();
    }
}



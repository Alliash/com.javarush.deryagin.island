package island.model.animals;

public class Deer extends Herbivore {
    public Deer() {
        super("Олень", "🦌", 300, 20, 4, 50);
    }

    @Override
    protected Animal createChild() {
        return new Deer();
    }
}

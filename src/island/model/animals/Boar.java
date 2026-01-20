package island.model.animals;

public class Boar extends Herbivore {
    public Boar() {
        super("Кабан", "🐗", 400, 50, 2, 50);
    }

    @Override
    protected Animal createChild() {
        return new Boar();
    }
}
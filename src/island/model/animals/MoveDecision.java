package island.model.animals;

public class MoveDecision {

    private final Animal animal;
    private final int fromX;
    private final int fromY;
    private final int toX;
    private final int toY;
    private final boolean move;

    public MoveDecision(Animal animal, int fromX, int fromY, int toX, int toY) {
        this.animal = animal;
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.move = true;
    }

    private MoveDecision() {
        this.animal = null;
        this.fromX = 0;
        this.fromY = 0;
        this.toX = 0;
        this.toY = 0;
        this.move = false;
    }

    public static MoveDecision noMove() {
        return new MoveDecision();
    }

    public boolean isMove() {
        return move;
    }

    public Animal getAnimal() {
        return animal;
    }

    public int getFromX() {
        return fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public int getToX() {
        return toX;
    }

    public int getToY() {
        return toY;
    }
}


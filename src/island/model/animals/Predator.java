package island.model.animals;

public abstract class Predator extends Animal {
    protected Predator(String name, String icon, double weight, int maxOnCell, int speed, double foodNeed) {
        super(name, icon, weight, maxOnCell, speed, foodNeed);
    }
}



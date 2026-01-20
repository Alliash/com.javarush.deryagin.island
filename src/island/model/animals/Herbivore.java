package island.model.animals;



public abstract class Herbivore extends Animal {
    protected Herbivore(String name, String icon, double weight, int maxOnCell, int speed, double foodNeed) {
        super(name, icon, weight, maxOnCell, speed, foodNeed);
    }
}

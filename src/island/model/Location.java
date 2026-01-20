package island.model;
import island.model.animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class Location {

    private int plants;
    private final List<Animal> animals = new ArrayList<>();

    public int getPlants() {
        return plants;
    }

    public void setPlants(int plants) {
        this.plants = plants;
    }

    public void addPlants(int add) {
        this.plants += add;
        if (this.plants < 0) this.plants = 0;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

}

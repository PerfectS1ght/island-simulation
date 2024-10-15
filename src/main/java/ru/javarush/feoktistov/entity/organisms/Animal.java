package ru.javarush.feoktistov.entity.organisms;

import ru.javarush.feoktistov.entity.Location;
import ru.javarush.feoktistov.repository.AnimalFactory;
import ru.javarush.feoktistov.util.IslandStatistics;
import ru.javarush.feoktistov.util.OrganismType;
import ru.javarush.feoktistov.util.PropertiesReader;
import ru.javarush.feoktistov.util.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Animal extends Organism {

    protected int speed;
    protected double requiredFoodWeight;
    protected int maxTriesToMultiply;

    protected Animal(OrganismType type) {
        super(type);
        this.speed = PropertiesReader.getSpeedOfOrganism(type);
        this.requiredFoodWeight = PropertiesReader.getRequiredFoodWeightOfOrganism(type);
        this.maxTriesToMultiply = PropertiesReader.getMaxTriesOfMultiply();
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getRequiredFoodWeight() {
        return requiredFoodWeight;
    }

    public void setRequiredFoodWeight(double requiredFoodWeight) {
        this.requiredFoodWeight = requiredFoodWeight;
    }

    public int getMaxTriesToMultiply() {
        return maxTriesToMultiply;
    }

    public void setMaxTriesToMultiply(int maxTriesToMultiply) {
        this.maxTriesToMultiply = maxTriesToMultiply;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Animal animal = (Animal) o;
        return speed == animal.speed && Double.compare(requiredFoodWeight, animal.requiredFoodWeight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), speed, requiredFoodWeight);
    }

    @Override
    public void multiply(Location location) {
        int currentCountThisTypeInLocation = IslandStatistics.quantityOfOrganismTypeOnLocation(this.getType(), location);
        if(currentCountThisTypeInLocation == this.getMaxCount()) {
            return;
        }
        List<Organism> organisms = location.getPopulation().get(this.getType());
        List<Animal> bornAnimals = new ArrayList<>();
        int readyToAdd = this.getMaxCount() - currentCountThisTypeInLocation;
        int counterOfBorn = 0;
        int counterOfTries = 0;
        for(Organism organism: organisms) {
            if(this.getId().equals(organism.getId())){
                continue;
            }
            if(counterOfBorn == readyToAdd || counterOfTries == this.getMaxTriesToMultiply()) {
                    break;
            }
            if(Randomizer.canDoIt(this.getMultiplyChance())) {
                    bornAnimals.add(AnimalFactory.createAnimal(this.type));
                    counterOfBorn++;
            }
            counterOfTries++;
        }
        organisms.addAll(bornAnimals);
        bornAnimals.clear();
    }

    public void eat() {

    }


//    protected abstract void move();
}

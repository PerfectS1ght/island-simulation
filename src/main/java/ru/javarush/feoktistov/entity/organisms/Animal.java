package ru.javarush.feoktistov.entity.organisms;

import ru.javarush.feoktistov.util.OrganismType;
import ru.javarush.feoktistov.util.PropertiesReader;

import java.util.Objects;

public abstract class Animal extends Organism {

    protected int speed;
    protected double requiredFoodWeight;

    protected Animal(OrganismType type) {
        super(type);
        this.speed = PropertiesReader.getSpeedOfOrganism(type);
        this.requiredFoodWeight = PropertiesReader.getRequiredFoodWeightOfOrganism(type);
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

//    protected abstract void eat();
//    protected abstract void move();
}

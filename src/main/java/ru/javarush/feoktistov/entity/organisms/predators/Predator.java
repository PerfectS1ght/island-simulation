package ru.javarush.feoktistov.entity.organisms.predators;

import ru.javarush.feoktistov.entity.organisms.Animal;
import ru.javarush.feoktistov.util.OrganismType;

public abstract class Predator extends Animal {

    public Predator(OrganismType type) {
        super(type);
    }
}

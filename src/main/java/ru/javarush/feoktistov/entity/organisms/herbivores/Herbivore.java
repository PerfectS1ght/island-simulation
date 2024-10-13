package ru.javarush.feoktistov.entity.organisms.herbivores;

import ru.javarush.feoktistov.entity.organisms.Animal;
import ru.javarush.feoktistov.util.OrganismType;

public class Herbivore extends Animal {
    protected Herbivore(OrganismType type) {
        super(type);
    }
}

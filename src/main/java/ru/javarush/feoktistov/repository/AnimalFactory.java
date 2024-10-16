package ru.javarush.feoktistov.repository;

import ru.javarush.feoktistov.entity.organisms.Animal;
import ru.javarush.feoktistov.entity.organisms.herbivores.Caterpillar;
import ru.javarush.feoktistov.entity.organisms.herbivores.Rabbit;
import ru.javarush.feoktistov.entity.organisms.predators.Wolf;
import ru.javarush.feoktistov.util.OrganismType;

public class AnimalFactory {
    public static Animal createAnimal(OrganismType type) {
        switch (type) {
            case WOLF:
                return new Wolf();
            case RABBIT:
                return new Rabbit();
            case CATERPILLAR:
                return new Caterpillar();
            default:
                throw new IllegalArgumentException("Wrong animal type: " + type);
        }
    }
}

package ru.javarush.feoktistov.repository;

import ru.javarush.feoktistov.entity.organisms.Organism;
import ru.javarush.feoktistov.entity.organisms.herbivores.Caterpillar;
import ru.javarush.feoktistov.entity.organisms.herbivores.Rabbit;
import ru.javarush.feoktistov.entity.organisms.plants.Plant;
import ru.javarush.feoktistov.entity.organisms.predators.Wolf;
import ru.javarush.feoktistov.util.OrganismType;

public class OrganismFactory {

    public static Organism createOrganism(OrganismType type) {

        switch (type) {
            case WOLF:
                return new Wolf();
            case RABBIT:
                return new Rabbit();
            case CATERPILLAR:
                return new Caterpillar();
            case PLANT:
                return new Plant();
            default:
                throw new IllegalArgumentException("Wrong organism type: " + type);
        }
    }
}

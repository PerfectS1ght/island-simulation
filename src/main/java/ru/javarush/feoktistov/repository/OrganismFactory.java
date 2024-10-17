package ru.javarush.feoktistov.repository;

import ru.javarush.feoktistov.entity.organisms.Organism;
import ru.javarush.feoktistov.entity.organisms.herbivores.*;
import ru.javarush.feoktistov.entity.organisms.plants.Plant;
import ru.javarush.feoktistov.entity.organisms.predators.*;
import ru.javarush.feoktistov.util.OrganismType;

public class OrganismFactory {

    public static Organism createOrganism(OrganismType type) {

        switch (type) {
            case WOLF:
                return new Wolf();
            case BOA:
                return new Boa();
            case FOX:
                return new Fox();
            case BEAR:
                return new Bear();
            case EAGLE:
                return new Eagle();
            case HORSE:
                return new Horse();
            case DEER:
                return new Deer();
            case RABBIT:
                return new Rabbit();
            case MOUSE:
                return new Mouse();
            case GOAT:
                return new Goat();
            case SHEEP:
                return new Sheep();
            case WILD_BOAR:
                return new WildBoar();
            case BUFFALO:
                return new Buffalo();
            case DUCK:
                return new Duck();
            case CATERPILLAR:
                return new Caterpillar();
            case PLANT:
                return new Plant();
            default:
                throw new IllegalArgumentException("Wrong organism type: " + type);
        }
    }
}

package ru.javarush.feoktistov.repository;

import ru.javarush.feoktistov.entity.organisms.plants.Plant;
import ru.javarush.feoktistov.util.OrganismType;

public class PlantFactory {

    public static Plant createPlant(OrganismType type) {
        switch (type) {
            case PLANT:
                return new Plant();
            default:
                throw new IllegalArgumentException("Wrong plant type: " + type);
        }
    }
}

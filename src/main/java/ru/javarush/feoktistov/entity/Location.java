package ru.javarush.feoktistov.entity;

import ru.javarush.feoktistov.entity.organisms.Animal;
import ru.javarush.feoktistov.entity.organisms.plants.Plant;
import ru.javarush.feoktistov.repository.AnimalFactory;
import ru.javarush.feoktistov.repository.PlantFactory;
import ru.javarush.feoktistov.util.OrganismType;
import ru.javarush.feoktistov.util.PropertiesReader;
import ru.javarush.feoktistov.util.Randomizer;

import java.util.ArrayList;
import java.util.List;

public class Location {

    private final List<Animal> animals;
    private final List<Plant> plants;

    public Location() {
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
    }

    public void initializeLocation() {
        for(OrganismType type: OrganismType.values()) {
            int maxCountOfType = PropertiesReader.getMaxCountOfOrganism(type);
            int countToAdd = Randomizer.getRandomInt(maxCountOfType);
            if(type == OrganismType.PLANT) {
                for(int i = 0; i < countToAdd; i++) {
                    plants.add(PlantFactory.createPlant(type));
                }
            }else{
                for(int i = 0; i < countToAdd; i++) {
                    animals.add(AnimalFactory.createAnimal(type));
                }
            }
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Plant> getPlants() {
        return plants;
    }
}

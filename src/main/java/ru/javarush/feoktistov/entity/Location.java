package ru.javarush.feoktistov.entity;

import ru.javarush.feoktistov.entity.organisms.Animal;
import ru.javarush.feoktistov.entity.organisms.Organism;
import ru.javarush.feoktistov.repository.AnimalFactory;
import ru.javarush.feoktistov.repository.PlantFactory;
import ru.javarush.feoktistov.util.IslandStatistics;
import ru.javarush.feoktistov.util.OrganismType;
import ru.javarush.feoktistov.util.PropertiesReader;
import ru.javarush.feoktistov.util.Randomizer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Location {

    private final int positionY;
    private final int positionX;
    private final Map<OrganismType, List<Organism>> population;

    public Location(int i, int j) {
        this.positionY = i;
        this.positionX = j;
        this.population = new ConcurrentHashMap<>();
    }

    public int getPositionY() {
        return positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public Map<OrganismType, List<Organism>> getPopulation() {
        return population;
    }

    public void initializeLocation() {
        for(OrganismType type: OrganismType.values()) {
            int randomCount = Randomizer.getRandomInt(PropertiesReader.getMaxCountOfOrganism(type));
            List<Organism> organisms = new ArrayList<>(randomCount);
            if(type == OrganismType.PLANT) {
                for(int i = 0; i < randomCount; i++) {
                    organisms.add(PlantFactory.createPlant(type));
                }
            }else{
                for(int i = 0; i < randomCount; i++) {
                    organisms.add(AnimalFactory.createAnimal(type));
                }
            }
            population.put(type, organisms);
        }
    }

    public synchronized void growPlants() {
        List<Organism> plants = population.get(OrganismType.PLANT);
        for(int i = 0; i < plants.size(); i++) {
            plants.get(i).multiply(this);
        }
    }

    public synchronized void multiplyAnimals() {
        for(OrganismType type: population.keySet()) {
            if(type != OrganismType.PLANT) {
                List<Organism> organisms = population.get(type);
                for(int i = 0; i < organisms.size(); i++) {
                    Animal animal = (Animal) organisms.get(i);
                    animal.multiply(this);
                }

            }
        }
    }

    public synchronized void eatAnimals() {
        for(OrganismType type: population.keySet()) {
            if(type != OrganismType.PLANT) {
                List<Organism> organisms = population.get(type);
                for(int i = 0; i < organisms.size(); i++) {
                    Animal animal = (Animal) organisms.get(i);
                    animal.eat(this);
                }
            }
        }
    }

    public synchronized void moveAnimals() {
        for(OrganismType type: population.keySet()) {
            if(type != OrganismType.PLANT) {
                List<Organism> organisms = population.get(type);
                for(int i = 0; i < organisms.size(); i++) {
                    Animal animal = (Animal) organisms.get(i);
                    animal.move(this);
                }
            }
        }
    }

    public synchronized void animalsFeelsHungry() {
        for(OrganismType type: population.keySet()) {
            if(type != OrganismType.PLANT) {
                double weightLosesPerDay = (PropertiesReader.getWeightOfOrganism(type) * PropertiesReader.getPercentOfWeightLosesPerDay())/100;
                List<Organism> organisms = population.get(type);
                for(int i = 0; i < organisms.size(); i++) {
                    Animal animal = (Animal) organisms.get(i);
                    animal.decreaseWeight(weightLosesPerDay);
                    if(animal.getWeight() <= 0 || animal.getWeight() > PropertiesReader.getWeightOfOrganism(type) * 2) {
                        animal.die(this);
                    }
                }
            }
        }
    }

    public boolean isPlantCapacityReached() {
        int quantityOfPlants = IslandStatistics.quantityOfOrganismTypeOnLocation(OrganismType.PLANT, this);
        int maxCountOfPlants = PropertiesReader.getMaxCountOfOrganism(OrganismType.PLANT);
        return quantityOfPlants >= maxCountOfPlants;
    }
}

package ru.javarush.feoktistov.util;

import ru.javarush.feoktistov.entity.Island;
import ru.javarush.feoktistov.entity.Location;
import ru.javarush.feoktistov.entity.organisms.Animal;
import ru.javarush.feoktistov.entity.organisms.plants.Plant;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IslandStatistics {

    private static final Island island = Island.getInstance();

    private static final Map<OrganismType, Integer> countOfOrganisms = new ConcurrentHashMap<>();
    static {
        for(OrganismType type: OrganismType.values()) {
            countOfOrganisms.put(type, 0);
        }
    }

    private IslandStatistics() {
        throw new IllegalStateException("Utility class");
    }

    public static void showCurrentStatistics() {
        Location[][] locations = island.getLocations();
        collectStatistics(locations);
        printStatistics();
        resetStatistics();
    }

    public static int quantityOfOrganismTypeOnLocation(OrganismType type, Location location){
        int counter = 0;
        if(type == OrganismType.PLANT) {
            return location.getPlants().size();
        }else{
            List<Animal> animals = location.getAnimals();
            for(Animal animal: animals) {
                if(animal.getType() == type) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public static void printStatisticsLocation(Location location) {
        System.out.print("Количество травы и животных на локации: | ");
        for(OrganismType type: OrganismType.values()) {
            String emoji = PropertiesReader.getEmojiOfOrganism(type);
            int quantity = quantityOfOrganismTypeOnLocation(type, location);
            System.out.print(emoji + " - " + quantity + " | ");
        }
        System.out.println();
    }

    private static void collectStatistics(Location[][] locations) {
        for(Location[] locationArray: locations) {
            for(Location location: locationArray) {
                List<Animal> animals = location.getAnimals();
                for(Animal animal: animals) {
                    OrganismType type = animal.getType();
                    countOfOrganisms.put(type, countOfOrganisms.get(type) + 1);
                }
                List<Plant> plants = location.getPlants();
                for(Plant plant: plants) {
                    OrganismType type = plant.getType();
                    countOfOrganisms.put(type, countOfOrganisms.get(type) + 1);
                }
            }
        }
    }

    private static void printStatistics() {
        System.out.print("Количество травы и животных на острове: | ");
        for(Map.Entry<OrganismType, Integer> pair: countOfOrganisms.entrySet()) {
            OrganismType key = pair.getKey();
            Integer value = pair.getValue();
            String emojiOfOrganism = PropertiesReader.getEmojiOfOrganism(key);
            System.out.print(emojiOfOrganism + " - " + value + " | ");
        }
        System.out.println();
    }

    private static void resetStatistics() {
        countOfOrganisms.replaceAll((key, value) -> 0);
    }
}

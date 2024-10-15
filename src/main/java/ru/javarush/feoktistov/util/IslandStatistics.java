package ru.javarush.feoktistov.util;

import ru.javarush.feoktistov.entity.Island;
import ru.javarush.feoktistov.entity.Location;
import ru.javarush.feoktistov.entity.organisms.Organism;

import java.util.List;
import java.util.Map;

public class IslandStatistics {

    private IslandStatistics() {
        throw new IllegalStateException("Utility class");
    }

    public static int quantityOfOrganismTypeOnLocation(OrganismType type, Location location){
        if(location == null) {
            return 0;
        }
        Map<OrganismType, List<Organism>> population = location.getPopulation();
        return population.containsKey(type) ? population.get(type).size() : 0;
    }

    public static int quantityOfOrganismTypeOnIsland(OrganismType type) {
        Location[][] locations = Island.getInstance().getLocations();
        int result = 0;
        for(Location[] locationsArray: locations) {
            for(Location location: locationsArray) {
                result += quantityOfOrganismTypeOnLocation(type, location);
            }
        }
        return result;
    }
}

package ru.javarush.feoktistov.entity;

import ru.javarush.feoktistov.util.PropertiesReader;

public class Island {

    private static final Island instance = new Island();
    private final Location[][] locations;

    private Island() {
        int length = PropertiesReader.getLengthOfIsland();
        int width = PropertiesReader.getWidthOfIsland();
        locations = new Location[width][length];
    }

    public static Island getInstance() {
        return instance;
    }

    public Location[][] getLocations() {
        return locations;
    }

    public void fillOutIslandByLocations() {
        for(int i = 0; i < locations.length; i++) {
            for(int j = 0; j < locations[i].length; j++) {
                locations[i][j] = new Location();
                locations[i][j].initializeLocation();
            }
        }
    }

    public void growPlantsOnLocs() {
        for(int i = 0; i < locations.length; i++) {
            for(int j = 0; j < locations[i].length; j++) {
                locations[i][j].growPlants();
            }
        }
    }
}

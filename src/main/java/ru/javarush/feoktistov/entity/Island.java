package ru.javarush.feoktistov.entity;

import ru.javarush.feoktistov.util.PrinterStats;
import ru.javarush.feoktistov.util.PropertiesReader;

public class Island {

    private static final Island instance = new Island();
    private final int LENGTH;
    private final int WIDTH;
    private final Location[][] locations;

    private Island() {
        this.LENGTH = PropertiesReader.getLengthOfIsland();
        this.WIDTH = PropertiesReader.getWidthOfIsland();
        locations = new Location[WIDTH][LENGTH];
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
                locations[i][j] = new Location(i, j);
                locations[i][j].initializeLocation();
            }
        }
    }

    public void startLifeInLocs() {
        for(int i = 0; i < locations.length; i++) {
            for(int j = 0; j < locations[i].length; j++) {
                locations[i][j].animalsFeelsHungry();
                locations[i][j].growPlants();
                locations[i][j].eatAnimals();
                locations[i][j].moveAnimals();


            }
        }
    }

    public Location getLocationByCoordinates(int i, int j) {
        if(i < 0 || i >= WIDTH || j < 0 || j >= LENGTH) {
            return null;
        }
        return locations[i][j];
    }

    public boolean isLocationExistsByCoordinates(int i, int j) {
        return i >= 0 && i < WIDTH && j >= 0 && j < LENGTH;
    }

}

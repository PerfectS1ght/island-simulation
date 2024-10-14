package ru.javarush.feoktistov;

import ru.javarush.feoktistov.entity.Location;
import ru.javarush.feoktistov.util.IslandStatistics;

public class Main {


    public static void main(String[] args) {
        Location location = new Location();
        location.initializeLocation();
        IslandStatistics.printStatisticsLocation(location);
        location.multiplyAnimals();
        IslandStatistics.printStatisticsLocation(location);
    }
}
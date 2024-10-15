package ru.javarush.feoktistov.app;

import ru.javarush.feoktistov.config.GameStarter;
import ru.javarush.feoktistov.entity.Location;
import ru.javarush.feoktistov.util.PrinterStats;

public class Main {
    public static void main(String[] args) {
        Location location = new Location(0,0);
        PrinterStats.printStatisticsLocation(location);
        location.initializeLocation();
        PrinterStats.printStatisticsLocation(location);
        location.growPlants();
        PrinterStats.printStatisticsLocation(location);
        location.multiplyAnimals();
        PrinterStats.printStatisticsLocation(location);
    }
}
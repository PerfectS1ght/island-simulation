package ru.javarush.feoktistov.service;

import ru.javarush.feoktistov.entity.Island;
import ru.javarush.feoktistov.entity.Location;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationStarter {

    private final ScheduledExecutorService lifeCircleService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    private final ScheduledExecutorService plantService = Executors.newScheduledThreadPool(1);
    private final ScheduledExecutorService printService = Executors.newScheduledThreadPool(1);

    public void start() {
        Island island = Island.getInstance();
        Location[][] locations = island.getLocations();
        island.fillOutIslandByLocations();
        startLifeCircle(locations);
        startPrintStats();
    }

    public void startPlantSimulation(Location[][] locations) {
        for(int i = 0; i < locations.length; i++) {
            for(int j = 0; j < locations[i].length; j++) {
                plantService.scheduleWithFixedDelay(new PlantGrowTask(locations[i][j]), 0, 10, TimeUnit.SECONDS);
            }
        }
    }

    public void startPrintStats() {
        printService.scheduleWithFixedDelay(new PrintTask(), 0, 2, TimeUnit.SECONDS);
    }

    public void startLifeCircle(Location[][] locations) {
        for(int i = 0; i < locations.length; i++) {
            for(int j = 0; j < locations[i].length; j++) {
                lifeCircleService.scheduleWithFixedDelay(new LifeCircleTask(locations[i][j]), 0, 1, TimeUnit.MILLISECONDS);
            }
        }
    }
}

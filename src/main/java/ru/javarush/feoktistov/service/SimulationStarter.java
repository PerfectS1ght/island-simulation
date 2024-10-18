package ru.javarush.feoktistov.service;

import ru.javarush.feoktistov.entity.Island;
import ru.javarush.feoktistov.entity.Location;
import ru.javarush.feoktistov.service.tasks.LifeCircleTask;
import ru.javarush.feoktistov.service.tasks.PlantGrowTask;
import ru.javarush.feoktistov.service.tasks.PrintTask;
import ru.javarush.feoktistov.util.IslandStatistics;
import ru.javarush.feoktistov.util.PropertiesReader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SimulationStarter {

    private final int countOfSimulationSteps = PropertiesReader.getCountOfStepsSimulation();
    private final int durationOfStep = PropertiesReader.getDurationOfStep();
    private final AtomicInteger simulationStep = new AtomicInteger(0);
    private final ExecutorService lifeCircleService = Executors.newFixedThreadPool(1);
    private final ScheduledExecutorService plantService = Executors.newScheduledThreadPool(1);
    private final ScheduledExecutorService printService = Executors.newScheduledThreadPool(1);

    public void start() {
        Island island = Island.getInstance();
        Location[][] locations = island.getLocations();
        startPlantSimulation(locations);
        startPrintStats();
        while(countOfSimulationSteps > simulationStep.get()) {
            simulationStep.incrementAndGet();
            if(!IslandStatistics.isAnyoneAliveOnIsland()) {
                printService.shutdown();
                plantService.shutdown();
                lifeCircleService.shutdown();
                System.out.println("Все животные на острове погибли.");
                return;
            }
            List<Callable<Object>> tasks = new ArrayList<>();
            for (int i = 0; i < locations.length; i++) {
                for (int j = 0; j < locations[i].length; j++) {
                    tasks.add(new LifeCircleTask(locations[i][j]));
                }
            }
            try {
                lifeCircleService.invokeAll(tasks);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        lifeCircleService.shutdown();
        plantService.shutdown();
        printService.shutdown();
    }

    public void startPlantSimulation(Location[][] locations) {
        for(int i = 0; i < locations.length; i++) {
            for(int j = 0; j < locations[i].length; j++) {
                plantService.scheduleWithFixedDelay(new PlantGrowTask(locations[i][j]), 0, 100, TimeUnit.MILLISECONDS);
            }
        }
    }

    public void startPrintStats() {
        printService.scheduleWithFixedDelay(new PrintTask(), 1, durationOfStep, TimeUnit.SECONDS);
    }

}

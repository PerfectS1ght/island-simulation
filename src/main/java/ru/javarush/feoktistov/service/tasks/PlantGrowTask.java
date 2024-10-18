package ru.javarush.feoktistov.service.tasks;

import ru.javarush.feoktistov.entity.Location;

public class PlantGrowTask implements Runnable {

    private final Location location;

    public PlantGrowTask(Location location){
        this.location = location;
    }

    @Override
    public void run() {
        location.growPlants();
    }

}

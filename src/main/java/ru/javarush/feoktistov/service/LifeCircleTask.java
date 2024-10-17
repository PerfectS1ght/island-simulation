package ru.javarush.feoktistov.service;

import ru.javarush.feoktistov.entity.Location;

public class LifeCircleTask implements Runnable {

    private final Location location;

    public LifeCircleTask(Location location) {
        this.location = location;
    }

    @Override
    public void run() {
        doCircleOfLifeInLocation();
    }

    private void doCircleOfLifeInLocation() {
        location.animalsFeelsHungry();
        location.eatAnimals();
        location.multiplyAnimals();
        location.moveAnimals();
    }
}

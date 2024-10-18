package ru.javarush.feoktistov.service.tasks;

import ru.javarush.feoktistov.entity.Location;

import java.util.concurrent.Callable;

public class LifeCircleTask implements Callable {

    private final Location location;

    public LifeCircleTask(Location location) {
        this.location = location;
    }

    @Override
    public Object call() throws Exception {
        doCircleOfLifeInLocation();

        return null;
    }

    private void doCircleOfLifeInLocation() {
        location.animalsFeelsHungry();
        location.eatAnimals();
        location.multiplyAnimals();
        location.moveAnimals();
    }

}

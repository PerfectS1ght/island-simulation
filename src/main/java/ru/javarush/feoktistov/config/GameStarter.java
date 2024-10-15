package ru.javarush.feoktistov.config;

import ru.javarush.feoktistov.entity.Island;

public class GameStarter {

    private final Island island = Island.getInstance();

    public void start() {
        island.fillOutIslandByLocations();
    }
}

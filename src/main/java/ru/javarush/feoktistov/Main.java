package ru.javarush.feoktistov;

import ru.javarush.feoktistov.config.GameStarter;
import ru.javarush.feoktistov.entity.Island;
import ru.javarush.feoktistov.util.IslandStatistics;

public class Main {


    public static void main(String[] args) {
        GameStarter gameStarter = new GameStarter();
        gameStarter.start();
        System.out.println("Создание острова завершено!");
        IslandStatistics.showCurrentStatistics();
    }
}
package ru.javarush.feoktistov.app;

import ru.javarush.feoktistov.service.GameStarter;

public class Main {
    public static void main(String[] args) {
        GameStarter gameStarter = new GameStarter();
        gameStarter.startGame();
    }
}
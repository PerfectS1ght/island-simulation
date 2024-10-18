package ru.javarush.feoktistov.service;

import ru.javarush.feoktistov.entity.Island;
import ru.javarush.feoktistov.util.PrinterStats;

import java.util.Scanner;

public class GameStarter {

    private final Island island;
    private final SimulationStarter simulationStarter;

    public GameStarter(){
        this.island = Island.getInstance();
        this.simulationStarter = new SimulationStarter();
    }

    public void startGame() {
        System.out.println("Подождите, идёт инициализация острова...");
        island.fillOutIslandByLocations();
        System.out.println("Инициализация острова завершена!");
        PrinterStats.printStatisticsIsland();
        System.out.println("Нажмите ENTER для начала симуляции:");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("Жизнь на острове началась...");
        simulationStarter.start();
    }
}

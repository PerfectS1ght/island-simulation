package ru.javarush.feoktistov.config;

import ru.javarush.feoktistov.entity.Island;
import ru.javarush.feoktistov.util.IslandStatistics;
import ru.javarush.feoktistov.util.PrinterStats;

import java.util.Scanner;

public class GameStarter {

    private final Island island;

    public GameStarter(){
        island = Island.getInstance();
    }
    public void start() {
        System.out.println("Идёт инициализация острова, пожалуйста, подождите...");
        island.fillOutIslandByLocations();
        System.out.println("Инициализация острова завершена!" + "\n" + "Нажмите ENTER для начала симуляции:");
        PrinterStats.printStatisticsIsland();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();  // Считываем ввод пользователя

            if (input.isEmpty()) {  // Проверяем, что введена пустая строка (Enter)
                int count = 0;
                while(IslandStatistics.isAnyoneAliveOnIsland()) {
                    count++;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("DAY " + count);
                    island.startLifeInLocs();
                    PrinterStats.printStatisticsIsland();
                }
                System.out.println("Все животные на острове погибли.");
                break;
            } else {
                System.out.println("Вы ввели: " + input + ". Попробуйте снова.");
            }
        }

        scanner.close();
    }
}

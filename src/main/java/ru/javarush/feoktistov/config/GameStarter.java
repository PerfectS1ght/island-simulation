package ru.javarush.feoktistov.config;

import ru.javarush.feoktistov.entity.Island;
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
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();  // Считываем ввод пользователя

            if (input.isEmpty()) {  // Проверяем, что введена пустая строка (Enter)
                System.out.println("Вы нажали Enter! Выполняю действие...");
                PrinterStats.printStatisticsIsland();
                island.startLifeInLocs();
                // Здесь выполняем нужные действия
                // Например, можно прервать цикл
                break;  // Завершаем программу
            } else {
                System.out.println("Вы ввели: " + input + ". Попробуйте снова.");
            }
        }

        scanner.close();
    }
}

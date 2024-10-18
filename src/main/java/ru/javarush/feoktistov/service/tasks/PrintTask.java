package ru.javarush.feoktistov.service.tasks;

import ru.javarush.feoktistov.util.PrinterStats;

public class PrintTask implements Runnable {

    @Override
    public void run() {
        PrinterStats.printStatisticsIsland();
    }
}

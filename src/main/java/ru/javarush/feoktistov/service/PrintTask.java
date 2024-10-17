package ru.javarush.feoktistov.service;

import ru.javarush.feoktistov.util.PrinterStats;

public class PrintTask implements Runnable {

    @Override
    public void run() {
        PrinterStats.printStatisticsIsland();
    }
}

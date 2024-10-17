package ru.javarush.feoktistov.app;

import ru.javarush.feoktistov.config.GameStarter;
import ru.javarush.feoktistov.entity.Island;
import ru.javarush.feoktistov.entity.Location;
import ru.javarush.feoktistov.entity.organisms.predators.Wolf;
import ru.javarush.feoktistov.service.SimulationStarter;
import ru.javarush.feoktistov.util.PrinterStats;
import ru.javarush.feoktistov.util.PropertiesReader;

public class Main {
    public static void main(String[] args) {
        SimulationStarter simulationStarter = new SimulationStarter();
        simulationStarter.start();
    }
}
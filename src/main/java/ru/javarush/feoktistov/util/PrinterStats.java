package ru.javarush.feoktistov.util;

import ru.javarush.feoktistov.entity.Location;

public class PrinterStats {

    private PrinterStats() {
        throw new IllegalStateException("Utility class");
    }

    public static void printStatisticsLocation(Location location) {
        System.out.print("Количество травы и животных на локации: | ");
        for(OrganismType type: OrganismType.values()) {
            String emoji = PropertiesReader.getEmojiOfOrganism(type);
            int quantity = IslandStatistics.quantityOfOrganismTypeOnLocation(type, location);
            System.out.print(emoji + " - " + quantity + " | ");
        }
        System.out.println();
    }

    public static synchronized void printStatisticsIsland() {
        System.out.print("Количество травы и животных на острове: | ");
        for(OrganismType type: OrganismType.values()) {
            String emoji = PropertiesReader.getEmojiOfOrganism(type);
            int quantity = IslandStatistics.quantityOfOrganismTypeOnIsland(type);
            System.out.print(emoji + " - " + quantity + " | ");
        }
        System.out.println();
    }

}

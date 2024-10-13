package ru.javarush.feoktistov.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PropertiesReader {

    private PropertiesReader() {
        throw new IllegalStateException("Utility class");
    }

    private static final Properties PROPERTIES = new Properties();
    static{
        try{
            PROPERTIES.load(Files.newBufferedReader(Path.of("src/main/resources/application.properties"), StandardCharsets.UTF_8));
        }catch (IOException e) {
            throw new IllegalArgumentException("Failed to load a properties file", e);
        }
    }

    public static int getLengthOfIsland() {
        String value = getProperty("island.length");
        int result = Integer.parseInt(value);
        if(result <= 0) {
            throw new IllegalArgumentException("The length of Island cannot be negative or Zero");
        }
        return result;
    }

    public static int getWidthOfIsland() {
        String value = getProperty("island.width");
        int result = Integer.parseInt(value);
        if(result <= 0) {
            throw new IllegalArgumentException("The width of Island cannot be negative or Zero");
        }
        return result;
    }

    public static String getEmojiOfOrganism(OrganismType type) {
        String value = getProperty("emoji." + type.toString().toLowerCase());
        if(value.isEmpty() || value.isBlank()) {
            throw new IllegalArgumentException("No emoji found in properties file for: " + type);
        }
        return value;
    }

    public static int getProbabilityOfOrganismEating(OrganismType eater, OrganismType food) {
        String value = getProperty("probability." + eater.toString().toLowerCase() + "." + food.toString().toLowerCase());
        try {
            int result = Integer.parseInt(value);
            if(result < 0) {
                throw new IllegalArgumentException("Probability of " + eater + " and " + food + " in properties file cannot be negative");
            }
            return result;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to parse probability from properties file for " + eater + " and " + food + ": " + e.getMessage());
        }
    }

    public static double getWeightOfOrganism(OrganismType type) {
        String value = getProperty("weight." + type.toString().toLowerCase());
        try {
            double result = Double.parseDouble(value);
            if(result < 0) {
                throw new IllegalArgumentException("Weight of " + type + " in properties file cannot be negative ");
            }
            return result;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to parse weight for " + type + ": " + e.getMessage());
        }
    }

    public static int getMaxCountOfOrganism(OrganismType type) {
        String value = getProperty("maxCount." + type.toString().toLowerCase());
        try {
            int result = Integer.parseInt(value);
            if(result < 0){
                throw new IllegalArgumentException("Max count of " + type + " in properties file cannot be negative " + type);
            }
            return result;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to parse maxCount in properties file for " + type + ": " + e.getMessage());
        }
    }

    public static int getSpeedOfOrganism(OrganismType type) {
        String value = getProperty("speed." + type.toString().toLowerCase());
        try {
            int result = Integer.parseInt(value);
            if(result < 0) {
                throw new IllegalArgumentException("Speed of " + type + " in properties file cannot be negative " + type);
            }
            return result;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to parse speed in properties file for " + type + ": " + e.getMessage());
        }
    }

    public static Double getRequiredFoodWeightOfOrganism(OrganismType type) {
        String value = getProperty("requiredFoodWeight." + type.toString().toLowerCase());
        try {
            double result = Double.parseDouble(value);
            if(result < 0) {
                throw new IllegalArgumentException("Required food weight of " + type + " in properties file cannot be negative");
            }
            return result;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to parse requiredFoodWeight in properties file for " + type + ": " + e.getMessage());
        }
    }

    private static String getProperty(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null || value.isEmpty() || value.isBlank()) {
            throw new IllegalArgumentException("No value found in properties file for key: " + key);
        }
        return value;
    }

}

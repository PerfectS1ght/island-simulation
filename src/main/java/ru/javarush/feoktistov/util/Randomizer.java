package ru.javarush.feoktistov.util;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

    private Randomizer() {
        throw new IllegalStateException("Utility class");
    }

    public static int getRandomInt(int number) {
        if(number < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }
        return ThreadLocalRandom.current().nextInt(0, number + 1);
    }

    public static boolean canDoIt(int probabilityToDoSomething) {
        int chance = getRandomInt(100);
        return chance < probabilityToDoSomething;
    }

    public static int[] getRandomDirection() {
        int[][] directions = {{-1, 0}, {1, 0}, {0,-1}, {0,1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        return directions[Randomizer.getRandomInt(directions.length - 1)];
    }
}

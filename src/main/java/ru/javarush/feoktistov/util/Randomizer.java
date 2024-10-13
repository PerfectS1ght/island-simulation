package ru.javarush.feoktistov.util;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

    private Randomizer() {
        throw new IllegalStateException("Utility class");
    }

    public static int getRandomInt(int number) {
        return ThreadLocalRandom.current().nextInt(0, number + 1);
    }
}

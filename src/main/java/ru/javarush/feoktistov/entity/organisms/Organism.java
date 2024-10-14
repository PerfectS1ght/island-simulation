package ru.javarush.feoktistov.entity.organisms;

import ru.javarush.feoktistov.entity.Location;
import ru.javarush.feoktistov.util.OrganismType;
import ru.javarush.feoktistov.util.PropertiesReader;

import java.util.Objects;
import java.util.UUID;

public abstract class Organism {

    protected OrganismType type;
    protected String id;
    protected double weight;
    protected int maxCount;
    protected String emoji;

    protected final int multiplyChance;

    protected Organism(OrganismType type) {
        this.type = type;
        this.id = UUID.randomUUID().toString();
        this.multiplyChance = PropertiesReader.getMultiplyChance(type);
        this.weight = PropertiesReader.getWeightOfOrganism(type);
        this.maxCount = PropertiesReader.getMaxCountOfOrganism(type);
        this.emoji = PropertiesReader.getEmojiOfOrganism(type);
    }

    public OrganismType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public int getMultiplyChance() {
        return multiplyChance;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setType(OrganismType type) {
        this.type = type;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organism organism = (Organism) o;
        return Double.compare(weight, organism.weight) == 0 && maxCount == organism.maxCount && type == organism.type && Objects.equals(id, organism.id) && Objects.equals(emoji, organism.emoji);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, id, weight, maxCount, emoji);
    }

    @Override
    public String toString() {
        return "Organism{" +
                "type=" + type +
                ", id='" + id + '\'' +
                ", weight=" + weight +
                ", maxCount=" + maxCount +
                ", emoji='" + emoji + '\'' +
                '}';
    }

    protected void increaseWeight(double weight) {
        this.weight += weight;
    }

    protected void decreaseWeight(double weight) {
        this.weight -= weight;
    }


    public abstract void multiply(Location location);

    public abstract void die(Location location);

}

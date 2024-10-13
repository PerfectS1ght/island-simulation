package ru.javarush.feoktistov.entity.organisms;

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

    protected Organism(OrganismType type) {
        this.type = type;
        this.id = UUID.randomUUID().toString();
        this.weight = PropertiesReader.getWeightOfOrganism(type);
        this.maxCount = PropertiesReader.getMaxCountOfOrganism(type);
        this.emoji = PropertiesReader.getEmojiOfOrganism(type);
    }

    public OrganismType getType() {
        return type;
    }

    protected String getId() {
        return id;
    }

    protected double getWeight() {
        return weight;
    }

    protected int getMaxCount() {
        return maxCount;
    }

    protected String getEmoji() {
        return emoji;
    }

    protected void setType(OrganismType type) {
        this.type = type;
    }

    protected void setWeight(double weight) {
        this.weight = weight;
    }

    protected void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    protected void setEmoji(String emoji) {
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

/*
    protected abstract void multiply();
    protected abstract void die();
*/
}

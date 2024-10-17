package ru.javarush.feoktistov.entity.organisms;

import ru.javarush.feoktistov.entity.Island;
import ru.javarush.feoktistov.entity.Location;
import ru.javarush.feoktistov.repository.AnimalFactory;
import ru.javarush.feoktistov.util.*;

import java.util.*;

public abstract class Animal extends Organism {

    protected int speed;
    protected double requiredFoodWeight;
    protected int maxTriesToMultiply;

    protected Animal(OrganismType type) {
        super(type);
        this.speed = PropertiesReader.getSpeedOfOrganism(type);
        this.requiredFoodWeight = PropertiesReader.getRequiredFoodWeightOfOrganism(type);
        this.maxTriesToMultiply = PropertiesReader.getMaxTriesOfMultiply();
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getRequiredFoodWeight() {
        return requiredFoodWeight;
    }

    public void setRequiredFoodWeight(double requiredFoodWeight) {
        this.requiredFoodWeight = requiredFoodWeight;
    }

    public int getMaxTriesToMultiply() {
        return maxTriesToMultiply;
    }

    public void setMaxTriesToMultiply(int maxTriesToMultiply) {
        this.maxTriesToMultiply = maxTriesToMultiply;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Animal animal = (Animal) o;
        return speed == animal.speed && Double.compare(requiredFoodWeight, animal.requiredFoodWeight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), speed, requiredFoodWeight);
    }

    @Override
    public void multiply(Location location) {
        if(IslandStatistics.isLocationFullOfThisType(this.getType(), location)) {
            return;
        }
        int currentCountThisTypeInLocation = IslandStatistics.quantityOfOrganismTypeOnLocation(this.getType(), location);
        List<Organism> organisms = location.getPopulation().get(this.getType());
        List<Animal> bornAnimals = new ArrayList<>();
        int readyToAdd = this.getMaxCount() - currentCountThisTypeInLocation;
        int counterOfBorn = 0;
        int counterOfTries = 0;
        for(Organism organism: organisms) {
            if(this.getId().equals(organism.getId())){
                continue;
            }
            if(counterOfBorn == readyToAdd || counterOfTries == this.getMaxTriesToMultiply()) {
                    break;
            }
            if(Randomizer.canDoIt(this.getMultiplyChance())) {
                    bornAnimals.add(AnimalFactory.createAnimal(this.type));
                    counterOfBorn++;
            }
            counterOfTries++;
        }
        organisms.addAll(bornAnimals);
        bornAnimals.clear();
    }

    public void eat(Location location) {
        double eatenFood = 0;
        Map<OrganismType, List<Organism>> population = location.getPopulation();

        // Проходим по всем типам организмов в популяции
        for (Map.Entry<OrganismType, List<Organism>> pair : population.entrySet()) {
            if (!isStillHungry(eatenFood)) {
                break;
            }

            OrganismType key = pair.getKey();
            int chanceToEat;

            // Пропускаем, если тип совпадает или вероятность поедания равна 0
            if (this.getType() == key || (chanceToEat = PropertiesReader.getProbabilityOfOrganismEating(this.getType(), key)) == 0) {
                continue;
            }

            // Используем Iterator для удаления организмов
            List<Organism> organisms = pair.getValue();
            Iterator<Organism> iterator = organisms.iterator();

            while (iterator.hasNext()) {
                Organism organism = iterator.next();

                // Проверяем, может ли животное поедать данный организм
                if (Randomizer.canDoIt(chanceToEat)) {
                    eatenFood += organism.getWeight();
                    iterator.remove(); // Удаляем организм из списка

                    if (!isStillHungry(eatenFood)) {
                        break; // Если животное больше не голодно, выходим из цикла
                    }
                }
            }
        }

        this.increaseWeight(eatenFood); // Увеличиваем вес животного на количество съеденной пищи
    }

    public void move(Location location) {
        Island island = Island.getInstance();
        int distance = Randomizer.getRandomInt(this.getSpeed());
        if(distance == 0) {
            return;
        }
        int count = 0;
        int currentI = location.getPositionY();
        int currentJ = location.getPositionX();
        Location targetLocation = null;

        while(count < distance) {
            int[] directions = Randomizer.getRandomDirection();
            int newI = currentI + directions[0];
            int newJ = currentJ + directions[1];

            if(island.isLocationExistsByCoordinates(newI, newJ)) {
                currentI = newI;
                currentJ = newJ;
                targetLocation = island.getLocationByCoordinates(currentI, currentJ);
                count++;
            }
        }
        if(IslandStatistics.isLocationFullOfThisType(this.getType(), location)) {
            return;
        }

        this.die(location);
        Map<OrganismType, List<Organism>> population = targetLocation.getPopulation();
        population.get(this.getType()).add(this);
    }

    private boolean isStillHungry(double eatenFood) {
        double requiredEat = this.getRequiredFoodWeight();
        return requiredEat > eatenFood;
    }


}

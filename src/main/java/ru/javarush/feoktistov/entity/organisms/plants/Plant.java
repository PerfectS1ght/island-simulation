package ru.javarush.feoktistov.entity.organisms.plants;

import ru.javarush.feoktistov.entity.Location;
import ru.javarush.feoktistov.entity.organisms.Organism;
import ru.javarush.feoktistov.repository.PlantFactory;
import ru.javarush.feoktistov.util.OrganismType;
import ru.javarush.feoktistov.util.Randomizer;

import java.util.List;
import java.util.Map;

public class Plant extends Organism {

    public Plant() {
        super(OrganismType.PLANT);
    }

    @Override
    public void multiply(Location location) {
        if(location.isPlantCapacityReached()){
            return;
        }
        if(Randomizer.canDoIt(this.getMultiplyChance())) {
            Map<OrganismType, List<Organism>> population = location.getPopulation();
            population.get(this.getType()).add(PlantFactory.createPlant(this.getType()));
        }
    }

}

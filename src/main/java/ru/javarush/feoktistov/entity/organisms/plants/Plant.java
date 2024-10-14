package ru.javarush.feoktistov.entity.organisms.plants;

import ru.javarush.feoktistov.entity.Location;
import ru.javarush.feoktistov.entity.organisms.Organism;
import ru.javarush.feoktistov.repository.PlantFactory;
import ru.javarush.feoktistov.util.OrganismType;
import ru.javarush.feoktistov.util.Randomizer;


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
            location.getPlants().add(PlantFactory.createPlant(OrganismType.PLANT));
        }
    }

    @Override
    public void die(Location location) {
        location.getPlants().remove(this);
    }


}

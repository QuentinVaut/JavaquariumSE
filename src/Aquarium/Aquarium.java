package Aquarium;

import Poissons.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Aquarium {


    private List<Poisson> poissons;
    private List<Algue> algues;
    private static Aquarium INSTANCE = null;

    private Aquarium() {

    }

    public void initAquarium(int nbPoissons, int nbAlgues) {
        FishFactory fishFactory = new FishFactory();
        poissons = IntStream.range(0, nbPoissons)
                .mapToObj(i -> "Poisson " + i)
                .map(fishFactory::randomWithName)
                .collect(Collectors.toList());
        algues = IntStream.range(0, nbAlgues)
                .mapToObj(i -> new Algue())
                .collect(Collectors.toList());
    }

    /*public Aquarium(int nbPoissons, int nbAlgues) {
        FishFactory fishFactory = new FishFactory();
        poissons = IntStream.range(0, nbPoissons)
                .mapToObj(i -> "Poisson " + i)
                .map(fishFactory::randomWithName)
                .collect(Collectors.toList());
        algues = IntStream.range(0, nbAlgues)
                .mapToObj(i -> new Algue())
                .collect(Collectors.toList());
    }*/

    public static Aquarium getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Aquarium();
        }

        return INSTANCE;
    }

    public List<Poisson> getPoissons() {
        return poissons;
    }

    public List<Algue> getAlgues() {
        return algues;
    }

    public void newTour() {
        poissons.forEach(this::newTour);
        poissons = poissons.stream()
                .filter(Poisson::isAlive)
                .collect(Collectors.toList());
        algues = algues.stream()
                .filter(Algue::isAlive)
                .collect(Collectors.toList());
    }

    private void newTour(Poisson poisson) {
        if(poisson.isDead()) {
            return;
        }
        if (poisson instanceof Carnivore) {
            Optional<Poisson> otherFish = pickOtherRandomFish(poisson);
            otherFish.ifPresent(((Carnivore) poisson)::mange);
        } else if (poisson instanceof Herbivore) {
            Optional<Algue> algue = pickRandomAlgue();
            algue.ifPresent(((Herbivore) poisson)::mange);
        }
    }

    private boolean thereAreOtherFishes() {
        return poissons.size() > 1;
    }

    private boolean thereIsNoOtherFish() {
        return !thereAreOtherFishes();
    }

    private Optional<Poisson> pickOtherRandomFish(Poisson fishNotToPick) {
        if (thereIsNoOtherFish()) {
            return Optional.empty();
        }
        while (true) {
            Poisson otherFish = poissons.get(new Random().nextInt(poissons.size()));
            if (otherFish != fishNotToPick) {
                return Optional.of(otherFish);
            }
        }
    }

    private Optional<Algue> pickRandomAlgue() {
        if (algues.size() == 0) {
            return Optional.empty();
        }
        Algue algue = algues.get(new Random().nextInt(algues.size()));
        return Optional.of(algue);
    }
}

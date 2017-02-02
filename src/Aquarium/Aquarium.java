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

    public void displayAquarium() {
        System.out.println("AQUARIUM INFOS :");
        getPoissons().forEach(System.out::println);
        getAlgues().forEach(System.out::println);
        System.out.println("//////////////////////////////////////////////////////FIN////////////////////////////////////////////");
    }


    public Aquarium(int nbPoissons, int nbAlgues) {
        FishFactory fishFactory = new FishFactory();
        poissons = IntStream.range(0, nbPoissons)
                .mapToObj(i -> "Poisson " + i)
                .map(fishFactory::randomWithName)
                .collect(Collectors.toList());
        algues = IntStream.range(0, nbAlgues)
                .mapToObj(i -> new Algue())
                .collect(Collectors.toList());
    }

    public List<Poisson> getPoissons() {
        return poissons;
    }

    public List<Algue> getAlgues() {
        return algues;
    }

    public void newTour() {
        poissons.forEach(poisson -> poisson.setPv(poisson.getPv() -1));
        //poissons.forEach(this::newTour);
        for(Poisson poisson : poissons) {
            poisson.setPv(poisson.getPv() -1);
            poisson.setTour(poisson.getTour() + 1);
            if(poisson.getTour() > 20) {
                poisson.die();
                System.out.println("Poisson : " + poisson.getNom() + " est mort de vieillesse");
            }
            newTour(poisson);
        }
        poissons = poissons.stream()
                .filter(poisson -> poisson.isAlive())
                .collect(Collectors.toList());

        for(Algue algue : algues) {
            algue.setPv(algue.getPv() + 1);
            algue.setTour(algue.getTour() + 1);
            if(algue.getTour() > 20) {
                algue.die();
                System.out.println("Une algue est morte de vieillesse.");
            }
        }

        //algues.forEach(algue -> algue.setPv(algue.getPv() + 1));
        algues = algues.stream()
                .filter(Algue::isAlive)
                .collect(Collectors.toList());
        displayAquarium();
    }

    private void newTour(Poisson poisson) {
        if(poisson.isDead()) {
            return;
        }

        if(poisson.getPv() <= 5) {
            if (poisson instanceof Carnivore) {
                Optional<Poisson> otherFish = pickOtherRandomFish(poisson);
                otherFish = otherFish.filter(Poisson::isAlive);
                otherFish.ifPresent(((Carnivore) poisson)::mange);
            } else if (poisson instanceof Herbivore) {
                Optional<Algue> algue = pickRandomAlgue();
                algue = algue.filter(Algue::isAlive);
                algue.ifPresent(((Herbivore) poisson)::mange);
            }
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
            if (otherFish != fishNotToPick && otherFish.getClass() != fishNotToPick.getClass()) {
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

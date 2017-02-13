package Aquarium;

import Poissons.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.round;

public class Aquarium {


    private List<Poisson> poissons;
    private List<Poisson> babyPoissons;
    private List<Algue> algues;
    private List<Algue> babyAlgues;
    private int countBaby = 1;

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
                .mapToObj(i -> new Algue(10))
                .collect(Collectors.toList());
    }

    public List<Poisson> getPoissons() {
        return poissons;
    }

    public List<Algue> getAlgues() {
        return algues;
    }

    public void newTour() {
        babyPoissons = new ArrayList<>();
        babyAlgues = new ArrayList<>();
        //poissons.forEach(poisson -> poisson.setPv(poisson.getPv() -1));
        //poissons.forEach(this::newTour);
        for(Poisson poisson : poissons) {
            poisson.setPv(poisson.getPv() -1);
            poisson.setTour(poisson.getTour() + 1);
            if(poisson.getTour() > 20) {
                poisson.die();
                System.out.println("Poisson : " + poisson.getNom() + " est mort de vieillesse");
            }
            if(poisson.getPv() <= 0 && poisson.isAlive()) {
                poisson.die();
                System.out.println("Poisson : " + poisson.getNom() + " est mort de faim");
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
            if(algue.getPv() >= 10) {
                reproduireAlgue(algue);
            }
        }

        //algues.forEach(algue -> algue.setPv(algue.getPv() + 1));
        algues = algues.stream()
                .filter(Algue::isAlive)
                .collect(Collectors.toList());
        poissons.addAll(babyPoissons);
        algues.addAll(babyAlgues);
        displayAquarium();
    }

    private void newTour(Poisson poisson) {
        if(poisson.isDead()) {
            return;
        }

        if(poisson.getPv() <= 5) {
            if (poisson instanceof Carnivore) {
                Optional<Poisson> otherFish = pickOtherRandomFish(poisson);
                otherFish = otherFish.filter(poissonX -> poissonX.isAlive() && poissonX.getClass() != poisson.getClass());
                otherFish.ifPresent(((Carnivore) poisson)::mange);
            } else if (poisson instanceof Herbivore) {
                Optional<Algue> algue = pickRandomAlgue();
                algue = algue.filter(Algue::isAlive);
                algue.ifPresent(((Herbivore) poisson)::mange);
            }
        } else {
            reproduirePoisson(poisson);
        }
    }

    private void reproduirePoisson(Poisson poisson) {
        Optional<Poisson> otherFish = pickOtherRandomFish(poisson);
        otherFish = otherFish.filter(poissonX -> poissonX.isAlive() && poissonX.getClass() == poisson.getClass() && poissonX.getGenre() != poisson.getGenre());
        otherFish.ifPresent(poissonX -> seReproduire(poissonX) );
    }

    private void reproduireAlgue(Algue algue) {
        Algue babyAlgue = new Algue(round(algue.getPv() / 2));
        algue.setPv(round(algue.getPv() / 2));
        babyAlgues.add(babyAlgue);
        System.out.println("Une algue avec  " + babyAlgue.getPv() + " pv est née.");
    }

    private void seReproduire(Poisson poisson) {
        FishFactory fishFactory = new FishFactory();
        String strNamePoisson = "PoissonBaby " + countBaby;
        babyPoissons.add(fishFactory.poissonWithName(poisson.getClass(),strNamePoisson));
        System.out.println("Un poisson " + poisson.getClass().getSimpleName() + " est né.");
        countBaby++;
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

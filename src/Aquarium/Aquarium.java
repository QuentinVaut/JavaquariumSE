package Aquarium;

import Poissons.*;

import java.util.List;
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
        if(INSTANCE == null) {
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
        Random rnd = new Random();
        for(Poisson p : getPoissons()) {
            int i = rnd.nextInt(getPoissons().size());
            if(p instanceof PoissonCarnivore) {
                if (getPoissons().get(i) != p) {
                    ((PoissonCarnivore) p).mange(getPoissons().get(i));
                }
            } else {
                Algue algueManger =  getAlgues().get(i);
                ((PoissonHerbivore) p).mange(algueManger);
            }
        }
    }
}

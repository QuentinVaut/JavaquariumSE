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
        //Chaque poisson mange
        for(Poisson p : getPoissons()) {
            Random rnd = new Random();
            //On prend un poisson au hasard
            if(p instanceof PoissonCarnivore) {
                int i = rnd.nextInt(getPoissons().size());
                Poisson pManger =  getPoissons().get(i);
                //On vérifie qu'il ne se mange pas lui mêmee
                if (pManger != p) {
                    ((PoissonCarnivore) p).mange(pManger);
                }
            } else {
                int i = rnd.nextInt(getAlgues().size());
                Algue algueManger =  getAlgues().get(i);
                ((PoissonHerbivore) p).mange(algueManger);
            }
        }
    }
}

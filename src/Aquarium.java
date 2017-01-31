import Poissons.Algue;
import Poissons.FishFactory;
import Poissons.Poisson;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Aquarium {

    private Collection<Poisson> poissons;
    private Collection<Algue> algues;

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

    public Collection<Poisson> getPoissons() {
        return poissons;
    }

    public Collection<Algue> getAlgues() {
        return algues;
    }
}

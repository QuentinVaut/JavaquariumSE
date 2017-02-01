package Poissons;

import Aquarium.Aquarium;

/**
 * Created by quentin on 31/01/2017.
 */
public abstract class PoissonCarnivore extends Poisson implements Carnivore {

    public PoissonCarnivore(String nom, Genre genre) {
        super(nom, genre);
    }

    public void mange(Poisson poisson) {
        Aquarium aquarium = Aquarium.getInstance();
        aquarium.getPoissons().remove(poisson);
        System.out.println("Poisson manger : " + poisson.toString());
    }
}

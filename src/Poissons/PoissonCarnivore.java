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
        System.out.println("Poisson :" + this.getNom() + " mange : " + poisson.getNom());
        poisson.setPv(poisson.getPv() - 4);
        if(poisson.getPv() <= 0) {
            poisson.die();
        }
        this.setPv(this.getPv() + 5);
    }
}

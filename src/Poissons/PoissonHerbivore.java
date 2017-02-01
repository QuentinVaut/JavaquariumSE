package Poissons;

/**
 * Created by quentin on 31/01/2017.
 */
public abstract class PoissonHerbivore extends Poisson implements Herbivore {
    public PoissonHerbivore(String nom, Genre genre) {
        super(nom, genre);
    }

    @Override
    public void mange(Algue algue) {

    }
}

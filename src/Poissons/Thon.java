package Poissons;

/**
 * Created by quentin on 31/01/2017.
 */
public class Thon extends Poisson implements Carnivore {
    public Thon(String nom, Genre genre) {
        super(nom, genre);
    }

    @Override
    public void mange(Poisson poisson) {

    }
}

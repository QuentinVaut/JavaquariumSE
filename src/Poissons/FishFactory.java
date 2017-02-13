package Poissons;

import java.util.Random;

public class FishFactory {

    private static Class[] registeredFishTypes = new Class[] {
            Bar.class,
            Carpe.class,
            Merou.class,
            PoissonClown.class,
            Sole.class,
            Thon.class
    };

    public Poisson poissonWithName(Class espece,String name) {
        Genre genre = Genre.getRandom();
        if(espece == Bar.class) {
            return new Bar(name, genre);
        }
        if(espece == Carpe.class) {
            return new Carpe(name, genre);
        }
        if(espece == Merou.class) {
            return new Merou(name, genre);
        }
        if(espece == PoissonClown.class) {
            return new PoissonClown(name, genre);
        }
        if(espece == Sole.class) {
            return new Sole(name, genre);
        }
        if(espece == Thon.class) {
            return new Thon(name, genre);
        }
        return null;
    }

    public Poisson randomWithName(String name) {
        Genre genre = Genre.getRandom();
        Class fishType = registeredFishTypes[(new Random()).nextInt(registeredFishTypes.length)];
        if(fishType == Bar.class) {
            return new Bar(name, genre);
        }
        if(fishType == Carpe.class) {
            return new Carpe(name, genre);
        }
        if(fishType == Merou.class) {
            return new Merou(name, genre);
        }
        if(fishType == PoissonClown.class) {
            return new PoissonClown(name, genre);
        }
        if(fishType == Sole.class) {
            return new Sole(name, genre);
        }
        if(fishType == Thon.class) {
            return new Thon(name, genre);
        }
        throw new IllegalArgumentException("Requested type is not in the registered typed.");
    }

}

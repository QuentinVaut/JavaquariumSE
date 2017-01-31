package Poissons;

/**
 * Created by quentin on 31/01/2017.
 */
public enum Espece {
    MEROU,THON,POISSONCLOWN,SOLE,BAR,CARPE;

    public static Espece getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}

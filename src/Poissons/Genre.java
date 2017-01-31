package Poissons;

/**
 * Created by quentin on 31/01/2017.
 */
public enum  Genre {
    MALE,FEMELLE;

    public static Genre getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}

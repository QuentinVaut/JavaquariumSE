package Poissons;

public interface LivingThing {

    void die();

    boolean isDead();

    default boolean isAlive() {
        return !isDead();
    }

}

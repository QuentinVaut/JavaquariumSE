package Poissons;

/**
 * Created by quentin on 31/01/2017.
 */
public class Algue implements LivingThing {

    private boolean dead = false;

    @Override
    public void die() {
        if(dead) {
            throw new IllegalStateException("A dead algae cannot die");
        }
        dead = true;
    }

    @Override
    public boolean isDead() {
        return dead;
    }
}

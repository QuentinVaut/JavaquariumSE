package Poissons;

/**
 * Created by quentin on 31/01/2017.
 */
public class Algue implements LivingThing {

    private boolean dead = false;
    private int pv = 10;
    private int tour = 0;

    public Algue(int pv) {
        this.pv = pv;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getTour() {
        return tour;
    }

    public void setTour(int tour) {
        this.tour = tour;
    }

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

    @Override
    public String toString() {
        return "Algue{" +
                "pv=" + pv +
                "En vie=" + this.isAlive() +
                '}';
    }
}

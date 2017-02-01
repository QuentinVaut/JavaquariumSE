import Aquarium.Aquarium;

/**
 * Created by quentin on 31/01/2017.
 */
public class Javaquarium {

    public static void main(String[] args) {
        System.out.println("Démarrage de l'aquarium");
        Aquarium aquarium = new Aquarium(10, 5);



        //aquarium.getPoissons().forEach(System.out::println);


        while(aquarium.getPoissons().size() != 1 ) {
            aquarium.newTour();
            System.out.println("NB poissons : " + aquarium.getPoissons().size());
            System.out.println("NB Algues : " + aquarium.getAlgues().size());
        }
    }
}

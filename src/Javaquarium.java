import Aquarium.Aquarium;

/**
 * Created by quentin on 31/01/2017.
 */
public class Javaquarium {

    public static void main(String[] args) {
        System.out.println("Démarrage de l'aquarium");
        Aquarium aquarium = new Aquarium(20, 5);



        //aquarium.getPoissons().forEach(System.out::println);


        int countTour = 1;
        for(int i = 0; i <= 25;i++) {
            System.out.println("TOUR : " + countTour);
            aquarium.newTour();
            System.out.println("NB poissons : " + aquarium.getPoissons().size());
            System.out.println("NB Algues : " + aquarium.getAlgues().size());
            countTour++;
        }
    }
}

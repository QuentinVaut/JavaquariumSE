import Poissons.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quentin on 31/01/2017.
 */
public class Javaquarium {

    private static List<Poisson> lstPoissons = new ArrayList<>();
    private static List<Algue> lstAlgues = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Démarrage de l'aquarium");
        genererAquarium(10,5);
        System.out.println("NB poissons : " + lstPoissons.size());
        System.out.println("NB Algues : " + lstAlgues.size());
        lstPoissons.forEach(Poisson::toString);

        for(Poisson p : lstPoissons) {
            System.out.println(p.toString());
        }
    }

    private static void genererAquarium(int nbPoisson, int nbAlgue) {
        for(int i = 1; i <= nbPoisson; i++)
        {
            ajouterPoisson("Poisson" + i,Genre.getRandom(),Espece.getRandom());
        }

        for(int i = 1; i <= nbAlgue; i++)
        {
            Algue a = new Algue();
            lstAlgues.add(a);
        }
    }

    private static void ajouterPoisson(String nom, Genre genre, Espece espece) {
        if(espece.equals(Espece.MEROU) || espece.equals(Espece.THON) || espece.equals(Espece.POISSONCLOWN)) {
            lstPoissons.add(new PoissonCarnivore(nom,genre,espece));
        } else {
            lstPoissons.add(new PoissonHerbivore(nom,genre,espece));
        }
    }
}

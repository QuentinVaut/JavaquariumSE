package Poissons;

/**
 * Created by quentin on 31/01/2017.
 */
public class Poisson {

    private String nom;
    private Genre genre;
    private Espece espece;

    public Poisson(String nom, Genre genre, Espece espece) {
        this.nom = nom;
        this.genre = genre;
        this.espece = espece;
    }

    public Poisson(String nom, Genre genre) {
        this.nom = nom;
        this.genre = genre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Poisson{" +
                "nom='" + nom + '\'' +
                ", genre=" + genre +
                ", espece=" + espece +
                '}';
    }

    public static Poisson randomWithName(String name) {
        return new Poisson(name, Genre.getRandom(), Espece.getRandom());
    }


}

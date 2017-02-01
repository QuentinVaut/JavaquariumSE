package Poissons;


public abstract class Poisson {

    private String nom;
    private Genre genre;

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
        String strRegimeAlimentaire;
        if(this instanceof Carnivore) {
            strRegimeAlimentaire = "CARNIVORE";
        } else {
            strRegimeAlimentaire = "HERBIVORE";
        }

        return getClass().getSimpleName() + "{" +
                "nom='" + nom + '\'' +
                ", genre=" + genre + '\'' +
                ", alimentaire=" + strRegimeAlimentaire +
                '}';
    }



}

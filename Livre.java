package model;

public class Livre {
    private int ISBN;
    private String titre;
    private float prix;
    private Adherent adherent; 
    private Auteur auteur;     

    
    public Livre(int ISBN, String titre, float prix, Adherent adherent, Auteur auteur) {
        this.ISBN = ISBN;
        this.titre = titre;
        this.prix = prix;
        this.adherent = adherent;
        this.auteur = auteur;
    }

    
    public Livre(String isbn2, String titre2, float prix2) {
		// TODO Auto-generated constructor stub
	}


	public int getISBN() { return ISBN; }
    public void setISBN(int ISBN) { this.ISBN = ISBN; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public float getPrix() { return prix; }
    public void setPrix(float prix) { this.prix = prix; }

    public Adherent getAdherent() { return adherent; }
    public void setAdherent(Adherent adherent) { this.adherent = adherent; }

    public Auteur getAuteur() { return auteur; }
    public void setAuteur(Auteur auteur) { this.auteur = auteur; }

    @Override
    public String toString() {
        return titre + " - " + auteur.getNom() + " (prêté par " + adherent.getNom() + ")";
    }
}

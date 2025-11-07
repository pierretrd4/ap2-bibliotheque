package model;

import java.util.ArrayList;

public class Adherent {
    private int num;
    private String nom;
    private String prenom;
    private String email;
    private ArrayList<Livre> ListLivres;
    
    public Adherent(int num, String nom, String prenom, String email) {
        this.num = num;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.ListLivres = new ArrayList<>();
    }

    
    public int getNum() { return num; }
    public void setNum(int num) { this.num = num; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }


	public ArrayList<Livre> getListLivres() {
		return ListLivres;
	}


	public void setListeLivres(ArrayList<Livre> listeLivres) {
		ListLivres = listeLivres;
	}



	}



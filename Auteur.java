package model;

import java.time.LocalDate;

public class Auteur {
    private int num;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String description;

    
    public Auteur(int num, String nom, String prenom, LocalDate dateNaissance, String description) {
        this.num = num;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.description = description;
    }

    
    public int getNum() { return num; }
    public void setNum(int num) { this.num = num; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    
    public String toString() {
        return nom + " " + prenom + " (" + description + ")";
    }
}

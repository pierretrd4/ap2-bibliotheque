package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class model {

    private Connection con;
    private ArrayList<Livre> listLivre;
    private ArrayList<Auteur> listAuteur;
    private ArrayList<Adherent> listAdherent;

    private final String BDD = "ap2_biblio";
    private final String url = "jdbc:mysql://localhost:3306/" + BDD + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Europe/Paris";
    private final String user = "root";
    private final String passwd = "";

    public model() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, user, passwd);
        System.out.println("Connexion à la base de données");

        listLivre = new ArrayList<>();
        listAuteur = new ArrayList<>();
        listAdherent = new ArrayList<>();
    }
    public void emprunterLivre(int isbn, int numAdherent) throws SQLException {
        // MAJ BDD
        String sql = "UPDATE livre SET adherent = ? WHERE ISBN = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, numAdherent);
        ps.setInt(2, isbn);
        int rows = ps.executeUpdate();
        ps.close();

        if (rows > 0) {
            System.out.println(" Livre avec ISBN " + isbn + " emprunté par l'adhérent n°" + numAdherent);
        } else {
            System.out.println(" Aucun livre trouvé avec ISBN " + isbn);
        }

        // MAJ memoire
        Livre livre = null;
        for (Livre l : listLivre) {
            if (l.getISBN() == isbn) {
                livre = l;
                break;
            }
        }

        if (livre != null) {
            Adherent ad = findAdherent(numAdherent);
            if (ad != null) {
                livre.setAdherent(ad);
                if (ad.getListLivres() == null) {
                    ad.setListeLivres(new ArrayList<>());
                }
                ad.getListLivres().add(livre);
                System.out.println(" Livre ajouté à la liste des livres de l'adhérent.");
            } else {
                System.out.println(" Adhérent n°" + numAdherent + " non trouvé en mémoire !");
            }
        } else {
            System.out.println(" Livre avec ISBN " + isbn + " non trouvé en mémoire !");
        }
    }



    public void updateAdherent(Adherent ad) throws SQLException {
        String sql = "UPDATE adherent SET nom = ?, prenom = ?, email = ? WHERE num = ?";
        PreparedStatement ps = con.prepareStatement(sql); 
        ps.setString(1, ad.getNom());
        ps.setString(2, ad.getPrenom());
        ps.setString(3, ad.getEmail());
        ps.setInt(4, ad.getNum());
        ps.executeUpdate();
        ps.close();
    }

    public void restituerLivre(int isbn) throws SQLException {
        String sql = "UPDATE livre SET adherent = NULL WHERE ISBN = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, isbn);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Livre avec ISBN " + isbn + " restitué !");
            } else {
                System.out.println("Aucun livre trouvé avec cet ISBN !");
            }
        }
    }
    
    public ArrayList<Livre> getListLivre() { return listLivre; }
    public void setListLivre(ArrayList<Livre> listLivre) { this.listLivre = listLivre; }

    public ArrayList<Auteur> getListAuteur() { return listAuteur; }
    public void setListAuteur(ArrayList<Auteur> listAuteur) { this.listAuteur = listAuteur; }

    public ArrayList<Adherent> getListAdherent() { return listAdherent; }
    public void setListAdherent(ArrayList<Adherent> listAdherent) { this.listAdherent = listAdherent; }

    // chargemnet bdd
    public void getall() throws SQLException {

        listAuteur.clear();
        listAdherent.clear();
        listLivre.clear();

        Statement stmt = con.createStatement();
        ResultSet resultat;

        // Chargement auteurs
        resultat = stmt.executeQuery("SELECT * FROM AUTEUR");
        while (resultat.next()) {
            Auteur a = new Auteur(
                    resultat.getInt(1),
                    resultat.getString(2),
                    resultat.getString(3),
                    null,
                    resultat.getString(5)
            );
            listAuteur.add(a);
        }

        // Chargement adhérents
        resultat = stmt.executeQuery("SELECT * FROM ADHERENT");
        while (resultat.next()) {
            Adherent ad = new Adherent(
                    resultat.getInt(1),
                    resultat.getString(2),
                    resultat.getString(3),
                    resultat.getString(4)
            );
            listAdherent.add(ad);
        }

        // Chargement livres
        resultat = stmt.executeQuery("SELECT * FROM LIVRE");
        while (resultat.next()) {
            Livre l = new Livre(
                    resultat.getInt(1),
                    resultat.getString(2),
                    resultat.getFloat(3),
                    null,
                    null
            );

            String numAdherent = resultat.getString(4);
            if (numAdherent != null) {
                Adherent ad = findAdherent(Integer.parseInt(numAdherent));
                if (ad != null) {
                    l.setAdherent(ad);
                    ad.getListLivres().add(l);
                }
            }

            String numAuteur = resultat.getString(5);
            if (numAuteur != null) {
                Auteur a = findAuteur(Integer.parseInt(numAuteur));
                if (a != null) {
                    l.setAuteur(a);
                }
            }

            listLivre.add(l);
        }
        resultat.close();
        stmt.close();
    }

    
    public Auteur findAuteur(int num) {
        for (Auteur a : listAuteur) {
            if (a.getNum() == num) return a;
        }
        return null;
    }

    public Adherent findAdherent(int num) {
        for (Adherent ad : listAdherent) {
            if (ad.getNum() == num) return ad;
        }
        return null;
    }
}

package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.mainMVC;
import model.Livre;
import java.awt.Color;

public class view_Catalogue {

    private JFrame frame;
    private JTable table;

    public view_Catalogue() throws SQLException, ClassNotFoundException {
        initialize();
        frame.setVisible(true);
    }

    private void initialize() throws SQLException, ClassNotFoundException {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setTitle("Liste des livres");
        frame.setBounds(100, 100, 700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

        
        JLabel lblTitre = new JLabel("Liste des livres");
        lblTitre.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitre.setBounds(270, 20, 200, 30);
        frame.getContentPane().add(lblTitre);

        // Tableau
        String[] colonnes = {"ISBN", "Titre", "Auteur", "Disponibilité"};
        DefaultTableModel model = new DefaultTableModel(colonnes, 0);
        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(20);

        // On récupère les livres depuis le model avec le mainmvc.getm
        for (Livre l : mainMVC.getM().getListLivre()) {
            String auteur = (l.getAuteur() != null) ? l.getAuteur().getNom() : "—";
            String dispo = (l.getAdherent() == null) ? "Disponible" : "Emprunté";
            Object[] row = {l.getISBN(), l.getTitre(), auteur, dispo};
            model.addRow(row);
        }
        // Barre de nav
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 80, 600, 200);
        frame.getContentPane().add(scrollPane);

        
        JButton btnAccueil = new JButton("ACCUEIL");
        btnAccueil.setFont(new Font("Arial", Font.BOLD, 14));
        btnAccueil.setBounds(280, 310, 120, 35);
        frame.getContentPane().add(btnAccueil);

        btnAccueil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    new view_Accueil();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}

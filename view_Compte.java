package view;

import java.awt.Font;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controller.mainMVC;
import model.Adherent;
import model.Livre;
import java.awt.Color;

public class view_Compte {

    private JFrame frame;
    private JTextField textField;

    public view_Compte() throws SQLException, ClassNotFoundException {
        initialize();
        frame.setVisible(true);
    }

    private void initialize() throws SQLException, ClassNotFoundException {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        mainMVC.getM().getall();
        frame.setTitle("Mon Compte");
        frame.setBounds(100, 100, 700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel lblTitre = new JLabel("Mes informations");
        lblTitre.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitre.setBounds(258, 0, 200, 30);
        frame.getContentPane().add(lblTitre);

        JLabel lblNumAd = new JLabel("n° adhérent");
        lblNumAd.setBounds(10, 37, 80, 13);
        frame.getContentPane().add(lblNumAd);

        textField = new JTextField();
        textField.setBounds(86, 34, 96, 19);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JButton btnValider = new JButton("Valider");
        btnValider.setBounds(200, 32, 100, 23);
        frame.getContentPane().add(btnValider);

        JTextField txtNom = new JTextField();
        txtNom.setBounds(80, 34, 200, 20);
        frame.getContentPane().add(txtNom);
        txtNom.setVisible(false);

        JTextField txtPrenom = new JTextField();
        txtPrenom.setBounds(80, 64, 200, 20);
        frame.getContentPane().add(txtPrenom);
        txtPrenom.setVisible(false);

        JTextField txtEmail = new JTextField();
        txtEmail.setBounds(80, 94, 200, 20);
        frame.getContentPane().add(txtEmail);
        txtEmail.setVisible(false);

        JLabel lblNom = new JLabel("Nom :");
        lblNom.setBounds(10, 34, 70, 20);
        frame.getContentPane().add(lblNom);
        lblNom.setVisible(false);

        JLabel lblPrenom = new JLabel("Prénom :");
        lblPrenom.setBounds(10, 64, 70, 20);
        frame.getContentPane().add(lblPrenom);
        lblPrenom.setVisible(false);

        JLabel lblEmail = new JLabel("Email :");
        lblEmail.setBounds(10, 94, 70, 20);
        frame.getContentPane().add(lblEmail);
        lblEmail.setVisible(false);

        JButton btnMAJ = new JButton("Mettre à jour");
        btnMAJ.setBounds(20, 124, 130, 25);
        frame.getContentPane().add(btnMAJ);
        btnMAJ.setVisible(false);

        JLabel lblLivres = new JLabel("Mes livres empruntés :");
        lblLivres.setFont(new Font("Arial", Font.BOLD, 14));
        lblLivres.setBounds(258, 156, 200, 25);
        frame.getContentPane().add(lblLivres);
        lblLivres.setVisible(false);

        String[] colonnes = {"ISBN", "Titre", "Auteur"};
        DefaultTableModel model = new DefaultTableModel(colonnes, 0);
        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(20);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(34, 192, 614, 86);
        frame.getContentPane().add(scrollPane);
        scrollPane.setVisible(false);

        JButton btnAccueil = new JButton("ACCUEIL");
        btnAccueil.setFont(new Font("Arial", Font.BOLD, 16));
        btnAccueil.setBounds(269, 318, 141, 35);
        frame.getContentPane().add(btnAccueil);
        btnAccueil.setVisible(false);

        JButton btnEmprunt = new JButton("EMPRUNTER");
        btnEmprunt.setFont(new Font("Arial", Font.BOLD, 16));
        btnEmprunt.setBounds(34, 288, 175, 40);
        frame.getContentPane().add(btnEmprunt);
        btnEmprunt.setVisible(false);

        JButton btnRestitution = new JButton("RENDRE UN LIVRE");
        btnRestitution.setFont(new Font("Arial", Font.BOLD, 16));
        btnRestitution.setBounds(448, 288, 200, 40);
        frame.getContentPane().add(btnRestitution);
        btnRestitution.setVisible(false);

        // qd on appuie sur le boutton valider
        Runnable afficherInfos = () -> {
            try {
                mainMVC.getM().getall();
                int numAd = Integer.parseInt(textField.getText().trim());
                Adherent ad = mainMVC.getM().getListAdherent()
                        .stream()
                        .filter(a -> a.getNum() == numAd)
                        .findFirst()
                        .orElse(null);

                if (ad != null) {
                    txtNom.setText(ad.getNom());
                    txtPrenom.setText(ad.getPrenom());
                    txtEmail.setText(ad.getEmail());

                    model.setRowCount(0);
                    if (ad.getListLivres() != null && !ad.getListLivres().isEmpty()) {
                        for (Livre l : ad.getListLivres()) {
                            model.addRow(new Object[]{l.getISBN(), l.getTitre(), l.getAuteur().getNom()});
                        }
                    } else {
                        model.addRow(new Object[]{"-", "Aucun livre emprunté", "-"});
                    }

                    lblNom.setVisible(true);
                    lblPrenom.setVisible(true);
                    lblEmail.setVisible(true);
                    txtNom.setVisible(true);
                    txtPrenom.setVisible(true);
                    txtEmail.setVisible(true);
                    lblLivres.setVisible(true);
                    scrollPane.setVisible(true);
                    btnAccueil.setVisible(true);
                    btnEmprunt.setVisible(true);
                    btnRestitution.setVisible(true);
                    btnMAJ.setVisible(true);
                    btnValider.setVisible(false);
                    textField.setVisible(false);
                    lblNumAd.setVisible(false);

                    frame.revalidate();
                    frame.repaint();

                    btnMAJ.addActionListener(ev -> {
                        try {
                            ad.setNom(txtNom.getText().trim());
                            ad.setPrenom(txtPrenom.getText().trim());
                            ad.setEmail(txtEmail.getText().trim());
                            mainMVC.getM().updateAdherent(ad);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    });
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };

        
        textField.addActionListener(e -> afficherInfos.run());
        btnValider.addActionListener(e -> afficherInfos.run());

        btnAccueil.addActionListener(e -> {
            try {
                mainMVC.getM().getall();
                frame.dispose();
                new view_Accueil();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnEmprunt.addActionListener(e -> {
            frame.dispose();
            try {
                new view_Emprunt();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btnRestitution.addActionListener(e -> {
            frame.dispose();
            try {
                new view_Restitution();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}

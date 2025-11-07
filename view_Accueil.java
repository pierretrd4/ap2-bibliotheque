package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.mainMVC;
import javax.swing.ImageIcon;
import java.awt.Color;

public class view_Accueil {

    private JFrame frame;

    public view_Accueil() throws SQLException, ClassNotFoundException {
        mainMVC.getM().getall();
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setTitle("Accueil - Bibliothèque");
        frame.setBounds(100, 100, 700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

        // TITRE
        JLabel lblTitre = new JLabel("ACCUEIL");
        lblTitre.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitre.setBounds(296, 0, 103, 61);
        frame.getContentPane().add(lblTitre);

        // BOUTONS PRINCIPAUX
     // 🏠 CATALOGUE
        JButton btnCatalogue = new JButton("CATALOGUE");
        btnCatalogue.setIcon(new ImageIcon("C:\\\\Users\\\\Pierre\\\\Downloads\\\\transparent-Photoroom (6).png")); // ✅ ton image ici
        btnCatalogue.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        btnCatalogue.setBounds(193, 98, 138, 178);
        btnCatalogue.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCatalogue.setVerticalTextPosition(SwingConstants.CENTER);
        btnCatalogue.setForeground(Color.BLACK);
        btnCatalogue.setBorderPainted(false);
        btnCatalogue.setFocusPainted(false);
        btnCatalogue.setContentAreaFilled(false);
        btnCatalogue.setOpaque(false);
        btnCatalogue.setRolloverEnabled(false);
        btnCatalogue.setPressedIcon(btnCatalogue.getIcon());
        frame.getContentPane().add(btnCatalogue);


        // 👤 MON COMPTE
        JButton btnComptes = new JButton("MON COMPTE");
        btnComptes.setIcon(new ImageIcon("C:\\\\Users\\\\Pierre\\\\Downloads\\\\transparent-Photoroom (6).png")); // ✅ ton image ici
        btnComptes.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        btnComptes.setBounds(21, 98, 155, 178);
        btnComptes.setHorizontalTextPosition(SwingConstants.CENTER);
        btnComptes.setVerticalTextPosition(SwingConstants.CENTER);
        btnComptes.setForeground(Color.BLACK);
        btnComptes.setBorderPainted(false);
        btnComptes.setFocusPainted(false);
        btnComptes.setContentAreaFilled(false);
        btnComptes.setOpaque(false);
        btnComptes.setRolloverEnabled(false);
        btnComptes.setPressedIcon(btnComptes.getIcon());
        frame.getContentPane().add(btnComptes);


        // 📚 EMPRUNT
        JButton btnEmprunt = new JButton("EMPRUNT");
        btnEmprunt.setIcon(new ImageIcon("C:\\Users\\Pierre\\Downloads\\transparent-Photoroom (6).png"));
        btnEmprunt.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        btnEmprunt.setBounds(360, 98, 138, 178);
        btnEmprunt.setHorizontalTextPosition(SwingConstants.CENTER);
        btnEmprunt.setVerticalTextPosition(SwingConstants.CENTER);
        btnEmprunt.setForeground(Color.BLACK);
        btnEmprunt.setBorderPainted(false);
        btnEmprunt.setFocusPainted(false);
        btnEmprunt.setContentAreaFilled(false);
        btnEmprunt.setOpaque(false);
        btnEmprunt.setRolloverEnabled(false);
        btnEmprunt.setPressedIcon(btnEmprunt.getIcon());
        frame.getContentPane().add(btnEmprunt);


        // 🔄 RESTITUTION
        JButton btnRestitution = new JButton("RESTITUTION");
        btnRestitution.setIcon(new ImageIcon("C:\\\\Users\\\\Pierre\\\\Downloads\\\\transparent-Photoroom (6).png")); // ✅ ton image ici
        btnRestitution.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        btnRestitution.setBounds(523, 98, 138, 178);
        btnRestitution.setHorizontalTextPosition(SwingConstants.CENTER);
        btnRestitution.setVerticalTextPosition(SwingConstants.CENTER);
        btnRestitution.setForeground(Color.BLACK);
        btnRestitution.setBorderPainted(false);
        btnRestitution.setFocusPainted(false);
        btnRestitution.setContentAreaFilled(false);
        btnRestitution.setOpaque(false);
        btnRestitution.setRolloverEnabled(false);
        btnRestitution.setPressedIcon(btnRestitution.getIcon());
        frame.getContentPane().add(btnRestitution);



        // lien vers les autres vues
        btnCatalogue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    new view_Catalogue();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnComptes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    new view_Compte();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnEmprunt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    new view_Emprunt();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnRestitution.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    new view_Restitution();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}


package view;

import java.awt.Font;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import controller.mainMVC;
import model.Adherent;
import model.Livre;
import java.awt.Color;

public class view_Restitution {

    private JFrame frame;
    private JTable table;
    private JTextField txtNumAdherent;
    private JTextField txtISBN;
    private JLabel lblISBN;
    private JButton btnRestituer;
    private JScrollPane scrollPane;
    private JButton btnAccueil;
    private JLabel lblTitre;
    private DefaultTableModel modelTable;
    private TableRowSorter<DefaultTableModel> sorter;

    public view_Restitution() throws SQLException, ClassNotFoundException {
        initialize();
        frame.setVisible(true);
    }

    private void initialize() throws SQLException, ClassNotFoundException {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        mainMVC.getM().getall(); 
        frame.setTitle("Restitution des livres");
        frame.setBounds(100, 100, 700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

        lblTitre = new JLabel("Restitution de livres");
        lblTitre.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitre.setBounds(259, 20, 250, 30);
        frame.getContentPane().add(lblTitre);
     
        JLabel lblNumAd = new JLabel("Numéro adhérent :");
        lblNumAd.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNumAd.setBounds(180, 80, 150, 25);
        frame.getContentPane().add(lblNumAd);

        txtNumAdherent = new JTextField();
        txtNumAdherent.setBounds(340, 80, 120, 25);
        frame.getContentPane().add(txtNumAdherent);

        JButton btnValider = new JButton("Valider");
        btnValider.setFont(new Font("Arial", Font.PLAIN, 14));
        btnValider.setBounds(486, 78, 120, 30);
        frame.getContentPane().add(btnValider);

        lblISBN = new JLabel("Filtrer par ISBN :");
        lblISBN.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblISBN.setBounds(81, 80, 130, 30);
        lblISBN.setVisible(false);
        frame.getContentPane().add(lblISBN);

        txtISBN = new JTextField();
        txtISBN.setBounds(211, 80, 150, 30);
        txtISBN.setVisible(false);
        frame.getContentPane().add(txtISBN);

        btnRestituer = new JButton("RESTITUER");
        btnRestituer.setFont(new Font("Arial", Font.BOLD, 16));
        btnRestituer.setBounds(514, 286, 140, 35);
        btnRestituer.setVisible(false);
        frame.getContentPane().add(btnRestituer);

        btnAccueil = new JButton("ACCUEIL");
        btnAccueil.setFont(new Font("Arial", Font.BOLD, 14));
        btnAccueil.setBounds(281, 323, 140, 30);
        btnAccueil.setVisible(false);
        frame.getContentPane().add(btnAccueil);

        
        String[] colonnes = {"ISBN", "Titre", "Auteur"};
        modelTable = new DefaultTableModel(colonnes, 0);
        table = new JTable(modelTable);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(20);

        sorter = new TableRowSorter<>(modelTable);
        table.setRowSorter(sorter);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(54, 123, 600, 153);
        scrollPane.setVisible(false);
        frame.getContentPane().add(scrollPane);

        
        btnValider.addActionListener(e -> {
            try {
                int num = Integer.parseInt(txtNumAdherent.getText());
                Adherent ad = mainMVC.getM().findAdherent(num);

                if (ad != null) {
                    lblISBN.setVisible(true);
                    txtISBN.setVisible(true);
                    btnRestituer.setVisible(true);
                    btnAccueil.setVisible(true);
                    scrollPane.setVisible(true);
                    lblNumAd.setVisible(false);
                    txtNumAdherent.setVisible(false);
                    btnValider.setVisible(false);

                    // que les livres emprruntes par cet adherent
                    modelTable.setRowCount(0);
                    for (Livre l : ad.getListLivres()) {
                        String auteur = (l.getAuteur() != null) ? l.getAuteur().getNom() : "—";
                        modelTable.addRow(new Object[]{l.getISBN(), l.getTitre(), auteur});
                    }

                    
                    txtISBN.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            String filter = txtISBN.getText();
                            if (filter.trim().length() == 0) {
                                sorter.setRowFilter(null);
                            } else {
                                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filter, 0));
                            }
                        }
                    });

                    
                    btnRestituer.addActionListener(ev -> {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            int modelRow = table.convertRowIndexToModel(selectedRow);
                            int isbn = Integer.parseInt(modelTable.getValueAt(modelRow, 0).toString());
                            try {
                                mainMVC.getM().restituerLivre(isbn); 
                                mainMVC.getM().getall();
                                modelTable.setRowCount(0);
                                for (Livre l : ad.getListLivres()) {
                                    String auteur = (l.getAuteur() != null) ? l.getAuteur().getNom() : "—";
                                    modelTable.addRow(new Object[]{l.getISBN(), l.getTitre(), auteur});
                                }
                                System.out.println("Livre restitué !");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });

                    btnAccueil.addActionListener(ev -> {
                        frame.dispose();
                        try {
                            new view_Accueil();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });

                } else {
                    System.out.println("Aucun adhérent trouvé pour ce numéro !");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}

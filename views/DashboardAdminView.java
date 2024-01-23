package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Controllers.DetailTransaksiController;
import Controllers.BukuController;
import Controllers.RootController;
import Entities.DetailTransaksiEntity;
import Entities.BukuEntity;

public class DashboardAdminView extends JFrame {

    private BukuController bukuController;
    private DetailTransaksiController detailTransaksiController;
    private bukuTableModel tableModel;
    private DetailTransaksiTableModel tableModel2;
    private JTable bukuTable;
    private JTable detailTransaksiTable;

    private JTextField genreField;
    private JTextField judulField;
    private JTextField penerbitField;
    private JTextField tahunField;
    private JCheckBox stokCheckbox;

    public DashboardAdminView(String username) {
        setTitle("Dashboard Admin");
        setSize(800, 600);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel lblUsername = new JLabel("Username: " + username);
        JLabel noted = new JLabel("Ubah tahun menjadi penyewa saat transkip");
        add(lblUsername);


        bukuController = new BukuController();
        ArrayList<BukuEntity> bukus = bukuController.read();
        detailTransaksiController = new DetailTransaksiController();
        ArrayList<DetailTransaksiEntity> detailTransaksiEntities = detailTransaksiController.read();
        tableModel2 = new DetailTransaksiTableModel(detailTransaksiEntities);
        tableModel = new bukuTableModel(bukus);
        bukuTable = new JTable(tableModel);
        detailTransaksiTable = new JTable(tableModel2);
        JScrollPane scrollPane2 = new JScrollPane(detailTransaksiTable);
        add(scrollPane2);
        JScrollPane scrollPane = new JScrollPane(bukuTable);
        add(scrollPane);

        genreField = new JTextField();
        judulField = new JTextField();
        penerbitField = new JTextField();
        tahunField = new JTextField();
        stokCheckbox = new JCheckBox("Stok");

        JButton btnAdd = new JButton("Add Buku");
        JButton btnEdit = new JButton("Edit Buku");
        JButton btnDelete = new JButton("Delete Buku");
        JButton btnhis = new JButton("Transkip");

 bukuTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = bukuTable.getSelectedRow();
            if (selectedRow != -1) {
                // Get the selected buku
                BukuEntity selectedBuku = tableModel.getBukuAt(selectedRow);

                // Update input fields with selected buku data
                genreField.setText(selectedBuku.getGenre());
                judulField.setText(selectedBuku.getJudul());
                penerbitField.setText(selectedBuku.getPenerbit());
                tahunField.setText(selectedBuku.getTahun());
                stokCheckbox.setSelected(selectedBuku.isStok());
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBuku();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editBuku();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBuku();
            }
        });

        //hiss
        btnhis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //gantiiki
                buathistory();
            }
        });

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        JPanel logoutPanel = new JPanel();
        logoutPanel.add(logoutButton);
        add(logoutPanel);
        add(noted);
    
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(new JLabel("Genre: "));
        inputPanel.add(genreField);
        inputPanel.add(new JLabel("Judul: "));
        inputPanel.add(judulField);
        inputPanel.add(new JLabel("Penerbit: "));
        inputPanel.add(penerbitField);
        inputPanel.add(new JLabel("Tahun: "));
        inputPanel.add(tahunField);
        inputPanel.add(stokCheckbox);
        add(inputPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnhis); //his
        add(buttonPanel);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

   private void logout() {
        try {
            new RootController().loginPage();
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     private void addBuku() {
        try {
            String genre = genreField.getText();
            String judul = judulField.getText();
            String penerbit = penerbitField.getText();
            String tahun = tahunField.getText();
            boolean stok = stokCheckbox.isSelected();
            bukuController.create(genre, judul, penerbit, tahun, stok);
            tableModel.fireTableDataChanged();
            clearInputFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //his
    
    //end his

private void editBuku() {
    try {
            int selectedRow = bukuTable.getSelectedRow();
        if (selectedRow != -1) {
            // Get the modified data from input fields
            String genre = genreField.getText();
            String judul = judulField.getText();
            String penerbit = penerbitField.getText();
            String tahun = tahunField.getText();
            boolean stok = stokCheckbox.isSelected();

            bukuController.update(selectedRow, genre, judul, penerbit, tahun, stok);
            tableModel.fireTableDataChanged();

            // Clear input fields
            clearInputFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a buku to edit.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
  
    }
    private void deleteBuku() {
        int selectedRow = bukuTable.getSelectedRow();
        if (selectedRow != -1) {
            bukuController.delete(selectedRow);
            tableModel.fireTableDataChanged();
            clearInputFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a buku to delete.");
        }
    }

    private void clearInputFields() {
        genreField.setText("");
        judulField.setText("");
        penerbitField.setText("");
        tahunField.setText("");
        stokCheckbox.setSelected(false);
    }

    private class bukuTableModel extends AbstractTableModel {
        private ArrayList<BukuEntity> bukus;
        private String[] columnNames = {"Genre", "Judul", "Penerbit", "Tahun", "stok"};

        public bukuTableModel(ArrayList<BukuEntity> bukus) {
            this.bukus = bukus;
        }

        @Override
        public int getRowCount() {
            return bukus.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

         public BukuEntity getBukuAt(int index) {
        return bukus.get(index);
    }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            BukuEntity buku = bukus.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return buku.getGenre();
                case 1:
                    return buku.getJudul();
                case 2:
                    return buku.getPenerbit();
                case 3:
                    return buku.getTahun();
                case 4:
                    return buku.isStok();
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }

     private class DetailTransaksiTableModel extends AbstractTableModel {
        private ArrayList<DetailTransaksiEntity> detailTransaksis;
        private String[] columnNames = {"Username", "Judul", "Penerbit", "tahun", "stok"};

        public DetailTransaksiTableModel(ArrayList<DetailTransaksiEntity> detailTransaksis) {
            this.detailTransaksis = detailTransaksis;
        }

        @Override
        public int getRowCount() {
            return detailTransaksis.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

         public DetailTransaksiEntity getBukuAt(int index) {
        return detailTransaksis.get(index);
    }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            DetailTransaksiEntity buku = detailTransaksis.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return buku.getUserEntity().getUsername();
                case 1:
                    return buku.getBukuEntity().getJudul();
                case 2:
                    return buku.getBukuEntity().getPenerbit();
                case 3:
                    return buku.getBukuEntity().getTahun();
                case 4:
                    return buku.getBukuEntity().isStok();
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }

    

    private void buathistory() {
        try {
            File historyFile = new File("/home/superbia/Downloads/history.txt");
    
            // Additional file path handling if needed
    
            try (FileWriter writer = new FileWriter(historyFile, true)) {
                int selectedRow = bukuTable.getSelectedRow();
                if (selectedRow != -1) {
                    BukuEntity selectedBuku = tableModel.getBukuAt(selectedRow);
    
                    // Write the history information to the file
                    writer.write("\nAction: Viewed Buku");
                    writer.write("\nGenre: " + selectedBuku.getGenre());
                    writer.write("\nJudul: " + selectedBuku.getJudul());
                    writer.write("\nPenerbit: " + selectedBuku.getPenerbit());
                    writer.write("\nTahun: " + selectedBuku.getTahun());
                    writer.write("\nStok: " + selectedBuku.isStok());
                    writer.write("\n\n");
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a buku to view history.");
                }
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the file: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}




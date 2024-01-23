package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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

import Controllers.DetailTransaksiController;
import Controllers.BukuController;
import Controllers.RootController;
import Entities.BukuEntity;
import Entities.UserEntity;

class BukuTableModel extends AbstractTableModel {
    private ArrayList<BukuEntity> buku = new ArrayList<>();
    private ArrayList<BukuEntity> filteredBuku = new ArrayList<>();
    private String[] columnNames = {"Genre", "Judul", "Penerbit", "Tahun", "Stok"};
    private boolean isSearchEmpty = false;

    public BukuTableModel(ArrayList<BukuEntity> buku) {
        this.buku = buku;
        updateFilteredBuku();
    }

    private void updateFilteredBuku() {
        filteredBuku.clear();
        for (BukuEntity buku : buku) {
            if (buku.isStok()) {
                filteredBuku.add(buku);
            }
        }
        fireTableDataChanged();
    }

    public BukuEntity getbukuAt (int index){
        return filteredBuku.get(index);
    }

    @Override
    public int getRowCount() {
        if (isSearchEmpty) {
            // Display a single row for the message when the search result is empty
            return 1;
        } else {
            return filteredBuku.size();
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (isSearchEmpty) {
            return "Maaf judul buku yang anda cari tidak ada";
        }

        BukuEntity buku = filteredBuku.get(rowIndex);
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

    // Helper method to filter buku based on the entered title
   public void filterByTitle(String title) {
        try {
            isSearchEmpty = true;
            filteredBuku.clear();

            for (BukuEntity buku : buku) {
                if (buku.isStok() && buku.getJudul().toLowerCase().contains(title.toLowerCase())) {
                    filteredBuku.add(buku);
                    isSearchEmpty = false;
                }
            }

            fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class DashboardUserView extends JFrame {
    
    private BukuController bukuController;
    private BukuTableModel tableModel;
    JTable bukuTable;

    public DashboardUserView(String username) {
        setTitle("Dashboard User");
        setSize(800, 600);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel lblUsername = new JLabel("Username: " + username);
        add(lblUsername);

        bukuController = new BukuController();
        ArrayList<BukuEntity> buku = bukuController.read();

        tableModel = new BukuTableModel(buku);
        bukuTable = new JTable(tableModel);

        JTextField searchField = new JTextField();
        searchField.setColumns(10);
        JButton searchButton = new JButton("Search");

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSearch();
            }

            private void updateSearch() {
                String searchText = searchField.getText();
                tableModel.filterByTitle(searchText);
            }
        });
 
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            tableModel.filterByTitle(searchText);
        });

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search Title: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel);
        JButton Pinjam = new JButton("pinjam");
  

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(Pinjam);
        add(buttonPanel);
        JScrollPane scrollPane = new JScrollPane(bukuTable);
        add(scrollPane);

        JTabbedPane tabbedPane = new JTabbedPane();
        add(tabbedPane);

      JButton logoutButton = new JButton("Logout");

        JPanel logoutPanel = new JPanel();
        logoutPanel.add(logoutButton);
        add(logoutPanel);

           try {
            Pinjam.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        bookTicket(username);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        logout();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            // ... (unchanged)
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

          private void logout() {
         try {
          
            setVisible(false);
            new RootController().loginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

      private void bookTicket(String username) {
        try {
            int selectedRow = bukuTable.getSelectedRow();
            if (selectedRow != -1) {
                BukuEntity selectedBuku = tableModel.getbukuAt(selectedRow);
                DetailTransaksiController detailTransaksiController = new DetailTransaksiController();
                detailTransaksiController.create(new BukuEntity(selectedBuku.getGenre(), selectedBuku.getJudul(), selectedBuku.getPenerbit(), selectedBuku.getTahun(), selectedBuku.isStok()), new UserEntity(username));
                showBookingConfirmation(selectedBuku);
            } else {
                JOptionPane.showMessageDialog(this, "Silahkan pilih buku.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showBookingConfirmation(BukuEntity buku) {
        try {
            // JOptionPane.showMessageDialog(this, "pinjam buku dengan judul : " + buku.getJudul() + buku.getFinish());
            // JOptionPane.showMessageDialog(this, "waktu : " + buku.getFinish());

            //waktu
            String judul = buku.getJudul();
        String finishDate = buku.getFinish();

        // Constructing the output message
        StringBuilder message = new StringBuilder();
        message.append("Buku dengan judul: ").append(judul).append("\n");
        message.append("Tanggal pengembalian: ").append(finishDate).append("\n");

        // Display the formatted message
        JOptionPane.showMessageDialog(this, message.toString(), "Booking Confirmation", JOptionPane.INFORMATION_MESSAGE);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



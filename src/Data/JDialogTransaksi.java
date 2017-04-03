/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import util.DbConn;

/**
 *
 * @author COMPUTER
 */
public class JDialogTransaksi extends javax.swing.JDialog {

    private Connection conn;

    /**
     * Creates new form JDialogTransaksi
     */
    public JDialogTransaksi(java.awt.Frame parent, boolean modal, Connection conn) {
        super(parent, modal);
        this.conn = conn;
        initComponents();
        databaseConnection();
        loadAllDatabase();
        setLocationRelativeTo(null);
    }

<<<<<<< HEAD
    private String getNameByIdPenyewa() throws SQLException {
        String nama = "";
        String sqlPenyewaLookup = "Select nama from datapenyewa p inner join transaksi t "
                + "where p.idpenyewa = t.idpenyewa order by t.idtransaksi; ";

        PreparedStatement pstPenyewaLookup = conn.prepareStatement(sqlPenyewaLookup);
=======
<<<<<<< HEAD
    private String getNamebyIdNama(int idnama) throws SQLException {
        String Name = "";
        String sqlPenyewaLookup = "Select nama from datapenyewa where idnama = ? ;";

        PreparedStatement pstPenyewaLookup = conn.prepareStatement(sqlPenyewaLookup);
        pstPenyewaLookup.setInt(1, idnama);
=======
    private Void getNameByIdPenyewa() throws SQLException {
        String Name = "";
        String sqlPenyewaLookup = "Select nama from datapenyewa p inner join transaksi t where p.idpenyewa = t.idpenyewa order by t.idtransaksi;";

        PreparedStatement pstPenyewaLookup = conn.prepareStatement(sqlPenyewaLookup);
//        pstPenyewaLookup.setInt(1, idpenyewa);
>>>>>>> origin/master
>>>>>>> origin/master

        ResultSet rsPenyewaLookup = pstPenyewaLookup.executeQuery();
        while (rsPenyewaLookup.next()) {
            nama = rsPenyewaLookup.getString("nama");
        }
        return nama;
    }

    private String getJudulBukuByIdBuku() throws SQLException {
        String judulbuku = "";
        String sqlBukuLookup = "Select judulbuku from databuku b inner join transaksi t where b.idbuku = t.idbuku order by t.idtransaksi;";

        PreparedStatement pstBukuLookup = conn.prepareStatement(sqlBukuLookup);

        ResultSet rsBukuLookup = pstBukuLookup.executeQuery();
        while (rsBukuLookup.next()) {
            judulbuku = rsBukuLookup.getString("judulbuku");
        }
        return judulbuku;
    }

    private void loadAllDatabase() {
        try {
            String sql = "SELECT username, idtransaksi, idnama, idbuku"
                    + ", date_format(tanggalpinjam, '%d-%m-%Y') as tanggalpinjam"
                    + ", date_format(tanggalpengembalian, '%d-%m-%Y') as tanggalpengembalian FROM transaksi;";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            ResultSet rs = pstatement.executeQuery();

            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblTransaksi.getModel();
//                    int row = tblTransaksi.getSelectedRow();
                        Object data[] = {
                            rs.getInt("idtransaksi"),
<<<<<<< HEAD
                            rs.getInt("idnama"),
//                            getNamebyIdnama((int)(tblTransaksi.getValueAt(row, 1))),
=======
                            rs.getInt("idpenyewa"),
                            getNameByIdPenyewa(),
>>>>>>> origin/master
                            rs.getInt("idbuku"),
                            getJudulBukuByIdBuku(),
                            rs.getString("tanggalpinjam"),
                            rs.getString("tanggalpengembalian")
                        };
                        tableModel.addRow(data);
                }
            } else {
                util.Sutil.msg(this, "Record Empty");
            }

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDialogTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void databaseConnection() {
        try {
            conn = DriverManager.getConnection(DbConn.JDBC_URL,
                    DbConn.JDBC_USERNAME,
                    DbConn.JDBC_PASSWORD);

            if (conn != null) {
                System.out.println("Connected to DB!\n");
            }
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbxSearch = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblTransaksi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Transaksi", "ID Penyewa", "Nama", "ID Buku", "Judul Buku", "Tanggal Pinjam", "Tanggal Pengembalian"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTransaksi.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblTransaksi);
        tblTransaksi.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Search by : ");

        cbxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID Transaksi", "ID Penyewa", "Nama", "ID Buku", "Judul Buku" }));

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/google_custom_search.png"))); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        searchDataTransaksi();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void searchDataTransaksi() {
        if (cbxSearch.getSelectedItem().equals("ID Transaksi")) {
            if (!txtSearch.getText().trim().equals("")) {
                searchByIdTransaksi();
            } else if (txtSearch.getText().trim().equals("")) {
                loadAllDatabase();
            }
        } else if (cbxSearch.getSelectedItem().equals("ID Penyewa")) {
            if (!txtSearch.getText().trim().equals("")) {
                searchByIdPenyewa();
            } else if (txtSearch.getText().trim().equals("")) {
                loadAllDatabase();
            }
        } else if (cbxSearch.getSelectedItem().equals("Nama")) {
            if (!txtSearch.getText().trim().equals("")) {
//                searchByNama();
            } else if (txtSearch.getText().trim().equals("")) {
                loadAllDatabase();
            }
        } else if (cbxSearch.getSelectedItem().equals("ID Buku")) {
            if (!txtSearch.getText().trim().equals("")) {
                searchByIdBuku();
            } else if (txtSearch.getText().trim().equals("")) {
                loadAllDatabase();
            }
        } else if (cbxSearch.getSelectedItem().equals("Judul Buku")) {
            if (!txtSearch.getText().trim().equals("")) {
//                searchByJudulBuku();
            } else if (txtSearch.getText().trim().equals("")) {
                loadAllDatabase();
            }
        } else {
            util.Sutil.mse(this, "Not found !");
        }
    }

    private void searchByIdTransaksi() {
        try {
            removeTableData();
            String sql = "SELECT * FROM transaksi WHERE idtransaksi LIKE ?";

            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setInt(1, Integer.parseInt(txtSearch.getText().trim()));

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                DefaultTableModel tableModel = (DefaultTableModel) tblTransaksi.getModel();
                while (rs.next()) {
                    Object data[] = {
                        rs.getInt("idtransaksi"),
                        rs.getInt("idpenyewa"),
                        getNameByIdPenyewa(),
                        rs.getInt("idbuku"),
                        getJudulBukuByIdBuku(),
                        rs.getString("judulbuku")
                    };
                    tableModel.addRow(data);
                }
            }

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        }
    }
    
    private void searchByIdPenyewa() {
        try {
            removeTableData();
            String sql = "SELECT * FROM transaksi WHERE idpenyewa LIKE ?";

            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setInt(1, Integer.parseInt(txtSearch.getText().trim()));

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                DefaultTableModel tableModel = (DefaultTableModel) tblTransaksi.getModel();
                while (rs.next()) {
                    Object data[] = {
                        rs.getInt("idtransaksi"),
                        rs.getInt("idpenyewa"),
                        getNameByIdPenyewa(),
                        rs.getInt("idbuku"),
                        getJudulBukuByIdBuku(),
                        rs.getString("judulbuku")
                    };
                    tableModel.addRow(data);
                }
            }

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        }
    }

    private void searchByNama() {
        try {
            removeTableData();
            String sql = "SELECT * FROM transaksi WHERE nama LIKE ?";

            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setString(1, "%" + txtSearch.getText().trim() + "%");

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                DefaultTableModel tableModel = (DefaultTableModel) tblTransaksi.getModel();
                while (rs.next()) {
                    Object data[] = {
                        rs.getInt("idtransaksi"),
                        rs.getInt("idpenyewa"),
                        getNameByIdPenyewa(),
                        rs.getInt("idbuku"),
                        getJudulBukuByIdBuku(),
                        rs.getString("judulbuku")
                    };
                    tableModel.addRow(data);
                }
            }

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        }
    }

    private void searchByIdBuku() {
        try {
            removeTableData();
            String sql = "SELECT * FROM transaksi WHERE idbuku LIKE ?";

            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setString(1, "%" + txtSearch.getText().trim() + "%");

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                DefaultTableModel tableModel = (DefaultTableModel) tblTransaksi.getModel();
                while (rs.next()) {
                    Object data[] = {
                        rs.getInt("idtransaksi"),
                        rs.getInt("idpenyewa"),
                        getNameByIdPenyewa(),
                        rs.getInt("idbuku"),
                        getJudulBukuByIdBuku(),
                        rs.getString("judulbuku")
                    };
                    tableModel.addRow(data);
                }
            }

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        }
    }

    private void searchByJudulBuku() {
        try {
            removeTableData();
            String sql = "SELECT * FROM transaksi WHERE judulbuku LIKE ?";

            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setString(1, "%" + txtSearch.getText().trim() + "%");

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                DefaultTableModel tableModel = (DefaultTableModel) tblTransaksi.getModel();
                while (rs.next()) {
                    Object data[] = {
                        rs.getInt("idtransaksi"),
                        rs.getInt("idpenyewa"),
                        getNameByIdPenyewa(),
                        rs.getInt("idbuku"),
                        getJudulBukuByIdBuku(),
                        rs.getString("judulbuku")
                    };
                    tableModel.addRow(data);
                }
            }

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        }
    }

    private void removeTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) tblTransaksi.getModel();
        tableModel.setRowCount(0);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbxSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

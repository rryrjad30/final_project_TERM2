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
        loadAllDatabase();
        setLocationRelativeTo(null);
    }

    private String getNamebyIdNama(Integer i) throws SQLException {
        String nama = "";
        String sqlPenyewaLookup = "Select nama from datapenyewa p inner join transaksi t where p.idnama = ? and t.idnama = ? order by t.idtransaksi;";

        PreparedStatement pstPenyewaLookup = conn.prepareStatement(sqlPenyewaLookup);
        pstPenyewaLookup.setInt(1, i);
        pstPenyewaLookup.setInt(2, i);

        ResultSet rsPenyewaLookup = pstPenyewaLookup.executeQuery();
        while (rsPenyewaLookup.next()) {
            nama = rsPenyewaLookup.getString("nama");
        }
        return nama;
    }

    private String getJudulBukuByIdBuku(Integer i) throws SQLException {
        String judulbuku = "";
        String sqlBukuLookup = "Select judulbuku from databuku b inner join transaksi t where b.idbuku = ? and t.idbuku = ? order by t.idtransaksi;";

        PreparedStatement pstBukuLookup = conn.prepareStatement(sqlBukuLookup);
        pstBukuLookup.setInt(1, i);
        pstBukuLookup.setInt(2, i);

        ResultSet rsBukuLookup = pstBukuLookup.executeQuery();
        while (rsBukuLookup.next()) {
            judulbuku = rsBukuLookup.getString("judulbuku");
        }
        return judulbuku;
    }

    private void loadAllDatabase() {
        removeTableData();
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
                    Integer idnama = rs.getInt("idnama");
                    Integer idbuku = rs.getInt("idbuku");
                    Object data[] = {
                        rs.getInt("idtransaksi"),
                        rs.getInt("idnama"),
                        getNamebyIdNama(idnama),
                        rs.getInt("idbuku"),
                        getJudulBukuByIdBuku(idbuku),
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
                "ID Transaksi", "ID Nama", "Nama", "ID Buku", "Judul Buku", "Tanggal Pinjam", "Tanggal Pengembalian"
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

        cbxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "ID Transaksi", "ID Nama", "Nama", "ID Buku", "Judul Buku" }));

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

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

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void searchDataTransaksi() {
        if (cbxSearch.getSelectedItem().equals("All")) {
            loadAllDatabase();
        } else if (cbxSearch.getSelectedItem().equals("ID Transaksi")) {
            if (!txtSearch.getText().trim().equals("")) {
                searchByIdTransaksi();
            } else if (txtSearch.getText().trim().equals("")) {
                util.Sutil.mse(this, "Field must not empty !");
            }
        } else if (cbxSearch.getSelectedItem().equals("ID Nama")) {
            if (!txtSearch.getText().trim().equals("")) {
                searchByIdNama();
            } else if (txtSearch.getText().trim().equals("")) {
                util.Sutil.mse(this, "Field must not empty !");
            }
        } else if (cbxSearch.getSelectedItem().equals("Nama")) {
            if (!txtSearch.getText().trim().equals("")) {
                searchByNama();
            } else if (txtSearch.getText().trim().equals("")) {
                util.Sutil.mse(this, "Field must not empty !");
            }
        } else if (cbxSearch.getSelectedItem().equals("ID Buku")) {
            if (!txtSearch.getText().trim().equals("")) {
                searchByIdBuku();
            } else if (txtSearch.getText().trim().equals("")) {
                util.Sutil.mse(this, "Field must not empty !");
            }
        } else if (cbxSearch.getSelectedItem().equals("Judul Buku")) {
            if (!txtSearch.getText().trim().equals("")) {
                searchByJudulBuku();
            } else if (txtSearch.getText().trim().equals("")) {
                util.Sutil.mse(this, "Field must not empty !");
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
                    Integer idnama = rs.getInt("idnama");
                    Integer idbuku = rs.getInt("idbuku");
                    Object data[] = {
                        rs.getInt("idtransaksi"),
                        rs.getInt("idnama"),
                        getNamebyIdNama(idnama),
                        rs.getInt("idbuku"),
                        getJudulBukuByIdBuku(idbuku),
                        rs.getString("tanggalpinjam"),
                        rs.getString("tanggalpengembalian")
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

    private void searchByIdTransaksi(Integer[] a) {
        PreparedStatement pstatement = null;
        ResultSet rs = null;
        try {
            removeTableData();
            String sql = "SELECT * FROM transaksi WHERE idtransaksi LIKE ?";
            for (Integer integer : a) {
                System.out.println(integer);
                pstatement = conn.prepareStatement(sql);
                pstatement.setInt(1, integer);
                rs = pstatement.executeQuery();
                if (rs.isBeforeFirst()) { // check is resultset not empty
                    DefaultTableModel tableModel = (DefaultTableModel) tblTransaksi.getModel();
                    while (rs.next()) {
                        Integer idnama = rs.getInt("idnama");
                        Integer idbuku = rs.getInt("idbuku");
                        Object data[] = {
                            rs.getInt("idtransaksi"),
                            rs.getInt("idnama"),
                            getNamebyIdNama(idnama),
                            rs.getInt("idbuku"),
                            getJudulBukuByIdBuku(idbuku),
                            rs.getString("tanggalpinjam"),
                            rs.getString("tanggalpengembalian")
                        };
                        tableModel.addRow(data);
                    }
                }
            }

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        } catch (NullPointerException e) {

        }
    }

    private void searchByIdNama() {
        try {
            removeTableData();

            String sql = "SELECT * FROM transaksi WHERE idnama LIKE ?";
            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setInt(1, Integer.parseInt(txtSearch.getText().trim()));
            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                DefaultTableModel tableModel = (DefaultTableModel) tblTransaksi.getModel();
                while (rs.next()) {
                    Integer idnama = rs.getInt("idnama");
                    Integer idbuku = rs.getInt("idbuku");
                    Object data[] = {
                        rs.getInt("idtransaksi"),
                        rs.getInt("idnama"),
                        getNamebyIdNama(idnama),
                        rs.getInt("idbuku"),
                        getJudulBukuByIdBuku(idbuku),
                        rs.getString("tanggalpinjam"),
                        rs.getString("tanggalpengembalian")
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
            Integer results;
            String sql = "select count(*) from transaksi,datapenyewa where datapenyewa.nama like ? and transaksi.idnama = datapenyewa.idnama;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, txtSearch.getText().trim());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                results = rs.getInt("count(*)");
            } else {
                results = 0;
            }
            Integer[] penampung = new Integer[results];
            String sqlPenyewaLookup = "select idtransaksi from transaksi,datapenyewa where datapenyewa.nama like ? and transaksi.idnama = datapenyewa.idnama;";
            PreparedStatement pstPenyewaLookup = conn.prepareStatement(sqlPenyewaLookup);
            pstPenyewaLookup.setString(1, txtSearch.getText().trim());
            Integer a = 0;
            ResultSet rsPenyewaLookup = pstPenyewaLookup.executeQuery();
            while (rsPenyewaLookup.next()) {
                penampung[a] = rsPenyewaLookup.getInt("idtransaksi");
                a++;
            }

            pstPenyewaLookup.close();
            pstmt.close();
            rsPenyewaLookup.close();
            searchByIdTransaksi(penampung);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
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
                    Integer idnama = rs.getInt("idnama");
                    Integer idbuku = rs.getInt("idbuku");
                    Object data[] = {
                        rs.getInt("idtransaksi"),
                        rs.getInt("idnama"),
                        getNamebyIdNama(idnama),
                        rs.getInt("idbuku"),
                        getJudulBukuByIdBuku(idbuku),
                        rs.getString("tanggalpinjam"),
                        rs.getString("tanggalpengembalian")
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
            Integer results;
            String sql = "select count(*) from transaksi,databuku where databuku.judulbuku like ? and transaksi.idbuku = databuku.idbuku;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + txtSearch.getText().trim() + "%");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                results = rs.getInt("count(*)");
            } else {
                results = 0;
            }
            System.out.println(results);
            Integer[] penampung = new Integer[results];
            String sqlBukuLookup = "select idtransaksi from transaksi,databuku where databuku.judulbuku like ? and transaksi.idbuku = databuku.idbuku;";
            PreparedStatement pstBukuLookup = conn.prepareStatement(sqlBukuLookup);
            pstBukuLookup.setString(1, "%" + txtSearch.getText().trim() + "%");
            Integer a = 0;
            ResultSet rsBukuLookup = pstBukuLookup.executeQuery();
            while (rsBukuLookup.next()) {
                penampung[a] = rsBukuLookup.getInt("idtransaksi");
                a++;
            }

            pstBukuLookup.close();
            pstmt.close();
            rsBukuLookup.close();
            searchByIdTransaksi(penampung);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
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

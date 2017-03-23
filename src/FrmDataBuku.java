
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import util.DbConn;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class FrmDataBuku extends javax.swing.JFrame {

    private Connection conn;
    
    /**
     * Creates new form FrmDataBuku
     */
    public FrmDataBuku() {
        initComponents();
        spinnerTahunTerbit();
        databaseConnection();
        loadAllDatabase();
        setLocationRelativeTo(null);
    }
    
    private void spinnerTahunTerbit(){
        SpinnerModel tahunTerbit = new SpinnerNumberModel(1990, 1990, 2017, 1);
        spnTahun.setModel(tahunTerbit);
        NumberEditor editor = new NumberEditor(spnTahun, "#");
        spnTahun.setEditor(editor);
    }
    
    private void loadAllDatabase() {
        try {

            String sql = "SELECT * FROM databuku";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblDataBuku.getModel();

                    Object data[] = {
                        rs.getString("idbuku"),
                        rs.getString("judulbuku"),
                        rs.getString("pengarang"),
                        rs.getString("penerbit"),
                        rs.getInt("tahunterbit"),
                        rs.getLong("isbn"),
                        rs.getString("kategori")
                    };
                    tableModel.addRow(data);
                }
            } else {
                util.Sutil.msg(this, "Record Empty");
            }

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(FrmDataPenyewa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void databaseConnection() {
        try {
            Class.forName(DbConn.JDBC_CLASS);
            conn = DriverManager.getConnection(DbConn.JDBC_URL,
                    DbConn.JDBC_USERNAME,
                    DbConn.JDBC_PASSWORD);

            if (conn != null) {
                System.out.println("Connected to DB!\n");
            }
        } catch (SQLException | ClassNotFoundException ex) {
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

        pnlDataBuku = new javax.swing.JPanel();
        lblIDBuku = new javax.swing.JLabel();
        lblJudulBuku = new javax.swing.JLabel();
        lblPengarang = new javax.swing.JLabel();
        lblPenerbit = new javax.swing.JLabel();
        lblTahunTerbit = new javax.swing.JLabel();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        txtIDBuku = new javax.swing.JTextField();
        txtJudulBuku = new javax.swing.JTextField();
        txtPengarang = new javax.swing.JTextField();
        txtPenerbit = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDataBuku = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        lblKategoriBuku = new javax.swing.JLabel();
        spnTahun = new javax.swing.JSpinner();
        lblISBN = new javax.swing.JLabel();
        txtISBN = new javax.swing.JTextField();
        lblStok = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnBack1 = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();
        lblLibrary = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        lblBack = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDataBuku.setBackground(new java.awt.Color(255, 255, 255));
        pnlDataBuku.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Buku", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 20))); // NOI18N

        lblIDBuku.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblIDBuku.setText("ID Buku");

        lblJudulBuku.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblJudulBuku.setText("Judul Buku");

        lblPengarang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPengarang.setText("Pengarang");

        lblPenerbit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPenerbit.setText("Penerbit");

        lblTahunTerbit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTahunTerbit.setText("Tahun Terbit");

        lblSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        lblSearch.setText("Search");

        txtIDBuku.setEditable(false);
        txtIDBuku.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtJudulBuku.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtPengarang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtPenerbit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOVEL", "NON- FIKSI", "KOMIK", "EDUKASI" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        tblDataBuku.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblDataBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Buku", "Judul Buku", "Pengarang", "Penerbit", "Tahun Terbit", "Kategori Buku", "ISBN", "Stok", "Keterangan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblDataBuku);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Buku.png"))); // NOI18N

        lblKategoriBuku.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblKategoriBuku.setText("Kategori Buku");

        spnTahun.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        spnTahun.setEditor(new javax.swing.JSpinner.NumberEditor(spnTahun, "0000"));

        lblISBN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblISBN.setText("ISBN");

        txtISBN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lblStok.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblStok.setText("Stok");

        txtStok.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSearch.setText("Search");

        btnCreate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add.png"))); // NOI18N
        btnCreate.setText("Create");

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/update.png"))); // NOI18N
        btnUpdate.setText("Update");

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        btnDelete.setText("Delete");

        btnBack1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBack1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/undo.png"))); // NOI18N
        btnBack1.setText("Menu");
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });

        btnNew.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/new.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDataBukuLayout = new javax.swing.GroupLayout(pnlDataBuku);
        pnlDataBuku.setLayout(pnlDataBukuLayout);
        pnlDataBukuLayout.setHorizontalGroup(
            pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlDataBukuLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblKategoriBuku)
                    .addComponent(lblPenerbit)
                    .addComponent(lblPengarang)
                    .addComponent(lblJudulBuku)
                    .addComponent(lblIDBuku)
                    .addComponent(lblStok)
                    .addComponent(lblISBN)
                    .addComponent(lblTahunTerbit))
                .addGap(18, 18, 18)
                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDataBukuLayout.createSequentialGroup()
                        .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPengarang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtJudulBuku, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPenerbit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spnTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtIDBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataBukuLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133))
                    .addGroup(pnlDataBukuLayout.createSequentialGroup()
                        .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch)))
                .addContainerGap())
        );
        pnlDataBukuLayout.setVerticalGroup(
            pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataBukuLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDataBukuLayout.createSequentialGroup()
                        .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(pnlDataBukuLayout.createSequentialGroup()
                        .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDataBukuLayout.createSequentialGroup()
                                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtIDBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblIDBuku))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtJudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblJudulBuku))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPengarang))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPenerbit))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(spnTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTahunTerbit))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblKategoriBuku))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblISBN)))
                            .addGroup(pnlDataBukuLayout.createSequentialGroup()
                                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)
                                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStok))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(pnlDataBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 123, 1110, 540));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo1.png"))); // NOI18N
        getContentPane().add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 90, 90));

        lblLibrary.setFont(new java.awt.Font("Trajan Pro", 1, 40)); // NOI18N
        lblLibrary.setForeground(new java.awt.Color(255, 255, 255));
        lblLibrary.setText("Book Forest");
        getContentPane().add(lblLibrary, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 660, 50));

        lblAlamat.setFont(new java.awt.Font("Trajan Pro", 1, 20)); // NOI18N
        lblAlamat.setForeground(new java.awt.Color(255, 255, 255));
        lblAlamat.setText("Jalan Kapten Maulana Lubis Aryaduta Hotel lt.1");
        getContentPane().add(lblAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, -1, -1));

        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Library-banner.jpg"))); // NOI18N
        getContentPane().add(lblBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 110));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed
        dispose();
    }//GEN-LAST:event_btnBack1ActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNewActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblIDBuku;
    private javax.swing.JLabel lblISBN;
    private javax.swing.JLabel lblJudulBuku;
    private javax.swing.JLabel lblKategoriBuku;
    private javax.swing.JLabel lblLibrary;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPenerbit;
    private javax.swing.JLabel lblPengarang;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblStok;
    private javax.swing.JLabel lblTahunTerbit;
    private javax.swing.JPanel pnlDataBuku;
    private javax.swing.JSpinner spnTahun;
    private javax.swing.JTable tblDataBuku;
    private javax.swing.JTextField txtIDBuku;
    private javax.swing.JTextField txtISBN;
    private javax.swing.JTextField txtJudulBuku;
    private javax.swing.JTextField txtPenerbit;
    private javax.swing.JTextField txtPengarang;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStok;
    // End of variables declaration//GEN-END:variables
}

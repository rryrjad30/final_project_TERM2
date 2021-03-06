import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

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
    public FrmDataBuku(Connection conn) {
        this.conn = conn;
        initComponents();
        tableSelectionListener();
        loadAllDatabase();
        idBuku();
        setLocationRelativeTo(null);
    }

    public void tableSelectionListener() {
        ListSelectionListener listener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int row = tblDataBuku.getSelectedRow();
                if (row >= 0) {
                    txtIDBuku.setText(tblDataBuku.getValueAt(row, 0).toString());
                    txtJudulBuku.setText(tblDataBuku.getValueAt(row, 1).toString());
                    txtPengarang.setText(tblDataBuku.getValueAt(row, 2).toString());
                    txtPenerbit.setText(tblDataBuku.getValueAt(row, 3).toString());
                    yrcTahunTerbit.setYear((int) tblDataBuku.getValueAt(row, 4));
                    cboKategoriBuku.getModel().setSelectedItem(tblDataBuku.getValueAt(row, 5).toString());
                    txtISBN.setText(tblDataBuku.getValueAt(row, 6).toString());
                }
            }
        };
        tblDataBuku.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblDataBuku.getSelectionModel().addListSelectionListener(listener);
    }
    
     private void idBuku() {
        try {
            String sql = "SELECT max(idbuku) FROM databuku;";
            Integer lastDataValue = 0;
            PreparedStatement pstatement = conn.prepareStatement(sql);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty

                while (rs.next()) {
                    lastDataValue = rs.getInt("max(idbuku)");
                };
                // tableModel.addRow(data);
            } else {
                util.Sutil.msg(this, "Record Empty");
            }
            ++lastDataValue;
            txtIDBuku.setText(lastDataValue.toString());

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(FrmDataBuku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAllDatabase() {
        removeTableData();
        try {

            String sql = "SELECT * FROM databuku;";
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
                        rs.getString("kategori"),
                        rs.getLong("isbn")
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

    private void createDatabase(String judulbuku, String pengarang, String penerbit, String tahunterbit, String kategori, String isbn) {
        try {

            if (conn != null) {
                System.out.println("Connected to DB!\n");

                String sql = "INSERT INTO `databuku` "
                        + "(judulbuku, pengarang, penerbit, tahunterbit, kategori, isbn)"
                        + "VALUES (?,?,?,?,?,?);";

                PreparedStatement pstatement = conn.prepareStatement(sql);
                pstatement.setString(1, judulbuku);
                pstatement.setString(2, pengarang);
                pstatement.setString(3, penerbit);
                pstatement.setString(4, tahunterbit);
                pstatement.setString(5, kategori);
                pstatement.setString(6, isbn);
                
                pstatement.executeUpdate();
                System.out.println("Record insert.");

                pstatement.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        }
    }

    private void updateDatabase(int idbuku, String judulbuku, String pengarang, String penerbit, String tahunterbit, String kategori, String isbn) {
        try {

            if (conn != null) {
                System.out.println("Connected to DB!\n");

                String sql = "UPDATE databuku set "
                        + "judulbuku = '" + judulbuku + "' , "
                        + "pengarang = '" + pengarang + "' , "
                        + "penerbit = '" + penerbit + "' , "
                        + "tahunterbit = '" + tahunterbit + "' , "
                        + "kategori = '" + kategori + "' , "
                        + "isbn = '" + isbn + "' "
                        + "where idbuku = '" + idbuku + "';";

                PreparedStatement pstatement = conn.prepareStatement(sql);

                pstatement.executeUpdate();
                System.out.println("Updated.");

                pstatement.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
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

        lblLibrary = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        pnlDataBuku = new javax.swing.JPanel();
        lblIDBuku = new javax.swing.JLabel();
        lblPengarang = new javax.swing.JLabel();
        lblPenerbit = new javax.swing.JLabel();
        lblTahunTerbit = new javax.swing.JLabel();
        txtIDBuku = new javax.swing.JTextField();
        txtJudulBuku = new javax.swing.JTextField();
        txtPengarang = new javax.swing.JTextField();
        txtPenerbit = new javax.swing.JTextField();
        cboKategoriBuku = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDataBuku = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        lblKategoriBuku = new javax.swing.JLabel();
        lblISBN = new javax.swing.JLabel();
        txtISBN = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnBack1 = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        yrcTahunTerbit = new com.toedter.calendar.JYearChooser();
        btnRefresh = new javax.swing.JButton();
        lblBack = new javax.swing.JLabel();

        lblLibrary.setFont(new java.awt.Font("Trajan Pro", 1, 40)); // NOI18N
        lblLibrary.setForeground(new java.awt.Color(255, 255, 255));
        lblLibrary.setText("Book Forest");

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo1.png"))); // NOI18N

        lblAlamat.setFont(new java.awt.Font("Trajan Pro", 1, 20)); // NOI18N
        lblAlamat.setForeground(new java.awt.Color(255, 255, 255));
        lblAlamat.setText("Jalan Kapten Maulana Lubis Aryaduta Hotel lt.1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDataBuku.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Buku", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 20))); // NOI18N

        lblIDBuku.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblIDBuku.setText("ID Buku");

        lblPengarang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPengarang.setText("Pengarang");

        lblPenerbit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPenerbit.setText("Penerbit");

        lblTahunTerbit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTahunTerbit.setText("Tahun Terbit");

        txtIDBuku.setEditable(false);
        txtIDBuku.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtJudulBuku.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtPengarang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtPenerbit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        cboKategoriBuku.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboKategoriBuku.setMaximumRowCount(99);
        cboKategoriBuku.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOVEL", "NON- FIKSI", "KOMIK", "EDUKASI" }));
        cboKategoriBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKategoriBukuActionPerformed(evt);
            }
        });

        tblDataBuku.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblDataBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Buku", "Judul Buku", "Pengarang", "Penerbit", "Tahun Terbit", "Kategori Buku", "ISBN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
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
        tblDataBuku.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(tblDataBuku);
        tblDataBuku.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Buku.png"))); // NOI18N

        lblKategoriBuku.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblKategoriBuku.setText("Kategori Buku");

        lblISBN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblISBN.setText("ISBN");

        txtISBN.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnCreate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add.png"))); // NOI18N
        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/update.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

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

        yrcTahunTerbit.setEndYear(2017);
        yrcTahunTerbit.setStartYear(1985);

        btnRefresh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow_refresh.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDataBukuLayout = new javax.swing.GroupLayout(pnlDataBuku);
        pnlDataBuku.setLayout(pnlDataBukuLayout);
        pnlDataBukuLayout.setHorizontalGroup(
            pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataBukuLayout.createSequentialGroup()
                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDataBukuLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDataBukuLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblKategoriBuku)
                            .addComponent(lblPenerbit)
                            .addComponent(lblPengarang)
                            .addComponent(lblIDBuku)
                            .addComponent(lblISBN)
                            .addComponent(lblTahunTerbit))
                        .addGap(18, 18, 18)
                        .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboKategoriBuku, 0, 397, Short.MAX_VALUE)
                            .addComponent(txtPenerbit)
                            .addGroup(pnlDataBukuLayout.createSequentialGroup()
                                .addComponent(txtIDBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtJudulBuku))
                            .addComponent(txtPengarang)
                            .addComponent(txtISBN)
                            .addComponent(yrcTahunTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNew, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)))
                .addGap(21, 21, 21))
        );
        pnlDataBukuLayout.setVerticalGroup(
            pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataBukuLayout.createSequentialGroup()
                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDataBukuLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtJudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlDataBukuLayout.createSequentialGroup()
                                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtIDBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblIDBuku))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPengarang, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPengarang))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPenerbit))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTahunTerbit)
                                    .addComponent(yrcTahunTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboKategoriBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblKategoriBuku))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblISBN)))
                            .addGroup(pnlDataBukuLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlDataBukuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addGap(31, 31, 31)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnlDataBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 123, 1020, 470));

        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Tampilan atas.jpg"))); // NOI18N
        getContentPane().add(lblBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 110));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        executeNew();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed
        dispose();
    }//GEN-LAST:event_btnBack1ActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        executeUpdate();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        executeCreate();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void cboKategoriBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKategoriBukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboKategoriBukuActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        loadAllDatabase();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void executeNew() {
        txtIDBuku.setText("");
        idBuku();
        txtJudulBuku.setText("");
        txtPengarang.setText("");
        txtPenerbit.setText("");
        yrcTahunTerbit.setYear(0);
        cboKategoriBuku.setSelectedIndex(0);
        txtISBN.setText("");
    }

    private void executeCreate() {
        createDatabase(txtJudulBuku.getText(), txtPengarang.getText(),
                txtPenerbit.getText(), String.valueOf(yrcTahunTerbit.getYear()),
                (String) (cboKategoriBuku.getSelectedItem()), txtISBN.getText());

        loadAllDatabase();
    }


    private void executeUpdate() {
        updateDatabase(Integer.parseInt(txtIDBuku.getText()), txtJudulBuku.getText(), txtPengarang.getText(),
                txtPenerbit.getText(), String.valueOf(yrcTahunTerbit.getYear()),
                (String) (cboKategoriBuku.getSelectedItem()), txtISBN.getText());

        loadAllDatabase();
    }
    
    private void removeTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) tblDataBuku.getModel();
        tableModel.setRowCount(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboKategoriBuku;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblIDBuku;
    private javax.swing.JLabel lblISBN;
    private javax.swing.JLabel lblKategoriBuku;
    private javax.swing.JLabel lblLibrary;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPenerbit;
    private javax.swing.JLabel lblPengarang;
    private javax.swing.JLabel lblTahunTerbit;
    private javax.swing.JPanel pnlDataBuku;
    private javax.swing.JTable tblDataBuku;
    private javax.swing.JTextField txtIDBuku;
    private javax.swing.JTextField txtISBN;
    private javax.swing.JTextField txtJudulBuku;
    private javax.swing.JTextField txtPenerbit;
    private javax.swing.JTextField txtPengarang;
    private com.toedter.calendar.JYearChooser yrcTahunTerbit;
    // End of variables declaration//GEN-END:variables
}

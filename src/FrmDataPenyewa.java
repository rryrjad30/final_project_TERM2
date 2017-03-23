
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class FrmDataPenyewa extends javax.swing.JFrame {

    private Connection conn;
    private static Integer idNumberNormal = 1;
    private static Integer idNumberSpecial = 1;
    private static Integer idNumberNone = 1;

    /**
     * Creates new form FrmDataPenyewa
     */
    public FrmDataPenyewa() {
        initComponents();
        dateFormat();
        databaseConnection();
        loadAllDatabase();
        setLocationRelativeTo(null);
    }

    private void dateFormat() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        txtTanggalLahir.setText(dateFormat.format(date));
    }

    private void loadAllDatabase() {
        try {

            String sql = "SELECT * FROM datapenyewa";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblPenyewa.getModel();

                    Object data[] = {
                        rs.getString("idpenyewa"),
                        rs.getString("nama"),
                        rs.getString("jeniskelamin"),
                        rs.getString("tempatlahir"),
                        rs.getString("tanggallahir"),
                        rs.getString("nohp"),
                        rs.getString("alamat"),
                        rs.getString("jenispenyewa")
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        pnlDataPenyewa = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPenyewa = new javax.swing.JTable();
        lblNama = new javax.swing.JLabel();
        lblJenisKelamin = new javax.swing.JLabel();
        lblTempatLahir = new javax.swing.JLabel();
        lblHp = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtTempatLahir = new javax.swing.JTextField();
        rdoLaki = new javax.swing.JRadioButton();
        rdoPerempuan = new javax.swing.JRadioButton();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblUserPict = new javax.swing.JLabel();
        lblAlamatPenyewa = new javax.swing.JLabel();
        txtAlamatPenyewa = new javax.swing.JTextField();
        txtIDPenyewa = new javax.swing.JTextField();
        lblNoHp = new javax.swing.JLabel();
        txtNoHP = new javax.swing.JTextField();
        lblJenis = new javax.swing.JLabel();
        rdoNormal = new javax.swing.JRadioButton();
        rdoSpesial = new javax.swing.JRadioButton();
        btnBack = new javax.swing.JButton();
        lblNama1 = new javax.swing.JLabel();
        txtTanggalLahir = new javax.swing.JTextField();
        btnNew = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();
        lblLibrary = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        lblBack = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlDataPenyewa.setBackground(new java.awt.Color(255, 255, 255));
        pnlDataPenyewa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Penyewa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 20))); // NOI18N

        tblPenyewa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblPenyewa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Penyewa", "Nama", "Jenis Kelamin", "Tempat Lahir", "Tanggal Lahir", "No. Hp", "Alamat", "Jenis Penyewa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPenyewa);

        lblNama.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNama.setText("Nama");

        lblJenisKelamin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblJenisKelamin.setText("Jenis Kelamin");

        lblTempatLahir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTempatLahir.setText("Tempat Lahir");

        lblHp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblHp.setText("Tanggal Lahir");

        txtNama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        txtTempatLahir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        buttonGroup1.add(rdoLaki);
        rdoLaki.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoLaki.setText("Laki- Laki");

        buttonGroup1.add(rdoPerempuan);
        rdoPerempuan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoPerempuan.setText("Perempuan");

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

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lblUserPict.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user.png"))); // NOI18N

        lblAlamatPenyewa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblAlamatPenyewa.setText("Alamat");

        txtAlamatPenyewa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtAlamatPenyewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAlamatPenyewaActionPerformed(evt);
            }
        });

        txtIDPenyewa.setEditable(false);
        txtIDPenyewa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtIDPenyewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDPenyewaActionPerformed(evt);
            }
        });

        lblNoHp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNoHp.setText("No. Hp");

        txtNoHP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lblJenis.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblJenis.setText("Jenis Penyewa");

        buttonGroup2.add(rdoNormal);
        rdoNormal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoNormal.setText("Normal");

        buttonGroup2.add(rdoSpesial);
        rdoSpesial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoSpesial.setText("Spesial");

        btnBack.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/undo.png"))); // NOI18N
        btnBack.setText("Menu");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblNama1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNama1.setText("ID_Penyewa");

        btnNew.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/new.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDataPenyewaLayout = new javax.swing.GroupLayout(pnlDataPenyewa);
        pnlDataPenyewa.setLayout(pnlDataPenyewaLayout);
        pnlDataPenyewaLayout.setHorizontalGroup(
            pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                                    .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblNama)
                                        .addComponent(lblJenisKelamin)
                                        .addComponent(lblTempatLahir)
                                        .addComponent(lblNama1))
                                    .addGap(1, 1, 1))
                                .addComponent(lblNoHp, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(lblJenis)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataPenyewaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHp, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblAlamatPenyewa, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIDPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(rdoLaki)
                        .addGap(37, 37, 37)
                        .addComponent(rdoPerempuan))
                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                                .addComponent(rdoNormal)
                                .addGap(50, 50, 50)
                                .addComponent(rdoSpesial))
                            .addComponent(txtNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtAlamatPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(55, 55, 55)
                .addComponent(lblUserPict, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addComponent(jScrollPane1)
        );
        pnlDataPenyewaLayout.setVerticalGroup(
            pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUserPict, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtAlamatPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblAlamatPenyewa)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNoHp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNormal)
                            .addComponent(lblJenis)
                            .addComponent(rdoSpesial)))
                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIDPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNama1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNama)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblJenisKelamin)
                            .addComponent(rdoPerempuan)
                            .addComponent(rdoLaki))
                        .addGap(2, 2, 2)
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblHp)
                                .addComponent(txtTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTempatLahir)
                                    .addComponent(txtTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31))))
                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(pnlDataPenyewa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 1050, 550));

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
        getContentPane().add(lblBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 110));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void txtIDPenyewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDPenyewaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDPenyewaActionPerformed

    private void txtAlamatPenyewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlamatPenyewaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlamatPenyewaActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        toDeleteData();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        toUpdateData();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        toCreateData();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        txtIDPenyewa.setText("");
        txtNama.setText("");
        txtTempatLahir.setText("");
        txtTanggalLahir.setText("");
        txtAlamatPenyewa.setText("");
        txtNoHP.setText("");
    }//GEN-LAST:event_btnNewActionPerformed

    public void createIdPenyewa() {
        //untuk normal atau special
        String normalSpecial = "";
        if (rdoNormal.isSelected()) {
            normalSpecial = "N";
            txtIDPenyewa.setText(normalSpecial + " - " + idNumberNormal);
            idNumberNormal++;
        } else if (rdoSpesial.isSelected()) {
            normalSpecial = "S";
            txtIDPenyewa.setText(normalSpecial + " - " + idNumberSpecial);
            idNumberSpecial++;
        } else {
            normalSpecial = "";
            txtIDPenyewa.setText("None - " + idNumberNone);
            idNumberNone++;
        }
        //--
    }

    public void toCreateData() {
        //untuk gender (jenis kelamin)
        String gender = "";
        if (rdoLaki.isSelected()) {
            gender = rdoLaki.getText();
        } else if (rdoPerempuan.isSelected()) {
            gender = rdoPerempuan.getText();
        } else {
            gender = "";
        }
        //--

        //untuk normal atau special
        String normalspecial = "";
        if (rdoNormal.isSelected()) {
            normalspecial = rdoNormal.getText();
        } else if (rdoSpesial.isSelected()) {
            normalspecial = rdoSpesial.getText();
        } else {
            normalspecial = "";
        }
        //--

        createIdPenyewa();

        Object data[] = {txtIDPenyewa.getText(),
            txtNama.getText(),
            gender,
            txtTempatLahir.getText(),
            txtTanggalLahir.getText(),
            txtAlamatPenyewa.getText(),
            txtNoHP.getText(),
            normalspecial
        };
        DefaultTableModel tableModel = (DefaultTableModel) tblPenyewa.getModel();
        tableModel.addRow(data);
    }

    public void toUpdateData() {
        DefaultTableModel tableModel = (DefaultTableModel) tblPenyewa.getModel();

        int row = tblPenyewa.getSelectedRow();
        if (row >= 0) {
            //untuk gender (jenis kelamin)
            String gender = "";
            if (rdoLaki.isSelected()) {
                gender = rdoLaki.getText();
            } else if (rdoPerempuan.isSelected()) {
                gender = rdoPerempuan.getText();
            } else {
                gender = "";
            }
            //--

            //untuk normal atau special
            String normalspecial = "";
            if (rdoLaki.isSelected()) {
                normalspecial = rdoNormal.getText();
            } else if (rdoPerempuan.isSelected()) {
                normalspecial = rdoSpesial.getText();
            } else {
                normalspecial = "";
            }
            //--

            tableModel.setValueAt(txtIDPenyewa.getText(), row, 0);
            tableModel.setValueAt(txtNama.getText(), row, 1);
            tableModel.setValueAt(gender, row, 2);
            tableModel.setValueAt(txtTempatLahir.getText(), row, 3);
            tableModel.setValueAt(txtTanggalLahir.getText(), row, 4);
            tableModel.setValueAt(txtAlamatPenyewa.getText(), row, 5);
            tableModel.setValueAt(txtNoHP.getText(), row, 6);
            tableModel.setValueAt(normalspecial, row, 7);
        } else {
            System.out.println("Update Error");
        }

    }

    public void toDeleteData() {
        DefaultTableModel tableModel = (DefaultTableModel) tblPenyewa.getModel();
        int row = tblPenyewa.getSelectedRow();
        if (row >= 0) {
            tableModel.removeRow(row);
        } else {
            System.out.println("Delete Error");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblAlamatPenyewa;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblHp;
    private javax.swing.JLabel lblJenis;
    private javax.swing.JLabel lblJenisKelamin;
    private javax.swing.JLabel lblLibrary;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNama1;
    private javax.swing.JLabel lblNoHp;
    private javax.swing.JLabel lblTempatLahir;
    private javax.swing.JLabel lblUserPict;
    private javax.swing.JPanel pnlDataPenyewa;
    private javax.swing.JRadioButton rdoLaki;
    private javax.swing.JRadioButton rdoNormal;
    private javax.swing.JRadioButton rdoPerempuan;
    private javax.swing.JRadioButton rdoSpesial;
    private javax.swing.JTable tblPenyewa;
    private javax.swing.JTextField txtAlamatPenyewa;
    private javax.swing.JTextField txtIDPenyewa;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoHP;
    private javax.swing.JTextField txtTanggalLahir;
    private javax.swing.JTextField txtTempatLahir;
    // End of variables declaration//GEN-END:variables
}


import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LENOVO
 */
public class JdialogDataPenyewa extends javax.swing.JDialog {

    /**
     * Creates new form JdialogDataPenyewa
     */
    public JdialogDataPenyewa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        SpinnerModel tanggal = new SpinnerNumberModel(1,1,31,1);
        SpinnerModel bulan = new SpinnerNumberModel(1,1,12,1);
        SpinnerModel tahun = new SpinnerNumberModel(1,1950,2017,1);
        spiTanggal.setModel(tanggal);
        spiBulan.setModel(bulan);
        spiTahun.setModel(tahun);
        
        setLocationRelativeTo(null);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnGroup = new javax.swing.ButtonGroup();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblNoHp = new javax.swing.JLabel();
        txtNoHP = new javax.swing.JTextField();
        lblJenis = new javax.swing.JLabel();
        btnNormal = new javax.swing.JRadioButton();
        btnSpesial = new javax.swing.JRadioButton();
        btnBack = new javax.swing.JButton();
        spiTanggal = new javax.swing.JSpinner();
        spiBulan = new javax.swing.JSpinner();
        spiTahun = new javax.swing.JSpinner();
        lblLogo = new javax.swing.JLabel();
        lblLibrary = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        lblBack = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
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

        lblNama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNama.setText("Nama");

        lblJenisKelamin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblJenisKelamin.setText("Jenis Kelamin");

        lblTempatLahir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTempatLahir.setText("Tempat Lahir");

        lblHp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblHp.setText("Tanggal Lahir");

        txtNama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        txtTempatLahir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        BtnGroup.add(rdoLaki);
        rdoLaki.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rdoLaki.setText("Laki- Laki");

        BtnGroup.add(rdoPerempuan);
        rdoPerempuan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        rdoPerempuan.setText("Perempuan");

        btnCreate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add.png"))); // NOI18N
        btnCreate.setText("Create");

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/update.png"))); // NOI18N
        btnUpdate.setText("Update");

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        btnDelete.setText("Delete");

        lblUserPict.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user.png"))); // NOI18N

        lblAlamatPenyewa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAlamatPenyewa.setText("Alamat");

        txtAlamatPenyewa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtIDPenyewa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtIDPenyewa.setText("...");

        jLabel1.setText("-");

        jLabel2.setText("-");

        lblNoHp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNoHp.setText("No. Hp");

        txtNoHP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lblJenis.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblJenis.setText("Jenis Penyewa");

        BtnGroup.add(btnNormal);
        btnNormal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnNormal.setText("Normal");

        BtnGroup.add(btnSpesial);
        btnSpesial.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSpesial.setText("Spesial");

        btnBack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/undo.png"))); // NOI18N
        btnBack.setText("Back to Menu");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDataPenyewaLayout = new javax.swing.GroupLayout(pnlDataPenyewa);
        pnlDataPenyewa.setLayout(pnlDataPenyewaLayout);
        pnlDataPenyewaLayout.setHorizontalGroup(
            pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataPenyewaLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNama)
                            .addComponent(lblTempatLahir)
                            .addComponent(lblHp)
                            .addComponent(lblAlamatPenyewa, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNoHp, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblJenis, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNama)
                            .addComponent(txtTempatLahir)
                            .addComponent(txtAlamatPenyewa)
                            .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                                .addComponent(spiTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spiBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spiTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                                .addComponent(btnNormal)
                                .addGap(71, 71, 71)
                                .addComponent(btnSpesial))))
                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                        .addComponent(lblJenisKelamin)
                        .addGap(18, 18, 18)
                        .addComponent(rdoLaki)
                        .addGap(71, 71, 71)
                        .addComponent(rdoPerempuan)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUserPict, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataPenyewaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtIDPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlDataPenyewaLayout.setVerticalGroup(
            pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNama)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblJenisKelamin)
                                    .addComponent(rdoLaki)
                                    .addComponent(rdoPerempuan))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTempatLahir)
                                    .addComponent(txtTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spiTanggal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblHp)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(spiBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spiTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtAlamatPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblAlamatPenyewa))
                                        .addGap(40, 40, 40))
                                    .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNoHp))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataPenyewaLayout.createSequentialGroup()
                                .addComponent(lblUserPict, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)))
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblJenis)
                            .addComponent(btnNormal)
                            .addComponent(btnSpesial)))
                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIDPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        getContentPane().add(pnlDataPenyewa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 128, 1050, 600));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N
        getContentPane().add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 110, 110));

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

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
     dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JdialogDataPenyewa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JdialogDataPenyewa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JdialogDataPenyewa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JdialogDataPenyewa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JdialogDataPenyewa dialog = new JdialogDataPenyewa(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BtnGroup;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JRadioButton btnNormal;
    private javax.swing.JRadioButton btnSpesial;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel lblNoHp;
    private javax.swing.JLabel lblTempatLahir;
    private javax.swing.JLabel lblUserPict;
    private javax.swing.JPanel pnlDataPenyewa;
    private javax.swing.JRadioButton rdoLaki;
    private javax.swing.JRadioButton rdoPerempuan;
    private javax.swing.JSpinner spiBulan;
    private javax.swing.JSpinner spiTahun;
    private javax.swing.JSpinner spiTanggal;
    private javax.swing.JTable tblPenyewa;
    private javax.swing.JTextField txtAlamatPenyewa;
    private javax.swing.JTextField txtIDPenyewa;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoHP;
    private javax.swing.JTextField txtTempatLahir;
    // End of variables declaration//GEN-END:variables
}

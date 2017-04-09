
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author COMPUTER
 */
public class FrmRegister extends javax.swing.JFrame {

    private Connection conn;

    /**
     * Creates new form FrmRegister
     */
    public FrmRegister(Connection conn) {
        this.conn = conn;
        initComponents();
        setLocationRelativeTo(null);
    }

    private void insertToDatabase(String username, String password, String confirmpassword, int active) {
        try {

            String sql = "INSERT INTO `registerdata` "
                    + "(username, password, confirmpassword, active)"
                    + "VALUES (?,?,?,?);";
            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setString(1, username);
            pstatement.setString(2, password);
            pstatement.setString(3, confirmpassword);
            pstatement.setInt(4, active);

            pstatement.executeUpdate();
            System.out.println("Record insert.");

            pstatement.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(FrmDataPenyewa.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPass = new javax.swing.JTextField();
        txtConPass = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnRegister = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        txtConfirmPassword = new javax.swing.JPasswordField();
        txtUsername = new javax.swing.JTextField();
        chkActive = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Register", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(102, 255, 255))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 255, 255));
        jLabel2.setText("Username");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 255, 255));
        jLabel3.setText("Password");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 255, 255));
        jLabel4.setText("Confirm Password");

        txtConPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConPassActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 255, 255));
        jLabel5.setText("Active");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConPass, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtConPass, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(56, 56, 56))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegister.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registerbtn.jpg"))); // NOI18N
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 380, 100, 30));
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, 270, 25));
        getContentPane().add(txtConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 270, 25));
        getContentPane().add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, 270, 25));

        chkActive.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        chkActive.setContentAreaFilled(false);
        chkActive.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkActive.setRequestFocusEnabled(false);
        getContentPane().add(chkActive, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 20, 25));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/register.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtConPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConPassActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        // TODO add your handling code here:
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        String confirmpassword = txtConfirmPassword.getText();
        int active;
        if (chkActive.isSelected()) {
                active = 1;
            } else {
                active = 0;
            }


        if (username.equals("") || password.equals("") || confirmpassword.equals("")) {
            util.Sutil.mse(this, "Field must not be empty !");
        } else if (!password.equals(confirmpassword)) {
            util.Sutil.mse(this, "Password and Confirm Password must be the same !");
        } else if(!username.equals("") && !password.equals("") && !confirmpassword.equals("")
                && password.equals(confirmpassword)){
            insertToDatabase(username, password, confirmpassword, active);
            util.Sutil.msg(this, "Succesfully registered !");
            dispose();
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegister;
    private javax.swing.JCheckBox chkActive;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtConPass;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JTextField txtPass;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}

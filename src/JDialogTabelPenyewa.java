
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import util.DbConn;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author COMPUTER
 */
public class JDialogTabelPenyewa extends javax.swing.JDialog {

    private Connection conn;

    /**
     * Creates new form JDialogTabelPenyewa
     */
    public JDialogTabelPenyewa(java.awt.Frame parent, boolean modal,Connection conn) {
        super(parent, modal);
        this.conn = conn;
        initComponents();
        databaseConnection();
        loadAllDatabase();
        tableSelectionListener();
        setLocationRelativeTo(null);
    }

    public void tableSelectionListener() {
        ListSelectionListener listener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int row = tblPenyewa.getSelectedRow();
                if (row >= 0) {
                    String idNama = tblPenyewa.getValueAt(row, 0).toString();
                    String nama = tblPenyewa.getValueAt(row, 1).toString();
                    FrmMain.pilihDataPenyewa(idNama, nama);
                }
                dispose();
            }
        };
        tblPenyewa.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblPenyewa.getSelectionModel().addListSelectionListener(listener);
    }

    private void loadAllDatabase() {
        try {
            String sql = "SELECT * FROM datapenyewa;";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty
                while (rs.next()) {
                    DefaultTableModel tableModel = (DefaultTableModel) tblPenyewa.getModel();

                    Object data[] = {
                        rs.getString("idnama"),
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
        tblPenyewa = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        tblPenyewa.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblPenyewa);
        tblPenyewa.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPenyewa;
    // End of variables declaration//GEN-END:variables
}


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
public class JDialogTabelBuku extends javax.swing.JDialog {

    private Connection conn;

    /**
     * Creates new form JDialogTabelBuku
     */
    public JDialogTabelBuku(java.awt.Frame parent, boolean modal,Connection conn) {
        super(parent, modal);
        this.conn = conn;
        initComponents();
        databaseConnection();
        loadAllDatabase();
        setLocationRelativeTo(null);
        tableSelectionListener();
    }

    private void loadAllDatabase() {
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
                        rs.getString("tahunterbit"),
                        rs.getString("isbn"),
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

    public void tableSelectionListener() {
        ListSelectionListener listener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int row = tblDataBuku.getSelectedRow();
                if (row >= 0) {
                    String idBuku = tblDataBuku.getValueAt(row, 0).toString();
                    String judulBuku = tblDataBuku.getValueAt(row, 1).toString();
                    FrmMain.pilihDataBuku(idBuku, judulBuku);
                }
                dispose();
            }
        };
        tblDataBuku.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblDataBuku.getSelectionModel().addListSelectionListener(listener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblDataBuku = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setPreferredSize(new java.awt.Dimension(684, 250));

        tblDataBuku.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblDataBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Buku", "Judul Buku", "Pengarang", "Penerbit", "Tahun Terbit", "ISBN", "Kategori Buku"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tblDataBuku.setName(""); // NOI18N
        tblDataBuku.setPreferredSize(new java.awt.Dimension(1048, 768));
        jScrollPane2.setViewportView(tblDataBuku);
        tblDataBuku.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblDataBuku;
    // End of variables declaration//GEN-END:variables
}
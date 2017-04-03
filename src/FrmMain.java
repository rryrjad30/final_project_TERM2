
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import util.DbConn;
import Data.JDialogTransaksi;
import Data.JDialogPenyewa;
import Data.JDialogBuku;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LENOVO
 */
public class FrmMain extends javax.swing.JFrame {

    private Connection conn;

    /**
     * Creates new form FrmMain
     */
    public FrmMain(Connection conn) {
        this.conn = conn;
        initComponents();

        String username = JDialogLogin.txtUsername.getText();
        txtUsernameMain.setText(username);
        
        tableSelectionListener();

        lblDenda.setText("<html>* Normal : Rp 10.000/hari<br>* Special : Rp 7.000/hari</html>");

        databaseConnection();
        loadAllDatabase();

        idTransaksi();

        setLocationRelativeTo(null);
    }

    public void tableSelectionListener() {
        ListSelectionListener listener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int row = tblTransaksi.getSelectedRow();
                if (row >= 0) {
                    try {
                        txtIdTransaksi.setText(tblTransaksi.getValueAt(row, 0).toString());
                        txtIdNama.setText(tblTransaksi.getValueAt(row, 1).toString());
                        txtNama.setText(getNamebyIdNama(Integer.valueOf(txtIdNama.getText())));
                        txtIdBuku.setText(tblTransaksi.getValueAt(row, 2).toString());
                        txtJudulBuku.setText(getBookbyIdBuku(Integer.valueOf(txtIdBuku.getText())));

                        Date datePinjam = new SimpleDateFormat("dd-MM-yyyy").parse((String) tblTransaksi.getValueAt(row, 3));
                        dtcTanggalPinjam.setDate(datePinjam);

                        Date datePengembalian = new SimpleDateFormat("dd-MM-yyyy").parse((String) tblTransaksi.getValueAt(row, 4));
                        dtcTanggalPengembalian.setDate(datePengembalian);
                    } catch (SQLException ex) {
                        Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        tblTransaksi.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblTransaksi.getSelectionModel().addListSelectionListener(listener);
    }

    private String getNamebyIdNama(int idnama) throws SQLException {
        String Name = "";
        String sqlPenyewaLookup = "Select nama from datapenyewa where idnama = ? ;";

        PreparedStatement pstPenyewaLookup = conn.prepareStatement(sqlPenyewaLookup);
        pstPenyewaLookup.setInt(1, idnama);

        ResultSet rsPenyewaLookup = pstPenyewaLookup.executeQuery();
        while (rsPenyewaLookup.next()) {
            Name = rsPenyewaLookup.getString("nama");
        }
        return Name;
    }

    private String getBookbyIdBuku(int idbuku) throws SQLException {
        String judulbuku = "";
        String sqlBukuLookup = "Select judulbuku from databuku where idbuku = ? ;";

        PreparedStatement pstBukuLookup = conn.prepareStatement(sqlBukuLookup);
        pstBukuLookup.setInt(1, idbuku);

        ResultSet rsBukuLookup = pstBukuLookup.executeQuery();
        while (rsBukuLookup.next()) {
            judulbuku = rsBukuLookup.getString("judulbuku");
        }
        return judulbuku;
    }

    private void idTransaksi() {
        try {
            String sql = "SELECT max(idtransaksi) FROM transaksi;";
            Integer lastDataValue = 0;
            PreparedStatement pstatement = conn.prepareStatement(sql);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty

                while (rs.next()) {
                    lastDataValue = rs.getInt("max(idtransaksi)");
                };
                // tableModel.addRow(data);
            } else {
                util.Sutil.msg(this, "Record Empty");
            }
            ++lastDataValue;
            txtIdTransaksi.setText(lastDataValue.toString());

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(FrmDataPenyewa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteDatabase(int idtransaksi) {
        try {
            Class.forName(DbConn.JDBC_CLASS);
            Connection conn = DriverManager.getConnection(DbConn.JDBC_URL,
                    DbConn.JDBC_USERNAME,
                    DbConn.JDBC_PASSWORD);

            if (conn != null) {
                System.out.println("Connected to DB!\n");

                String sql = "DELETE FROM transaksi where "
                        + "idtransaksi = '" + idtransaksi + "' ;";

                PreparedStatement pstatement = conn.prepareStatement(sql);

                pstatement.execute();
                System.out.println("Deleted.");

                pstatement.close();
                conn.close();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    private void createDatabase(String username, int idnama, int idbuku,
            Date tanggalpinjam, Date tanggalpengembalian) throws ParseException {
        try {
//            Class.forName(DbConn.JDBC_CLASS);
            Connection conn = DriverManager.getConnection(DbConn.JDBC_URL,
                    DbConn.JDBC_USERNAME,
                    DbConn.JDBC_PASSWORD);

            if (conn != null) {
                System.out.println("Connected to DB!\n");

                String sql = "INSERT INTO transaksi "
                        + "(username, idnama, idbuku, tanggalpinjam, tanggalpengembalian)"
                        + "VALUES (?,?,?,?,? + interval 3 day);";

                PreparedStatement pstatement = conn.prepareStatement(sql);
                pstatement.setString(1, username);
                pstatement.setInt(2, idnama);
                pstatement.setInt(3, idbuku);

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String tanggalpinjam1 = sdf.format(tanggalpinjam);
                String tanggalpengembalian1 = sdf.format(tanggalpengembalian);

                Date tanggalpinjam2 = sdf.parse(tanggalpinjam1);
                Date tanggalpengembalian2 = sdf.parse(tanggalpengembalian1);

                java.sql.Date sqlDate = new java.sql.Date(tanggalpinjam2.getTime());
                java.sql.Date sqlDate1 = new java.sql.Date(tanggalpengembalian2.getTime());

                pstatement.setDate(4, sqlDate);
                pstatement.setDate(5, sqlDate1);

                pstatement.executeUpdate();
                System.out.println("Record insert.");

                pstatement.close();
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        }
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

                    Object data[] = {
                        rs.getInt("idtransaksi"),
                        rs.getInt("idnama"),
                        rs.getInt("idbuku"),
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        lblLibrary = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblBack = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtIdTransaksi = new javax.swing.JTextField();
        txtIdNama = new javax.swing.JTextField();
        txtIdBuku = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        btnCariDataPenyewa = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btnCariDataBuku = new javax.swing.JButton();
        lblDenda = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnTransaction = new javax.swing.JButton();
        txtJudulBuku = new javax.swing.JTextField();
        dtcTanggalPinjam = new com.toedter.calendar.JDateChooser();
        dtcTanggalPengembalian = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtUsernameMain = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuFile = new javax.swing.JMenu();
        MniAdmin = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        MniLogout = new javax.swing.JMenuItem();
        MenuData = new javax.swing.JMenu();
        mniTransaksi = new javax.swing.JMenuItem();
        mniPenyewa = new javax.swing.JMenuItem();
        mniBuku = new javax.swing.JMenuItem();
        MenuRegister = new javax.swing.JMenu();
        MniDataPenyewa = new javax.swing.JMenuItem();
        MniDataBuku = new javax.swing.JMenuItem();
        MenuHelp = new javax.swing.JMenu();
        MenuAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLibrary.setFont(new java.awt.Font("Trajan Pro", 1, 40)); // NOI18N
        lblLibrary.setForeground(new java.awt.Color(255, 255, 255));
        lblLibrary.setText("Book Forest");
        getContentPane().add(lblLibrary, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 500, 50));

        lblAlamat.setFont(new java.awt.Font("Trajan Pro", 1, 20)); // NOI18N
        lblAlamat.setForeground(new java.awt.Color(255, 255, 255));
        lblAlamat.setText("Jalan Kapten Maulana Lubis Aryaduta Hotel lt.1");
        getContentPane().add(lblAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, -1, -1));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo1.png"))); // NOI18N
        getContentPane().add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 90, 90));

        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Library-banner.jpg"))); // NOI18N
        getContentPane().add(lblBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 110));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transaksi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jLabel1.setText("ID Transaksi");

        jLabel2.setText("ID Penyewa");

        jLabel4.setText("ID Buku");

        jLabel5.setText("Tanggal Pinjam");

        jLabel6.setText("Tanggal Pengembalian");

        txtIdTransaksi.setEditable(false);

        txtIdNama.setEditable(false);
        txtIdNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdNamaActionPerformed(evt);
            }
        });

        txtIdBuku.setEditable(false);

        txtNama.setEditable(false);

        btnCariDataPenyewa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCariDataPenyewa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        btnCariDataPenyewa.setText("Search");
        btnCariDataPenyewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariDataPenyewaActionPerformed(evt);
            }
        });

        jLabel12.setText("Denda");

        btnCariDataBuku.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCariDataBuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        btnCariDataBuku.setText("Search");
        btnCariDataBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariDataBukuActionPerformed(evt);
            }
        });

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/new.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnTransaction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/update.png"))); // NOI18N
        btnTransaction.setText("Save");
        btnTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransactionActionPerformed(evt);
            }
        });

        txtJudulBuku.setEditable(false);

        dtcTanggalPinjam.setToolTipText("");
        dtcTanggalPinjam.setDateFormatString("dd-MM-yyyy");

        dtcTanggalPengembalian.setDateFormatString("dd-MM-yyyy");
        dtcTanggalPengembalian.setDoubleBuffered(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(dtcTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(dtcTanggalPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(lblDenda, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(74, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtIdNama, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                            .addComponent(txtIdTransaksi))
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(btnCariDataPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtIdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtJudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(btnCariDataBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(483, 483, 483)))))
                .addGap(94, 94, 94))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdNama, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnCariDataPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtIdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariDataBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dtcTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtcTanggalPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(lblDenda, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 720, 330));

        tblTransaksi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Transaksi", "ID Penyewa", "ID Buku", "Tanggal Pinjam", "Tanggal Pengembalian"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        tblTransaksi.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 720, 158));

        jLabel7.setText("Username");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        txtUsernameMain.setEditable(false);
        getContentPane().add(txtUsernameMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 70, -1));

        MenuFile.setText("File");

        MniAdmin.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, java.awt.event.InputEvent.CTRL_MASK));
        MniAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/administrator.png"))); // NOI18N
        MniAdmin.setText("User");
        MniAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MniAdminActionPerformed(evt);
            }
        });
        MenuFile.add(MniAdmin);
        MenuFile.add(jSeparator1);

        MniLogout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        MniLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logout.png"))); // NOI18N
        MniLogout.setText("Logout");
        MniLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MniLogoutActionPerformed(evt);
            }
        });
        MenuFile.add(MniLogout);

        jMenuBar1.add(MenuFile);

        MenuData.setText("Data");

        mniTransaksi.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        mniTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/textfile.png"))); // NOI18N
        mniTransaksi.setText("Transaksi");
        mniTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTransaksiActionPerformed(evt);
            }
        });
        MenuData.add(mniTransaksi);

        mniPenyewa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        mniPenyewa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/User Icon.png"))); // NOI18N
        mniPenyewa.setText("Penyewa");
        mniPenyewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPenyewaActionPerformed(evt);
            }
        });
        MenuData.add(mniPenyewa);

        mniBuku.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        mniBuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Buku Icon.png"))); // NOI18N
        mniBuku.setText("Book");
        mniBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniBukuActionPerformed(evt);
            }
        });
        MenuData.add(mniBuku);

        jMenuBar1.add(MenuData);

        MenuRegister.setText("Register");

        MniDataPenyewa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        MniDataPenyewa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/User Icon.png"))); // NOI18N
        MniDataPenyewa.setText("Penyewa");
        MniDataPenyewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MniDataPenyewaActionPerformed(evt);
            }
        });
        MenuRegister.add(MniDataPenyewa);

        MniDataBuku.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        MniDataBuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Buku Icon.png"))); // NOI18N
        MniDataBuku.setText("Buku");
        MniDataBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MniDataBukuActionPerformed(evt);
            }
        });
        MenuRegister.add(MniDataBuku);

        jMenuBar1.add(MenuRegister);

        MenuHelp.setText("Help");

        MenuAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        MenuAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/about.png"))); // NOI18N
        MenuAbout.setText("About");
        MenuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAboutActionPerformed(evt);
            }
        });
        MenuHelp.add(MenuAbout);

        jMenuBar1.add(MenuHelp);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuAboutActionPerformed
        executeAbout();
    }//GEN-LAST:event_MenuAboutActionPerformed

    private void MniLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MniLogoutActionPerformed
        executeLogout();
    }//GEN-LAST:event_MniLogoutActionPerformed

    private void MniDataPenyewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MniDataPenyewaActionPerformed
        executeDataPenyewa();
    }//GEN-LAST:event_MniDataPenyewaActionPerformed

    private void MniDataBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MniDataBukuActionPerformed
        executeDataBuku();
    }//GEN-LAST:event_MniDataBukuActionPerformed

    private void txtIdNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdNamaActionPerformed

    private void btnCariDataPenyewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariDataPenyewaActionPerformed
        // TODO add your handling code here:
        JDialogTabelPenyewa dlgtblpenyewa = new JDialogTabelPenyewa(this, true, conn);
        dlgtblpenyewa.setVisible(true);
    }//GEN-LAST:event_btnCariDataPenyewaActionPerformed

    private void btnCariDataBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariDataBukuActionPerformed
        // TODO add your handling code here:
        JDialogTabelBuku dlgtblbuku = new JDialogTabelBuku(this, true, conn);
        dlgtblbuku.setVisible(true);
    }//GEN-LAST:event_btnCariDataBukuActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        executeNew();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        executeDelete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransactionActionPerformed
        // TODO add your handling code here:
        executeSave();
    }//GEN-LAST:event_btnTransactionActionPerformed

    private void MniAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MniAdminActionPerformed
        // TODO add your handling code here:
        FrmUser user = new FrmUser(conn);
        user.setVisible(true);
    }//GEN-LAST:event_MniAdminActionPerformed

    private void mniPenyewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPenyewaActionPerformed
        // TODO add your handling code here:
        seeDataPenyewa();
    }//GEN-LAST:event_mniPenyewaActionPerformed

    private void mniTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTransaksiActionPerformed
        // TODO add your handling code here:
        seeDataTransaksi();
    }//GEN-LAST:event_mniTransaksiActionPerformed

    private void mniBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniBukuActionPerformed
        // TODO add your handling code here:
        seeDataBuku();
    }//GEN-LAST:event_mniBukuActionPerformed

    private void seeDataTransaksi() {
        JDialogTransaksi dlgTransaksi = new JDialogTransaksi(this, true, conn);
        dlgTransaksi.setVisible(true);
    }

    private void seeDataPenyewa() {
        JDialogPenyewa dlgPenyewa = new JDialogPenyewa(this, true, conn);
        dlgPenyewa.setVisible(true);
    }

    private void seeDataBuku() {
        JDialogBuku dlgBuku = new JDialogBuku(this, true, conn);
        dlgBuku.setVisible(true);
    }

    private void executeDataBuku() {
        FrmDataBuku buku = new FrmDataBuku(conn);
        buku.setVisible(true);
    }

    private void executeDataPenyewa() {
        FrmDataPenyewa data = new FrmDataPenyewa(conn);
        data.setVisible(true);
    }

    private void executeLogout() {
        if (util.Sutil.msq(this, "Are you sure to log out ? ") == 0) {
            this.setVisible(false);
            JDialogLogin.instance.setVisible(true);
        } else {

        }
    }

    @Override
    public void dispose() {
        executeLogout();
    }

    private void executeAbout() {
        util.Sutil.msg(this, "Book Forest Library"
                + "\n Version : 1.0"
                + "\n Author  : Lavinia"
                + "\n               Pierry Rajadi"
                + "\n               Valerie Leonie"
                + "\n               William");
    }

    public static void pilihDataPenyewa(String idnama, String nama) {
        txtIdNama.setText(idnama);
        txtNama.setText(nama);
    }

    public static void pilihDataBuku(String idbuku, String judulbuku) {
        txtIdBuku.setText(idbuku);
        txtJudulBuku.setText(judulbuku);
    }

    private void executeNew() {
        txtIdTransaksi.setText("");
        txtIdNama.setText("");
        txtNama.setText("");
        txtIdBuku.setText("");
        txtJudulBuku.setText("");
        dtcTanggalPinjam.cleanup();
        dtcTanggalPengembalian.cleanup();
    }

    private void executeDelete() {
        deleteDatabase(Integer.parseInt(txtIdTransaksi.getText()));
        loadAllDatabase();
    }

    private void executeSave() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String tanggalpinjam = sdf.format(dtcTanggalPinjam.getDate());
            String tanggalpengembalian = sdf.format(dtcTanggalPengembalian.getDate());

            Date tanggalpinjam1 = sdf.parse(tanggalpinjam);
            Date tanggalpengembalian1 = sdf.parse(tanggalpengembalian);

            createDatabase(txtUsernameMain.getText(), Integer.parseInt(txtIdNama.getText()),
                    Integer.parseInt(txtIdBuku.getText()),
                    tanggalpinjam1, tanggalpengembalian1);

            loadAllDatabase();

        } catch (ParseException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removeTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) tblTransaksi.getModel();
        tableModel.setRowCount(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuAbout;
    private javax.swing.JMenu MenuData;
    private javax.swing.JMenu MenuFile;
    private javax.swing.JMenu MenuHelp;
    private javax.swing.JMenu MenuRegister;
    private javax.swing.JMenuItem MniAdmin;
    private javax.swing.JMenuItem MniDataBuku;
    private javax.swing.JMenuItem MniDataPenyewa;
    private javax.swing.JMenuItem MniLogout;
    private javax.swing.JButton btnCariDataBuku;
    private javax.swing.JButton btnCariDataPenyewa;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnTransaction;
    private javax.swing.ButtonGroup buttonGroup1;
    private static com.toedter.calendar.JDateChooser dtcTanggalPengembalian;
    private static com.toedter.calendar.JDateChooser dtcTanggalPinjam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblDenda;
    private javax.swing.JLabel lblLibrary;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JMenuItem mniBuku;
    private javax.swing.JMenuItem mniPenyewa;
    private javax.swing.JMenuItem mniTransaksi;
    private javax.swing.JTable tblTransaksi;
    public static javax.swing.JTextField txtIdBuku;
    public static javax.swing.JTextField txtIdNama;
    public javax.swing.JTextField txtIdTransaksi;
    public static javax.swing.JTextField txtJudulBuku;
    public static javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtUsernameMain;
    // End of variables declaration//GEN-END:variables
}

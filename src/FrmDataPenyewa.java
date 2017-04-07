
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
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

    /**
     * Creates new form FrmDataPenyewa
     */
    public FrmDataPenyewa(Connection conn) {
        this.conn = conn;
        initComponents();
        tableSelectionListener();
        createIdNama();
        databaseConnection();
        loadAllDatabase();
        setLocationRelativeTo(null);
    }

    public void tableSelectionListener() {
        ListSelectionListener listener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int row = tblPenyewa.getSelectedRow();
                if (row >= 0) {
                    try {
                        txtIdNama.setText(tblPenyewa.getValueAt(row, 0).toString());
                        txtNama.setText(tblPenyewa.getValueAt(row, 1).toString());
                        if (tblPenyewa.getValueAt(row, 2).toString().equals("Laki - laki")) {
                            rdoLaki.setSelected(true);
                        } else if (tblPenyewa.getValueAt(row, 2).toString().equals("Perempuan")) {
                            rdoPerempuan.setSelected(true);
                        }
                        txtTempatLahir.setText(tblPenyewa.getValueAt(row, 3).toString());
                        
                        Date tgllahir = new SimpleDateFormat("dd-MM-yyyy").parse((String) tblPenyewa.getValueAt(row, 4));
                        dtcTanggalLahir.setDate(tgllahir);
                        
                        txtNoHP.setText(tblPenyewa.getValueAt(row, 5).toString());
                        txtAlamatPenyewa.setText(tblPenyewa.getValueAt(row, 6).toString());
                        
                        if (tblPenyewa.getValueAt(row, 7).toString().equals("Normal")) {
                            rdoNormal.setSelected(true);
                        } else if (tblPenyewa.getValueAt(row, 7).toString().equals("Special")) {
                            rdoSpesial.setSelected(true);
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(FrmDataPenyewa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        tblPenyewa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblPenyewa.getSelectionModel().addListSelectionListener(listener);
    }

    private void loadAllDatabase() {
        removeTableData();
        createIdNama();
        try {
            String sql = "SELECT idnama,nama, jeniskelamin,tempatlahir,"
                    + "date_format(tanggallahir, '%d-%m-%Y') as tanggallahir,"
                    + "nohp, alamat, jenispenyewa FROM datapenyewa;";
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

    private void createDatabase(String nama, String gender, String tempatlahir,
            Date tanggallahir, String nohp, String alamat, String jenispenyewa) throws ParseException {
        try {
//            Class.forName(DbConn.JDBC_CLASS);
            Connection conn = DriverManager.getConnection(DbConn.JDBC_URL,
                    DbConn.JDBC_USERNAME,
                    DbConn.JDBC_PASSWORD);

            if (conn != null) {
                System.out.println("Connected to DB!\n");

                String sql = "INSERT INTO datapenyewa "
                        + "(nama, jeniskelamin, tempatlahir, tanggallahir, nohp, alamat, jenispenyewa)"
                        + "VALUES (?,?,?,?,?,?,?);";
                PreparedStatement pstatement = conn.prepareStatement(sql);
                pstatement.setString(1, nama);
                pstatement.setString(2, gender);

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String tanggallahir1 = sdf.format(tanggallahir);

                Date tanggallahir2 = sdf.parse(tanggallahir1);
                java.sql.Date sqlDate = new java.sql.Date(tanggallahir2.getTime());
                pstatement.setDate(3, sqlDate);

                pstatement.setString(4, tempatlahir);
                pstatement.setString(5, nohp);
                pstatement.setString(6, alamat);
                pstatement.setString(7, jenispenyewa);

                pstatement.executeUpdate();
                System.out.println("Record insert.");

                pstatement.close();
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        }
    }

    private void updateDatabase(int idnama, String nama, String gender, String tempatlahir,
            Date tanggallahir, String nohp, String alamat, String jenispenyewa) {
        try {
            Class.forName(DbConn.JDBC_CLASS);
            Connection conn = DriverManager.getConnection(DbConn.JDBC_URL,
                    DbConn.JDBC_USERNAME,
                    DbConn.JDBC_PASSWORD);

            if (conn != null) {
                System.out.println("Connected to DB!\n");

                String sql = "UPDATE `databuku` set "
                        + "nama = '" + nama + "' , "
                        + "gender = '" + gender + "' , "
                        + "tempatlahir = '" + tempatlahir + "' , "
                        + "tanggallahir = '" + tanggallahir + "' , "
                        + "nohp = '" + nohp + "' , "
                        + "alamat = '" + alamat + "' "
                        + "jenispenyewa = '" + jenispenyewa + "' "
                        + "where idnama = '" + idnama + "';";

                PreparedStatement pstatement = conn.prepareStatement(sql);

                pstatement.executeUpdate();
                System.out.println("Updated.");

                pstatement.close();

                conn.close();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    private void deleteDatabase(int idnama) {
        try {
            Class.forName(DbConn.JDBC_CLASS);
            Connection conn = DriverManager.getConnection(DbConn.JDBC_URL,
                    DbConn.JDBC_USERNAME,
                    DbConn.JDBC_PASSWORD);

            if (conn != null) {
                System.out.println("Connected to DB!\n");

                String sql = "DELETE FROM `datapenyewa` where idnama = '" + idnama + "';";

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
        txtIdNama = new javax.swing.JTextField();
        lblNoHp = new javax.swing.JLabel();
        txtNoHP = new javax.swing.JTextField();
        lblJenis = new javax.swing.JLabel();
        rdoNormal = new javax.swing.JRadioButton();
        rdoSpesial = new javax.swing.JRadioButton();
        btnBack = new javax.swing.JButton();
        lblNama1 = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        dtcTanggalLahir = new com.toedter.calendar.JDateChooser();
        btbRefresh = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();
        lblLibrary = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        lblBack = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        tblPenyewa.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblPenyewa);
        tblPenyewa.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

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

        txtIdNama.setEditable(false);
        txtIdNama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtIdNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdNamaActionPerformed(evt);
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

        dtcTanggalLahir.setDateFormatString("dd-MM-yyyy");

        btbRefresh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btbRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow_refresh.png"))); // NOI18N
        btbRefresh.setText("Refresh");
        btbRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDataPenyewaLayout = new javax.swing.GroupLayout(pnlDataPenyewa);
        pnlDataPenyewa.setLayout(pnlDataPenyewaLayout);
        pnlDataPenyewaLayout.setHorizontalGroup(
            pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataPenyewaLayout.createSequentialGroup()
                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btbRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                                .addComponent(lblNama1)
                                .addGap(19, 19, 19)
                                .addComponent(txtIdNama, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataPenyewaLayout.createSequentialGroup()
                                        .addComponent(lblAlamatPenyewa)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtAlamatPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataPenyewaLayout.createSequentialGroup()
                                        .addComponent(lblNoHp)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                                                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblJenisKelamin)
                                                    .addComponent(lblTempatLahir))
                                                .addGap(18, 18, 18)
                                                .addComponent(rdoLaki)
                                                .addGap(37, 37, 37)
                                                .addComponent(rdoPerempuan))
                                            .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                                                .addComponent(lblHp)
                                                .addGap(18, 18, 18)
                                                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(dtcTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                                    .addComponent(lblJenis)
                                    .addGap(18, 18, 18)
                                    .addComponent(rdoNormal)
                                    .addGap(50, 50, 50)
                                    .addComponent(rdoSpesial))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addComponent(lblUserPict, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
        );
        pnlDataPenyewaLayout.setVerticalGroup(
            pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUserPict, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtIdNama, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNama1)
                                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblJenisKelamin)
                                            .addComponent(rdoPerempuan)
                                            .addComponent(rdoLaki))
                                        .addGap(2, 2, 2)
                                        .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblTempatLahir)
                                            .addComponent(txtTempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dtcTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblHp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtAlamatPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAlamatPenyewa))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNoHP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNoHp))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDataPenyewaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoNormal)
                                    .addComponent(lblJenis)
                                    .addComponent(rdoSpesial)))))
                    .addGroup(pnlDataPenyewaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(btbRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

    private void txtIdNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdNamaActionPerformed

    private void txtAlamatPenyewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAlamatPenyewaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlamatPenyewaActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        toDeleteData();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        try {
            toUpdateData();
        } catch (ParseException ex) {
            Logger.getLogger(FrmDataPenyewa.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        executeNew();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btbRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbRefreshActionPerformed
        // TODO add your handling code here:
        loadAllDatabase();
    }//GEN-LAST:event_btbRefreshActionPerformed

    private void executeNew() {
        createIdNama();
        txtNama.setText("");
        rdoLaki.setSelected(false);
        rdoPerempuan.setSelected(false);
        txtTempatLahir.setText("");
        dtcTanggalLahir.cleanup();
        txtAlamatPenyewa.setText("");
        txtNoHP.setText("");
        rdoNormal.setSelected(false);
        rdoSpesial.setSelected(false);
    }

    private void createIdNama() {
        try {
            String sql = "SELECT max(idnama) FROM datapenyewa;";
            Integer lastDataValue = 0;
            PreparedStatement pstatement = conn.prepareStatement(sql);

            ResultSet rs = pstatement.executeQuery();
            if (rs.isBeforeFirst()) { // check is resultset not empty

                while (rs.next()) {
                    lastDataValue = rs.getInt("max(idnama)");
                };
                // tableModel.addRow(data);
            } else {
                util.Sutil.msg(this, "Record Empty");
            }
            ++lastDataValue;
            txtIdNama.setText(lastDataValue.toString());

            rs.close();
            pstatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(FrmDataPenyewa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toCreateData() {
        try {
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

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String tanggalpinjam = sdf.format(dtcTanggalLahir.getDate());

            Date tanggallahir1 = sdf.parse(tanggalpinjam);

            createDatabase(txtNama.getText(), gender,
                    txtTempatLahir.getText(), tanggallahir1,
                    txtNoHP.getText(), txtAlamatPenyewa.getText(),
                    normalspecial);
            loadAllDatabase();
        } catch (ParseException ex) {
            Logger.getLogger(FrmDataPenyewa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toUpdateData() throws ParseException {
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String tanggallahir = sdf.format(dtcTanggalLahir.getDate());

        Date tanggallahir1 = sdf.parse(tanggallahir);

        updateDatabase(Integer.parseInt(txtIdNama.getText()), txtNama.getText(), gender, txtTempatLahir.getText(),
                tanggallahir1, txtNoHP.getText(), txtAlamatPenyewa.getText(), normalspecial);

        loadAllDatabase();
    }

    public void toDeleteData() {
        deleteDatabase(Integer.parseInt(txtIdNama.getText()));
        loadAllDatabase();
    }

    private void removeTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) tblPenyewa.getModel();
        tableModel.setRowCount(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbRefresh;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser dtcTanggalLahir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblAlamatPenyewa;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblHp;
    private javax.swing.JLabel lblJenis;
    private javax.swing.JLabel lblJenisKelamin;
    private javax.swing.JLabel lblLibrary;
    private javax.swing.JLabel lblLogo;
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
    private javax.swing.JTextField txtIdNama;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoHP;
    private javax.swing.JTextField txtTempatLahir;
    // End of variables declaration//GEN-END:variables
}

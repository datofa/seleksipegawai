/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seleksipegawai;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zuni
 */
public class laman_bobotkriteria extends javax.swing.JFrame {

    koneksi1 koneksi;
    ResultSet resultSet;
    Statement statement;
    public String sql = "";
    private Connection con;
    DefaultTableModel tabMode;

//    private void update_tabel() {
//        try {
//            java.sql.Connection conn;
//            conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
//            java.sql.Statement stm = conn.createStatement();
//            java.sql.ResultSet sql;
//            sql = stm.executeQuery("Select * from bobot_kriteria");
////        sql = stm.executeQuery("Select * from bobot_kriteria where id_kriteria like '%" + id_k.getSelectedItem()+ "%'");
//            jTable1.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(sql));
//
//        } catch (Exception e) {
//        }
//    }

    private void tabel_kriteria() {
        if(id_k.getSelectedItem()==""){
            try {
                java.sql.Connection conn;
                conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet sql;
                sql = stm.executeQuery("Select bk.id_bk, bk.nm_bk, bk.nilai_bk, bk.x, bk.y from bobot_kriteria bk join kriteria k on bk.id_kriteria=k.id_kriteria");
    //        sql = stm.executeQuery("Select * from bobot_kriteria where id_kriteria like '%" + id_k.getSelectedItem()+ "%'");
                jTable1.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(sql));

            } catch (Exception e) {
            }
        }
        else{
            try {
//                System.out.println("t_kriteria");
                java.sql.Connection conn;
                conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet sql;
                String query = "Select bk.id_bk, bk.nm_bk, bk.nilai_bk, bk.x, bk.y from bobot_kriteria bk join kriteria k on bk.id_kriteria=k.id_kriteria where kriteria='" + id_k.getSelectedItem() +"' ";
                System.out.println(query);
                sql = stm.executeQuery(query);
    //        sql = stm.executeQuery("Select * from bobot_kriteria where id_kriteria like '%" + id_k.getSelectedItem()+ "%'");
                jTable1.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(sql));

            } catch (Exception e) {
            }
        }
        
    }

    public laman_bobotkriteria() {
        initComponents();
        tampil_k();
        auto_number();
        tabel_kriteria();
        kosongkan_text();
    }

    private void koneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/pegawai1", "root", "");
        } catch (Exception e) {
        }
    }

    private void auto_number() {
        try {
            koneksi();
            String sql = "SELECT MAX(right(id_bk,2))AS no from bobot_kriteria";
            statement = (Statement) con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                if (rs.first() == false) {
                    id_bk.setText("BK001");
                } else {
                    rs.last();
                    int auto_id = rs.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int noLong = no.length();
                    for (int a = 0; a < 3 - noLong; a++) {
                        no = "0" + no;
                    }
                    id_bk.setText("BK" + no);

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error:\n" + e.toString(), "Kesalahan", JOptionPane.WARNING_MESSAGE);

        }
    }

    public final void tampil_k() {
        id_k.addItem("");

        try {
            koneksi();
            resultSet = statement.executeQuery("select kriteria from kriteria");
            while (resultSet.next()) {
                String sa = resultSet.getString("kriteria");
                id_k.addItem(sa);

            }
        } catch (Exception e) {
        }
    }

   
    private void tampil_kriteria() {
        try {
            koneksi();
            sql = "select bf.bobot from bobot_fuzzy bf join kriteria kr on bf.id_bobotfuzzy=kr.id_bobotfuzzy where kriteria='" + id_k.getSelectedItem() + "'";
            statement = (Statement) con.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                bobot_bf.setText(resultSet.getString("bf.bobot"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, statement);
        }
    }
    
    public String getIdKriteria() {
        String id_kriteria = "";
        String sql = "SELECT id_kriteria FROM kriteria WHERE kriteria='" + id_k.getSelectedItem() + "'";
        try {
            java.sql.Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                id_kriteria = resultSet.getString(1);
            }
        } catch (SQLException e) {

        }
        return id_kriteria;
    }
    
    private void kosongkan_text() {
           id_k.getSelectedItem();
           bobot_bf.setText("");
           id_bk.setText("");
           nm_bk.setText("");
           bobot_bk.setText("");
           x.setText("");
           y.setText("");
           auto_number();
       }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jToolBar1 = new javax.swing.JToolBar();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        id_k = new javax.swing.JComboBox();
        id_bk = new javax.swing.JTextField();
        nm_bk = new javax.swing.JTextField();
        bobot_bk = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        x = new javax.swing.JTextField();
        y = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        bobot_bf = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setVisible(true);

        jToolBar1.setRollover(true);

        jButton2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton2.setText("Simpan");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton3.setText("Edit");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton4.setText("Hapus");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jButton5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton5.setText("Tampilkan Laporan");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton5);

        jButton6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton6.setText("Keluar");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel1.setText("ID Kriteria                           :");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setText("ID Bobot Kriteria               :");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setText("Bobot Kriteria                   :");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel5.setText("Nilai Bobot Kriteria         :");

        id_k.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        id_k.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                id_kItemStateChanged(evt);
            }
        });
        id_k.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_kActionPerformed(evt);
            }
        });
        id_k.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_kKeyReleased(evt);
            }
        });

        id_bk.setEditable(false);
        id_bk.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        nm_bk.setBackground(new java.awt.Color(240, 240, 240));
        nm_bk.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        bobot_bk.setBackground(new java.awt.Color(240, 240, 240));
        bobot_bk.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Range Penilaian", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel6.setText("Batas Awal");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel7.setText("Batas Akhir");

        x.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        y.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        y.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel8.setText("-");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(x, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(y, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel7)))
                .addGap(63, 63, 63))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(x, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(y, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jButton7.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton7.setText("Batal");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        bobot_bf.setEditable(false);
        bobot_bf.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel9.setText("Bobot Fuzzy                        :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(13, 13, 13)
                                .addComponent(id_k, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bobot_bk)
                                    .addComponent(nm_bk)
                                    .addComponent(bobot_bf, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(id_bk, javax.swing.GroupLayout.Alignment.TRAILING))))))
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(id_k, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(bobot_bf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id_bk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nm_bk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(bobot_bk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addContainerGap())
        );

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            String sql = "delete from bobot_kriteria where id_bk=? ";
            java.sql.Connection conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, id_bk.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Hapus");


        } catch (Exception e) {
        }
        tabel_kriteria();
        auto_number();
        kosongkan_text();

    }//GEN-LAST:event_jButton4ActionPerformed

    private void yActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yActionPerformed

    private void id_kActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_kActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_kActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        try {
            int row = jTable1.getSelectedRow();
            String tabel_klik;
            tabel_klik = (jTable1.getModel().getValueAt(row, 0).toString());
            java.sql.Connection conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet sql = stm.executeQuery("select * from bobot_kriteria where id_bk='" + tabel_klik + "'");
            if (sql.next()) {
                String add1 = sql.getString("id_bk");
                id_bk.setText(add1);
                String add2 = sql.getString("nm_bk");
                nm_bk.setText(add2);
                String add3 = sql.getString("nilai_bk");
                bobot_bk.setText(add3);
                String add4 = sql.getString("x");
                x.setText(add4);
                String add5 = sql.getString("y");
                y.setText(add5);

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        try {
            String sql;
            sql = "insert into bobot_kriteria values('" + id_bk.getText() + "','" + getIdKriteria() + "','" + nm_bk.getText() + "','" + bobot_bk.getText() + "','" + x.getText() + "','" + y.getText() + "')";
            System.out.println(sql);
            java.sql.Connection conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil Disimpan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        tabel_kriteria();
        auto_number();
        kosongkan_text();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Edit?", "Title on Box", dialogButton);
        if(dialogResult == 0) {
                try {
                    String value1 = id_bk.getText();
                    String value3 = nm_bk.getText();
                    String value4 = bobot_bk.getText();
                    String value5 = x.getText();
                    String value6 = y.getText();

                    String sql = "update bobot_kriteria set  id_kriteria='" + getIdKriteria() + "',nm_bk='" + value3 + "',nilai_bk='" + value4 + "',x='" + value5 + "',y='" + value6 + "' where id_bk='" + value1 + "'";
                    System.out.println(sql);
                    java.sql.Connection conn;
                    conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();

//                    JOptionPane.showMessageDialog(null, "Edit ?");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error");
                }

                tabel_kriteria();
                auto_number();
                kosongkan_text();
        } else {
          
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void id_kItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_id_kItemStateChanged
        tampil_kriteria();
        tabel_kriteria();
//    DefaultTableModel tabelTampil1 = new DefaultTableModel();
//    tabelTampil1.addColumn("ID BK");
//    tabelTampil1.addColumn("ID Kriteria");
//    tabelTampil1.addColumn("Nama ");
//    tabelTampil1.addColumn("Nilai ");
//    tabelTampil1.addColumn("Batas Awal ");
//    tabelTampil1.addColumn("Batas Akhir");
//     try{
//            koneksi(); 
//            String sql = "Select * from bobot_kriteria where id_kriteria like '%" + id_k.getSelectedItem()+ "%'" ;
//            ResultSet rs = statement.executeQuery(sql);
//            while (rs.next()) {
//            tabelTampil1.addRow(new Object[]{
//            rs.getString(1),
//            rs.getString(2),
//            rs.getString(3),
//            rs.getString(4),
//            rs.getString(5),
//              rs.getString(6),
//            });
//            }
//            jTable1.setModel(tabelTampil1);
//
//                }catch (Exception e){
//            }
//        
//    
    }//GEN-LAST:event_id_kItemStateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        kosongkan_text();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void id_kKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_kKeyReleased
        tabel_kriteria();
    }//GEN-LAST:event_id_kKeyReleased

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
            java.util.logging.Logger.getLogger(laman_bobotkriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(laman_bobotkriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(laman_bobotkriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(laman_bobotkriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new laman_bobotkriteria().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bobot_bf;
    private javax.swing.JTextField bobot_bk;
    private javax.swing.JTextField id_bk;
    private javax.swing.JComboBox id_k;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField nm_bk;
    private javax.swing.JTextField x;
    private javax.swing.JTextField y;
    // End of variables declaration//GEN-END:variables
}

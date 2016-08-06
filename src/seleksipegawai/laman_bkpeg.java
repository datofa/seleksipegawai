/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seleksipegawai;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Laporan.lap_bkpeg;
/**
 *
 * @author zuni
 */
public class laman_bkpeg extends javax.swing.JFrame {

    ResultSet resultSet;
    Statement statement;
    public String sql = "";
    private Connection con;

    /**
     * Creates new form laman_bkpeg
     */
    public laman_bkpeg() {
        koneksi1 koneksi = new koneksi1();
        con = (Connection) koneksi.koneksi();
        initComponents();
        tampil_periode();
//        auto_number();
        tabel_datamentah();
    }
    
    private void tabel_datamentah() {
        if ((tampil_periode.getSelectedItem() != "") && (k.getSelectedItem() != "")) {
            Object header[] = {"ID Pegawai", "Nama", "Nilai","Nama Bobot", "Bobot"};
            DefaultTableModel defaultTableModel = new DefaultTableModel(null, header);
            jTable1.setModel(defaultTableModel);

            //menghapus tabel sebelum menampilkan data
            int baris = jTable1.getRowCount();
            for (int i = 0; i < baris; i++) {
                defaultTableModel.removeRow(i);
            }
            String sql ="SELECT a.id_pegawai,a.nama,c.nilai,c.nm_bk,c.nilai_bk FROM pegawai a LEFT JOIN (SELECT * FROM bk_pegawai b WHERE b.id_periode ='" + tampil_periode.getSelectedItem() + "' AND b.id_kriteria='" + getIdKriteria() + "')c ON a.id_pegawai=c.id_pegawai";
//            System.out.println(sql);
            try {
                statement = (Statement) con.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String kolom1 = resultSet.getString(1);
                    String kolom2 = resultSet.getString(2);
                    String kolom3 = resultSet.getString(3);
                    String kolom4 = resultSet.getString(4);
                    String kolom5 = resultSet.getString(5);
                    
                    String kolom[] = {kolom1, kolom2, kolom3, kolom4, kolom5};
                    defaultTableModel.addRow(kolom);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.out.println("" + e.getMessage());
            }
        }
        else {
        Object header[] = {"ID Pegawai", "Nama Pegawai","Kriteria", "Nilai", "Bobot Kriteria"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(null, header);
        jTable1.setModel(defaultTableModel);

        //menghapus tabel sebelum menampilkan data
        int baris = jTable1.getRowCount();
        for (int i = 0; i < baris; i++) {
            defaultTableModel.removeRow(i);
        }
        }
    }

    
   

    public String getId_dm() {
        String id_dm = "";
        String sql = "SELECT id_dm FROM kriteria";
        try {
            java.sql.Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                id_dm = resultSet.getString(1);
            }
        } catch (SQLException e) {
        }
        return id_dm;
    }
    
    public String getIdKriteria() {
        String id = "";
        String sql = "SELECT id_kriteria FROM kriteria WHERE kriteria='" + k.getSelectedItem() + "'";
        try {
            java.sql.Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                id = resultSet.getString(1);
            }
        } catch (SQLException e) {
        }
        return id;
    }

    public String getIDkp() {
        String bk = "";
        String sql = "Select id_kp FROM kriteria_perperiode kp INNER JOIN kriteria k ON kp.id_kriteria=k.id_kriteria  WHERE kp.id_periode='" + tampil_periode.getSelectedItem() + "' AND k.id_kriteria='" + getIdKriteria() + "'";
//        System.out.println(sql);
        try {
            java.sql.Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                bk = resultSet.getString(1);
            }
        } catch (SQLException e) {
        }
        return bk;
    }

    public String getIDbk() {
        String bk = "";
        String sql = "Select id_bk FROM bobot_kriteria WHERE id_kriteria='" + getIdKriteria() + "' AND x<=" + tampil_xy() + " AND y>=" + tampil_xy();
//        System.out.println(sql);
        try {
            java.sql.Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                bk = resultSet.getString(1);
            }
        } catch (SQLException e) {
        }
        return bk;
    }

    public String getnmkriteria() {
        String nmk = "";
        String sql = "SELECT k.nama FROM kriteria k INNER JOIN kriteria_perperiode kp ON k.id_kriteria=kp.id_kriteria WHERE kp.id_periode='" + tampil_periode.getSelectedItem() + "' AND k.id_kriteria='" + getIdKriteria() + "'";
//        System.out.println(sql);
        try {
            java.sql.Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                nmk = resultSet.getString(1);
            }
        } catch (SQLException e) {
        }
        return nmk;
    }

    public Object tampil_idk() {
        Object add1 = (String) k.getSelectedItem();
        return add1;
    }

    public int tampil_xy() {
        if (nilai.getText() == "") {
            return 0;
        } else {
            int add2 = Integer.parseInt(nilai.getText());
            return add2;
        }
    }

    public String tampil_idpeg() {
        String add3 = (String) id_p.getText();
        return add3;
    }

    public String tampil_nmpeg() {
        String add4 = (String) nm_p.getText();
        return add4;
    }

     private void kosongkan_text() {
        id_p.setText("");
        nm_p.setText("");
        nilai.setText("");
        bobot.setText("");

    }

    public final void tampil_periode() {
        tampil_periode.addItem("");
        try {
            resultSet = statement.executeQuery("SELECT id_periode FROM kriteria_perperiode GROUP BY id_periode Order by id_periode");
            while (resultSet.next()) {
                String sa = resultSet.getString("id_periode");
                tampil_periode.addItem(sa);
            }
        } catch (Exception e) {
        }
    }


    public final void tampil_k() {
        k.removeAllItems();
        k.addItem("");
        try {
            resultSet = statement.executeQuery("SELECT k.kriteria FROM kriteria k INNER JOIN kriteria_perperiode kp ON k.id_kriteria=kp.id_kriteria WHERE kp.id_periode='" + tampil_periode.getSelectedItem() + "' ORDER BY k.id_kriteria ASC");
            while (resultSet.next()) {
                String sa = resultSet.getString("k.kriteria");
                k.addItem(sa);
//                System.out.println(sa);
            }
        } catch (Exception e) {
        }
    }

    private void tampil_kriteria() {
        try {
            sql = "SELECT k.kriteria FROM kriteria k INNER JOIN kriteria_perperiode kp ON k.id_kriteria=kp.id_kriteria WHERE kp.id_periode='" + tampil_periode.getSelectedItem() + "' ORDER BY k.id_kriteria ASC";
//            System.out.println(sql);
            statement = (Statement) con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, statement);
        }
    }
    public void insert() {
        if (k.getSelectedItem() == "") {
            JOptionPane.showMessageDialog(null, "Kriteria Kosong");
        } else {
            try {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(this, "Simpan data?", "Title on Box", dialogButton);
                if (dialogResult == 0) {
                    String sql;
                    sql = "insert into data_mentah values(null,'" + getIDkp() + "','" + getIDbk() + "','" + id_p.getText() + "','" + nilai.getText() + "')";
//                    System.out.println(sql);
                    java.sql.Connection conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Berhasil Disimpan");
                } else {
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            tabel_datamentah();
            kosongkan_text();
        }
    }
    
    public void update() {
        try {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Ubah data ini?", "Title on Box", dialogButton);
            if (dialogResult == 0) {
                String sql = "update data_mentah set id_bk='" + getIDbk() + "',nilai='" + nilai.getText() + "' where id_pegawai='" + id_p.getText() + "' AND id_kp='"+ getIDkp() +"'";
                System.out.println(sql);
                java.sql.Connection conn;
                conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Berhasil Diubah");
            } else {
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        tabel_datamentah();
        kosongkan_text();
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
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        id_p = new javax.swing.JTextField();
        nilai = new javax.swing.JTextField();
        bobot = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        tampil_periode = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        k = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        nm_p = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setTitle("Bobot Kriteria Pegawai");
        jInternalFrame1.setVisible(true);

        jToolBar1.setRollover(true);

        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton1.setText("Simpan");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton3.setText("Hapus");
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
        jButton4.setText("Tampilkan Laporan");
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
        jButton5.setText("Keluar");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setText("ID Pegawai            :");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setText("Nilai                          :");

        id_p.setBackground(new java.awt.Color(240, 240, 240));
        id_p.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        id_p.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_pKeyReleased(evt);
            }
        });

        nilai.setBackground(new java.awt.Color(240, 240, 240));
        nilai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nilaiKeyReleased(evt);
            }
        });

        bobot.setBackground(new java.awt.Color(240, 240, 240));
        bobot.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                bobotPropertyChange(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton6.setText("Batal");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        tampil_periode.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tampil_periode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tampil_periodeItemStateChanged(evt);
            }
        });
        tampil_periode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tampil_periodeActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel5.setText("Periode                    :");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Kriteria                     :");

        k.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        k.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                kItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel6.setText("Nama Pegawai    :");

        nm_p.setBackground(new java.awt.Color(240, 240, 240));
        nm_p.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        nm_p.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nm_pKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tampil_periode, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(k, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nm_p, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                            .addComponent(id_p)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(nilai, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bobot, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tampil_periode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(k, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(id_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nm_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nilai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bobot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addContainerGap(21, Short.MAX_VALUE))
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
        jTable1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTable1ComponentShown(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jInternalFrame1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        kosongkan_text();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void kItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_kItemStateChanged
        tampil_kriteria();
        getIDkp();
        tabel_datamentah();
    }//GEN-LAST:event_kItemStateChanged

    private void tampil_periodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tampil_periodeItemStateChanged
     tampil_k();
    }//GEN-LAST:event_tampil_periodeItemStateChanged

    private void tampil_periodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tampil_periodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tampil_periodeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
   try {
            String sql = "SELECT COUNT(*) FROM bk_pegawai WHERE id_pegawai='" + id_p.getText() + "' AND id_periode='" + tampil_periode.getSelectedItem() + "' AND id_kriteria='" + getIdKriteria() + "'";
            System.out.println(sql);
            statement = (Statement) con.createStatement();
            resultSet = statement.executeQuery(sql);
           
            while (resultSet.next()) {
                int no=resultSet.getInt(1);
                if (no > 0)
            {
                update();
            }else{
    insert();
}
            }} catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error:\n" + e.toString(), "Kesalahan", JOptionPane.WARNING_MESSAGE);
    }
//                if (resultSet.first() == false) {
//                    insert();
//            int no= resultSet.getInt(0);
//            {insert();
//            }
//                } else {
//                    resultSet.last();
//                    int no = Integer.getInteger(sql);
//                    if (no > 0) {
//                        update();


//        insert();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void nilaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nilaiKeyReleased
        bobot.setText("");
        if (tampil_xy() != 0) {
            try {
                sql = "SELECT nm_bk FROM bobot_kriteria WHERE id_kriteria='" + getIdKriteria() + "' AND x<=" + tampil_xy() + " AND y>=" + tampil_xy();
                statement = (Statement) con.createStatement();
//                System.out.println(sql);
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
//                    System.out.println(resultSet.getString("nm_bk"));
                    bobot.setText(resultSet.getString("nm_bk"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, statement);
            }
        }
    }//GEN-LAST:event_nilaiKeyReleased

    private void bobotPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_bobotPropertyChange
        bobot.setEnabled(false);        // TODO add your handling code here:
    }//GEN-LAST:event_bobotPropertyChange

    private void id_pKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_pKeyReleased
        nm_p.setText("");
        if (tampil_idpeg() != "") {
            try {
                sql = "Select * from pegawai where id_pegawai like '%" + tampil_idpeg() + "%'";
//                System.out.println(sql);
                statement = (Statement) con.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    nm_p.setText(resultSet.getString("nama"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, statement);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_id_pKeyReleased

    private void nm_pKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nm_pKeyReleased
        id_p.setText("");
        if (tampil_nmpeg() != "") {
            try {
                sql = "Select * from pegawai where nama like '%" + tampil_nmpeg() + "%'";
//               System.out.println(sql);
                statement = (Statement) con.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    id_p.setText(resultSet.getString("id_pegawai"));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, statement);
            }
        }
    }//GEN-LAST:event_nm_pKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            int row = jTable1.getSelectedRow();
            String tabel_klik;
            tabel_klik = (jTable1.getModel().getValueAt(row, 0).toString());
            java.sql.Connection conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            String query = "SELECT a.id_pegawai, a.nama, d.nilai, d.nm_bk,d.nilai_bk FROM pegawai a LEFT JOIN (select * from bk_pegawai c where c.id_kriteria='"+getIdKriteria()+"' and c.id_periode='"+tampil_periode.getSelectedItem()+"') d ON a.id_pegawai=d.id_pegawai where a.id_pegawai='"+tabel_klik+"';"; 
            java.sql.ResultSet sql = stm.executeQuery(query);
            System.out.print(query+"\n");
            if (sql.next()) {
                String add1 = sql.getString("a.id_pegawai");
                id_p.setText(add1);
                String add2 = sql.getString("a.nama");
                nm_p.setText(add2);
                String add4 = sql.getString("d.nilai");
                System.out.print(add4+"\n");
                nilai.setText(add4);
                String add5 = sql.getString("d.nm_bk");
                bobot.setText(add5);
            }
            else{
                System.out.println("data kosong");
            }
        } catch (Exception e) {
            System.out.print("error");
        }
        
//        auto_number();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
           try {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Hapus data ini?", "Title on Box", dialogButton);
            if (dialogResult == 0) {
                String sql = "delete from data_mentah where id_pegawai=? ";
                java.sql.Connection conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);

                pst.setString(1, id_p.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Berhasil Dihapus");
            } else {
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        tabel_datamentah();
        kosongkan_text();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTable1ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1ComponentShown

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
  new lap_bkpeg().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(laman_bkpeg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(laman_bkpeg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(laman_bkpeg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(laman_bkpeg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new laman_bkpeg().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bobot;
    private javax.swing.JTextField id_p;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JComboBox k;
    private javax.swing.JTextField nilai;
    private javax.swing.JTextField nm_p;
    private javax.swing.JComboBox tampil_periode;
    // End of variables declaration//GEN-END:variables
}

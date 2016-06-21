/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seleksipegawai;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JMonthChooser;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author zuni
 */
public class kriteria_periode extends javax.swing.JFrame {
    ResultSet resultSet;
    Statement statement;
     public String sql = "";
    private Connection con;
    /**
     * Creates new form kriteria_periode
     */
    public kriteria_periode() throws SQLException  {
        koneksi1 koneksi = new koneksi1();
        con = (Connection) koneksi.koneksi();
        initComponents();
        autonomor();
        tampil_periode();
        tampil_dfr();
    }
    

    
    private void update_tabel() {
        if (tampil_periode.getSelectedItem() == "") {
            try {
                java.sql.Connection conn;
                conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet sql;
                sql = stm.executeQuery("SELECT kp.id_kp,kp.id_kriteria,k.kriteria,bf.bobot FROM(kriteria_perperiode kp LEFT JOIN kriteria k ON kp.id_kriteria=k.id_kriteria)LEFT JOIN bobot_fuzzy bf on k.id_bobotfuzzy=bf.id_bobotfuzzy ORDER BY kp.id_kp ASC");
                jTable1.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(sql));
            } catch (Exception e) {
            }
        } else {
            try {
                java.sql.Connection conn;
                conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet sql;
                sql = stm.executeQuery("SELECT kp.id_kp,kp.id_kriteria,k.kriteria,bf.bobot FROM(kriteria_perperiode kp LEFT JOIN kriteria k ON kp.id_kriteria=k.id_kriteria) LEFT JOIN bobot_fuzzy bf on k.id_bobotfuzzy=bf.id_bobotfuzzy where kp.id_periode='" + tampil_periode.getSelectedItem() + "'");
                System.out.println(sql);
                jTable1.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(sql));
            } catch (Exception e) {
            }
        }
    }
    
    private void autonomor() {
        String sql = "select max(id_kp) from kriteria_perperiode";
        try {
            statement = (Statement) con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int a = rs.getInt(1);
                id_kp.setText("" + Integer.toString(a + 1));
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }
    
    private void kosongkan_text() {
        id_kp.setText("");
        tampil_periode.getSelectedItem();
        bln.setText("");
        thn.setText("");
        tampil_daftar.getSelectedItem();
        autonomor();
    }
    
    public final void tampil_periode() {
        tampil_periode.addItem("");
        try {
            resultSet = statement.executeQuery("SELECT id_periode FROM periode");
            while (resultSet.next()) {
                String sa = resultSet.getString("id_periode");
                tampil_periode.addItem(sa);

            }
        } catch (Exception e) {
        }
    }
    
    private void tampil_bulantahun() {
        try {
            sql = "select bulan, tahun from periode where id_periode='" + tampil_periode.getSelectedItem() + "'";
            statement = (Statement) con.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                bln.setText(resultSet.getString("bulan"));
                thn.setText(resultSet.getString("tahun")); 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, statement);
        }
    }
    
    
     public final void tampil_dfr() {
        tampil_daftar.addItem("");
        try {
            resultSet = statement.executeQuery("SELECT kriteria FROM kriteria");
            while (resultSet.next()) {
                String sa = resultSet.getString("kriteria");
                tampil_daftar.addItem(sa);

            }
        } catch (Exception e) {
        }
    }
      
       private void tampil_kriteria(){
         try {
           sql="select * from kriteria where id_kriteria='"+tampil_daftar.getSelectedItem()+"'";
            statement=(Statement) con.createStatement();
           resultSet=statement.executeQuery(sql);
           while (resultSet.next()) {
            }
            } catch (Exception e) {
          JOptionPane.showMessageDialog(rootPane, statement);
        } 
        }
       
      public String getIdKriteria() {
        String id = "";
        String sql = "SELECT id_kriteria FROM kriteria WHERE kriteria='" + tampil_daftar.getSelectedItem() + "'";
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
       
       
       
//       private void tampil_tahun(){
//           tampil_periode.addItem("");
//        try {
//            resultSet = statement.executeQuery("SELECT tahun FROM periode group by tahun order by tahun");
//            while (resultSet.next()) {
//                String sa = resultSet.getString("tahun");
//                tampil_periode.addItem(sa);
//
//            }
//        } catch (Exception e) {
//        }}
       

 
       /**
     *
     * @return
     */

     public void blnskrg(){
         SimpleDateFormat format = new SimpleDateFormat (" MMM ");
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
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tampil_daftar = new javax.swing.JComboBox();
        tampil_periode = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        id_kp = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        bln = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        thn = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame2.setTitle("Kriteria Per Periode");
        jInternalFrame2.setVisible(true);

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

        jButton2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton2.setText("Edit");
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
        jButton4.setText("Keluar");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Kriteria      :");

        tampil_daftar.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tampil_daftar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tampil_daftarItemStateChanged(evt);
            }
        });
        tampil_daftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tampil_daftarActionPerformed(evt);
            }
        });
        tampil_daftar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tampil_daftarKeyReleased(evt);
            }
        });

        tampil_periode.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tampil_periode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tampil_periodeItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setText("Periode     :");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel1.setText("ID                :");

        id_kp.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        id_kp.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                id_kpPropertyChange(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setText("Bulan          :");

        bln.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                blnPropertyChange(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel5.setText("Tahun");

        thn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                thnPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(bln))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel2)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tampil_daftar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 1, Short.MAX_VALUE))
                            .addComponent(tampil_periode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(thn)
                            .addComponent(id_kp))))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(id_kp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(tampil_periode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(bln, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(thn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tampil_daftar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (tampil_periode.getSelectedItem() == "") {
            JOptionPane.showMessageDialog(null, "Periode Kosong");
        } else if (tampil_daftar.getSelectedItem() == "") {
            JOptionPane.showMessageDialog(null, "Kriteria Kosong");
        } else {
            try {
                String value1 = id_kp.getText();
                Object value2 = tampil_periode.getSelectedItem();
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(this, "Ubah data ini?", "Title on Box", dialogButton);
                if (dialogResult == 0) {
                    String sql = "update kriteria_perperiode set id_periode='" + value2 + "',id_kriteria='" + getIdKriteria() + "' where id_kp='" + value1 + "'";
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
            update_tabel();
            autonomor();
            kosongkan_text();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (tampil_periode.getSelectedItem() == "") {
            JOptionPane.showMessageDialog(null, "Periode Kosong");
        } else if (tampil_daftar.getSelectedItem() == "") {
            JOptionPane.showMessageDialog(null, "Kriteria Kosong");
        } else {
            try {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(this, "Simpan data?", "Title on Box", dialogButton);
                if (dialogResult == 0) {
                String sql;
                sql = "insert into kriteria_perperiode values('" + id_kp.getText() + "','" + tampil_periode.getSelectedItem() + "','" + getIdKriteria() + "')";
                java.sql.Connection conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Berhasil Disimpan");
             } else {
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            update_tabel();
            autonomor();
            kosongkan_text();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tampil_daftarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tampil_daftarKeyReleased

    }//GEN-LAST:event_tampil_daftarKeyReleased

    private void tampil_daftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tampil_daftarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tampil_daftarActionPerformed

    private void tampil_daftarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tampil_daftarItemStateChanged
        tampil_kriteria();
    }//GEN-LAST:event_tampil_daftarItemStateChanged

    private void tampil_periodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tampil_periodeItemStateChanged
        update_tabel();
        tampil_bulantahun();       
    }//GEN-LAST:event_tampil_periodeItemStateChanged

    private void id_kpPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_id_kpPropertyChange
        id_kp.setEnabled(false);        
    }//GEN-LAST:event_id_kpPropertyChange

    private void blnPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_blnPropertyChange
        bln.setEnabled(false);
    }//GEN-LAST:event_blnPropertyChange

    private void thnPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_thnPropertyChange
        thn.setEnabled(false);
    }//GEN-LAST:event_thnPropertyChange

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       try {
            int row = jTable1.getSelectedRow();
            String tabel_klik;
            tabel_klik = (jTable1.getModel().getValueAt(row, 0).toString());
            java.sql.Connection conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet sql = stm.executeQuery("select * from kriteria_perperiode where id_kp='" + tabel_klik + "'");
            if (sql.next()) {
                String add1 = sql.getString("id_kp");
                id_kp.setText(add1);
                Object add2 = sql.getString("id_periode");
                tampil_periode.getSelectedItem();
                String add3 = sql.getString("id_kriteria");
                tampil_daftar.getSelectedItem();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Hapus data ini?", "Title on Box", dialogButton);
            if (dialogResult == 0) {
                String sql = "delete from kriteria_perperiode where id_kp=? ";
                java.sql.Connection conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, id_kp.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Berhasil Dihapus");
            } else {
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        update_tabel();
        autonomor();
        kosongkan_text();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(kriteria_periode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kriteria_periode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kriteria_periode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kriteria_periode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new kriteria_periode().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(kriteria_periode.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bln;
    private javax.swing.JTextField id_kp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JComboBox tampil_daftar;
    private javax.swing.JComboBox tampil_periode;
    private javax.swing.JTextField thn;
    // End of variables declaration//GEN-END:variables
}

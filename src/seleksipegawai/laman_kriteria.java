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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author zuni
 */
public class laman_kriteria extends javax.swing.JFrame {

    koneksi1 koneksi;
    ResultSet resultSet;
    Statement statement;
     public String sql = "";
    private Connection con;
    
// DefaultTableModel tabMode;
  private void update_tabel(){
//    try {
//  java.sql.Connection conn;
//  conn = (java.sql.Connection)seleksipegawai.koneksi1.koneksiDB();
//     java.sql.Statement stm = conn.createStatement();
//    java.sql.ResultSet sql;
//        sql = stm.executeQuery("select kriteria.id_kriteria, kriteria.kriteria,kriteria.id_bobotfuzzy,bobot_fuzzy.id_bobotfuzzy,bobot_fuzzy.huruf,bobot_fuzzy.bobot from kriteria inner join bobot_fuzzy on bobot_fuzzy.id_bobotfuzzy=kriteria.id_bobotfuzzy");
//   jTable1.setModel(DbUtils.resultSetToTableModel(sql));
//   
//    } catch (Exception e) {
//     }}
 Object header[] = {"ID KRITERIA", "NAMA KRITERIA", "ID BOBOT FUZZY","NAMA BOBOT FUZZY", "BOBOT FUZZY"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(null, header);
        jTable1.setModel(defaultTableModel);

        //menghapus tabel sebelum menampilkan data
        int baris = jTable1.getRowCount();
        for (int i = 0; i < baris; i++) {
            defaultTableModel.removeRow(i);
        }


        String sql = "select kriteria.id_kriteria, kriteria.kriteria,kriteria.id_bobotfuzzy,bobot_fuzzy.huruf,bobot_fuzzy.bobot from kriteria inner join bobot_fuzzy on bobot_fuzzy.id_bobotfuzzy=kriteria.id_bobotfuzzy";
        try {
            statement = (Statement) con.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String kolom1 = resultSet.getString(1);
                String kolom2 = resultSet.getString(2);
                String kolom3 = resultSet.getString(3);
                String kolom4 = resultSet.getString(4);
                String kolom5 = resultSet.getString(5);


                String kolom[] = {kolom1, kolom2,kolom3,kolom4,kolom5};
                defaultTableModel.addRow(kolom);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("" + e.getMessage());
        }
  
  }
    /**
     * Creates new form laman_kriteria
     */
    public laman_kriteria() {
        initComponents();
        tampil_bf();
        auto_number();
         update_tabel();
        kosongkan_text();  
    }
    
    public final void tampil_bf(){
    id_bf.addItem("");

      try {
          koneksi();
          resultSet=statement.executeQuery("select id_bobotfuzzy from bobot_fuzzy");
          while (resultSet.next()) {
             String sa = resultSet.getString("id_bobotfuzzy");
             id_bf.addItem(sa);

          }
      } catch (Exception e) {
      }
      
    }
    
    private void koneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/pegawai1", "root", "");

        } catch (Exception e) {
        }
    }
     private void kosongkan_text(){
     id_k.setText("");
     nm_k.setText("");
     id_bf.setSelectedItem("");
     nm_bf.setText("");
      bobot_bf.setText("");
      auto_number();
       }
    
 private void auto_number(){   
 try {
   koneksi();
           String sql ="SELECT MAX(right(id_kriteria,2))AS no from kriteria";
             statement = (Statement) con.createStatement();
          ResultSet rs= statement.executeQuery(sql);
         while (rs.next()) {
               if (rs.first() == false){
                id_k.setText("K001");
               }else{
                    rs.last();
                   int auto_id = rs.getInt(1) + 1;
                  String no = String.valueOf(auto_id);
                   int noLong=no.length();
                   for (int a = 0; a< 3-noLong; a++){
                       no= "0" + no;
                   }
                   id_k.setText("K" + no);
                   
               }
           }
     } catch (Exception e) {
           JOptionPane.showMessageDialog(this, "Error:\n" + e.toString(), "Kesalahan", JOptionPane.WARNING_MESSAGE);

     }
       }
 
 private void tampil_bobot(){
 try {
           koneksi();
           sql="select * from bobot_fuzzy where id_bobotfuzzy='"+id_bf.getSelectedItem()+"'";
            statement=(Statement) con.createStatement();
            resultSet=statement.executeQuery(sql);
           while (resultSet.next()) {
                 nm_bf.setText(resultSet.getString("huruf"));
                  bobot_bf.setText(resultSet.getString("bobot"));
            }
            } catch (Exception e) {
          JOptionPane.showMessageDialog(rootPane, statement);
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

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        id_k = new javax.swing.JTextField();
        nm_k = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nm_bf = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        bobot_bf = new javax.swing.JTextField();
        id_bf = new javax.swing.JComboBox();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setTitle("Kriteria");
        jInternalFrame1.setVisible(true);

        jToolBar1.setRollover(true);
        jToolBar1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

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
        jButton4.setText("Tampilkan Laporan");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
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

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel1.setText("ID Kriteria             :");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Kriteria                  :");

        id_k.setEditable(false);
        id_k.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        nm_k.setBackground(new java.awt.Color(240, 240, 240));
        nm_k.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        nm_k.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nm_kActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setText("ID Bobot Fuzzy    :");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setText("Bobot Fuzzy         :");

        nm_bf.setEditable(false);
        nm_bf.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        nm_bf.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                nm_bfPropertyChange(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel5.setText("Bobot                   :");

        bobot_bf.setEditable(false);
        bobot_bf.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        bobot_bf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bobot_bfActionPerformed(evt);
            }
        });

        id_bf.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        id_bf.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                id_bfItemStateChanged(evt);
            }
        });
        id_bf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_bfKeyReleased(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton7.setText("Batal");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(id_k, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(nm_k))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(id_bf, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(bobot_bf)
                                .addComponent(nm_bf)))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(id_k, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nm_k, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(id_bf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nm_bf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(bobot_bf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "NAMA", "ID BOBOT FUZZY", "NAMA BOBOT FUZZY", "BOBOT FUZZY"
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
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
//tampil_bobot();
//        try {
//  int row =jTable1.getSelectedRow();
//       String tabel_klik;
//          tabel_klik = (jTable1.getModel().getValueAt(row, 0).toString());
//       java.sql.Connection conn =(java.sql.Connection)seleksipegawai.koneksi1.koneksiDB();
//           java.sql.Statement stm = conn.createStatement();
//        java.sql.ResultSet sql = stm.executeQuery("select kriteria.id_kriteria, kriteria.kriteria,kriteria.id_bobotfuzzy, bobot_fuzzy.id_bobotfuzzy,bobot_fuzzy.huruf,bobot_fuzzy.bobot from kriteria inner join bobot_fuzzy on bobot_fuzzy.id_bobotfuzzy=kriteria.id_bobotfuzzy where id_kriteria='"+tabel_klik+"'");
//          if(sql.next()){
//              String add1= sql.getString("id_kriteria");
//              id_k.setText(add1);
//             String add2 = sql.getString("kriteria");
//               nm_k.setText(add2);
//                String add3 = sql.getString("id_bobotfuzzy");
//               id_bf.getSelectedItem();
//               String add4=sql.getString("huruf");
//               nm_bf.setText(add4);
//               String add5=sql.getString("bobot");
//               bobot_bf.setText(add5);
//         }     }
//            catch (Exception e) {
//            }
         int baris=jTable1.getSelectedRow();
        id_k.setText(jTable1.getValueAt(baris,0).toString());
        nm_k.setText(jTable1.getValueAt(baris,1).toString());
        id_bf.setSelectedItem(jTable1.getValueAt(baris, 2).toString());
         nm_bf.setText(jTable1.getValueAt(baris,3).toString());
          bobot_bf.setText(jTable1.getValueAt(baris,4).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void bobot_bfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bobot_bfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bobot_bfActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
             try {
            String sql;
            sql = "insert into kriteria values('"+id_k.getText()+"','"+nm_k.getText()+"','"+id_bf.getSelectedItem()+"')";
            java.sql.Connection conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil Disimpan");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        update_tabel();
        auto_number();
        kosongkan_text();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void id_bfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_bfKeyReleased
 
        // TODO add your handling code here:
    }//GEN-LAST:event_id_bfKeyReleased

    private void id_bfItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_id_bfItemStateChanged
tampil_bobot();
       
    }//GEN-LAST:event_id_bfItemStateChanged

    private void nm_bfPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_nm_bfPropertyChange
               // TODO add your handling code here:
    }//GEN-LAST:event_nm_bfPropertyChange

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            String value1 = id_k.getText();
            String value2 = nm_k.getText();
            Object value3 = id_bf.getSelectedItem();
            String sql ="update kriteria set  kriteria='"+value2+"', id_bobotfuzzy='"+value3+"'where id_kriteria='"+value1+"'";
            java.sql.Connection conn;
            conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            jButton1.setEnabled(false);

            JOptionPane.showMessageDialog(null, "Edit ?");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }  
        
        update_tabel();
        auto_number();
        kosongkan_text();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String sql ="delete from kriteria where id_kriteria=? ";
            java.sql.Connection conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, id_k.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Hapus");
           

        } catch (Exception e) {
        }
        update_tabel();
        auto_number();
        kosongkan_text();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        kosongkan_text();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void nm_kActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nm_kActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nm_kActionPerformed

        
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
            java.util.logging.Logger.getLogger(laman_kriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(laman_kriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(laman_kriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(laman_kriteria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new laman_kriteria().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bobot_bf;
    private javax.swing.JComboBox id_bf;
    private javax.swing.JTextField id_k;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField nm_bf;
    private javax.swing.JTextField nm_k;
    // End of variables declaration//GEN-END:variables
}

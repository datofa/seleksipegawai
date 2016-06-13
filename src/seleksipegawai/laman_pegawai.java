/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seleksipegawai;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.File;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Date;

/**
 *
 * @author zuni
 */
public class laman_pegawai extends javax.swing.JFrame {
    
    koneksi1 koneksi;
    ResultSet resultSet;
    Statement statement;
     public String sql = "";
    private Connection con;
    JasperReport JasRep;
    JasperPrint JasPri;
    Map param=new HashMap();
    JasperDesign JasDes;
    
  DefaultTableModel tabMode;
  private void update_tabel(){
    Object header[] = {"ID", "NAMA", "JK", "TANGGAL LAHIR", "ALAMAT"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(null, header);
        jTable1.setModel(defaultTableModel);

        //menghapus tabel sebelum menampilkan data
        int baris = jTable1.getRowCount();
        for (int i = 0; i < baris; i++) {
            defaultTableModel.removeRow(i);
        }


        String sql = "select id_pegawai,nama, jk, tgl_lahir, alamat from pegawai";
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
 
  
//    /**
//     * Creates new form laman_pegawai
//     */
    public laman_pegawai() {
        initComponents();
         auto_number();
         update_tabel();
        kosongkan_text(); 
       
    }
    
    public void tglskrg(){
         SimpleDateFormat format = new SimpleDateFormat ("dd MMMMM yyyy");
    }
    
//     private void cetak(){
//    try{
//    koneksi();
//     Map<String, Object> param = new HashMap<String, Object>();
//     try{
//         JasperPrint jasperPrint = JasperFillManager.fillReport("src/Laporan/laporan_pegawai.jasper", param, con);
//          param.put("idpeg",id_peg.getText());   
//         JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//         jasperViewer.setTitle("Laporan Data Pegawai");
//         jasperViewer.setVisible(true);
//         
//     }catch (Exception e){
//         JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada "+e);
//         
//     }
//     
//    }catch (Exception e){
//        System.out.println(e);
//    }}
//    
//    public class Fungsi_Cetak{
//        public Fungsi_Cetak(String namaReport){
//            try{
//               koneksi();
//               File report_file= new File(namaReport);
//               JasperReport jasperReport = (JasperReport) JRLoader.loadObject(report_file.getPath());
//               JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null,con);
//               
//               JasperViewer.viewReport(jasperPrint, false);
//               
//            }catch(Exception e){
//                System.out.println(e.getMessage());
//            }
//        }
//    }
    
     private void kosongkan_text(){
           id_peg.setText("");
        nm_peg.setText("");
        jk.setSelectedItem(null);
        tglhr.setDate(null);
        alamat_peg.setText("");
        auto_number();
       }
     
 private void koneksi() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/pegawai1", "root", "");

        } catch (Exception e) {
        }
    }
   private void auto_number(){
       
 try {
   koneksi();
           String sql ="SELECT MAX(right(id_pegawai,2))AS no from pegawai";
             statement = (Statement) con.createStatement();
          ResultSet rs= statement.executeQuery(sql);
         while (rs.next()) {
               if (rs.first() == false){
                id_peg.setText("P001");
               }else{
                    rs.last();
                   int auto_id = rs.getInt(1) + 1;
                  String no = String.valueOf(auto_id);
                   int noLong=no.length();
                   for (int a = 0; a< 3-noLong; a++){
                       no= "0" + no;
                   }
                   id_peg.setText("P" + no);
                   
               }
           }
     } catch (Exception e) {
           JOptionPane.showMessageDialog(this, "Error:\n" + e.toString(), "Kesalahan", JOptionPane.WARNING_MESSAGE);

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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nm_peg = new javax.swing.JTextField();
        jk = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        alamat_peg = new javax.swing.JTextArea();
        id_peg = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tglhr = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setTitle("Pegawai");
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
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton1.setText("Keluar");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel6.setText("ID                               :");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel7.setText("Nama                       :");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel8.setText("Jenis Kelamin        :");

        nm_peg.setBackground(new java.awt.Color(240, 240, 240));
        nm_peg.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jk.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-laki", "Perempuan" }));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel1.setText("Alamat                   :");

        alamat_peg.setBackground(new java.awt.Color(240, 240, 240));
        alamat_peg.setColumns(20);
        alamat_peg.setRows(5);
        jScrollPane2.setViewportView(alamat_peg);

        id_peg.setEditable(false);
        id_peg.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        id_peg.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                id_pegPropertyChange(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jButton7.setText("Batal");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Tanggal Lahir        :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(id_peg))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jk, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(190, 190, 190))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(nm_peg))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(tglhr, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(id_peg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(nm_peg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(tglhr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                "ID", "NAMA", "JENIS KELAMIN", "ALAMAT"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jInternalFrame1)
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

     try {
  int row =jTable1.getSelectedRow();
       String tabel_klik;
          tabel_klik = (jTable1.getModel().getValueAt(row, 0).toString());
       java.sql.Connection conn =(java.sql.Connection)seleksipegawai.koneksi1.koneksiDB();
           java.sql.Statement stm = conn.createStatement();
        java.sql.ResultSet sql = stm.executeQuery("select * from pegawai where id_pegawai='"+tabel_klik+"'");
          if(sql.next()){
              String add1= sql.getString("id_pegawai");
              id_peg.setText(add1);
             String add2 = sql.getString("nama");
               nm_peg.setText(add2);
                String add3 = sql.getString("jk");
               jk.getSelectedItem();
                Date add4 = sql.getDate("tgl_lahir");
               tglhr.setDate(add4);
              String add5 = sql.getString("alamat");
          alamat_peg.setText(add5);
             
         }     }
            catch (Exception e) {
            }
//        
        
//        id_peg.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
//     nm_peg.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
//        jk.(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       java.util.Date tanggal= (java.util.Date) this.tglhr.getDate();
         String jeniska="L";
        if (jk.getSelectedItem().equals("Laki-laki")) {
            jeniska="L";
        } else {
            jeniska= "P";
        }
        
         String nama="",alamat="";
         if(nm_peg.getText().equals(""))
     {
         JOptionPane.showConfirmDialog(null, "Nama Pegawai Tidak Boleh Kosong");
        
     }else if(alamat_peg.getText().equals("")) 
     {
         JOptionPane.showConfirmDialog(null,"Alamat Pegawai Tidak Boleh Kosong" );
     }else
     {
         try {
            String sql;
            sql = "insert into pegawai values('"+id_peg.getText()+"','"+nm_peg.getText()+"','"+jeniska+"','"+new java.sql.Date(tanggal.getTime())+"','"+alamat_peg.getText()+"')";
            System.out.println(sql);
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
//        tampilanadministrator();
       
     }   
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         String jeniska="L";
        if (jk.getSelectedItem().equals("Laki-laki")) {
            jeniska="L";
        } else {
            jeniska= "P";
        }
//        try {
            String value1 = id_peg.getText();
            String value2 = nm_peg.getText();
            java.util.Date tanggal = (java.util.Date) this.tglhr.getDate();
            String value4=alamat_peg.getText();
            String sql;
            sql ="update pegawai set  nama='"+value2+"',jk='"+jeniska+"', tgl_lahir='"+new java.sql.Date(tanggal.getTime())+"',alamat='"+value4+"' where id_pegawai='"+value1+"'";
            System.out.println(sql);
//            java.sql.Connection conn;
//            conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
//            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
//            pst.execute();
//            JOptionPane.showMessageDialog(null, "Edit ?");
//        } catch (Exception e) {          
//    JOptionPane.showMessageDialog(null, "Error");
//        }  
//        update_tabel();
//        auto_number();
//        kosongkan_text();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
//        try{
//            File rprt =new File("laporan_pegawai.jasper");
//            JasDes=JRXmlLoader.load(rprt);
//            param.clear();
//            JasRep=JasperCompileManager.compileReport(JasDes);
//            JasPri=JasperFillManager.fillReport(JasRep, param, con);
//            JasperViewer.viewReport(JasPri, false);
//        }catch (Exception e){
//            JOptionPane.showMessageDialog(null, e);
//        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
  try {
            String sql ="delete from pegawai where id_pegawai=? ";
            java.sql.Connection conn = (java.sql.Connection) seleksipegawai.koneksi1.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, id_peg.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Hapus");
           

        } catch (Exception e) {
        }
        update_tabel();
        auto_number();
        kosongkan_text();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
kosongkan_text();        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void id_pegPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_id_pegPropertyChange

    }//GEN-LAST:event_id_pegPropertyChange

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
            java.util.logging.Logger.getLogger(laman_pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(laman_pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(laman_pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(laman_pegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new laman_pegawai().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamat_peg;
    private javax.swing.JTextField id_peg;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JComboBox jk;
    private javax.swing.JTextField nm_peg;
    private com.toedter.calendar.JDateChooser tglhr;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seleksipegawai;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.*;


/*import org.json.simple.JSONObject;*/
/**
 *
 * @author zuni
 */
public class laman_seleksi extends javax.swing.JFrame {

    ResultSet resultSet, resultSet1, resultSet2;
    Statement statement, statement1, statement2;
    public String sql = "";
    private Connection con;

    /**
     * Creates new form laman_seleksi
     */
    public laman_seleksi() {
        koneksi1 koneksi = new koneksi1();
        con = (Connection) koneksi.koneksi();
        initComponents();
        pilih_periode();
//        System.out.print(jumlah_kriteria_perperiode("PRD001"));

//        update_tabel();
    }

//      DefaultTableModel tabMode;
//  private void update_tabel(){
//    try {
//  java.sql.Connection conn;
//  conn = (java.sql.Connection)seleksipegawai.koneksi1.koneksiDB();
//
//     java.sql.Statement stm = conn.createStatement();
//    java.sql.ResultSet sql;
//    sql = stm.executeQuery("");
////        sql = stm.executeQuery("Select * from bobot_kriteria where id_kriteria like '%" + id_k.getSelectedItem()+ "%'");
//   jTable1.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(sql));
//   
//    } catch (Exception e) {
//     }}
//    private Object[] data_header() {
    public Object[] data_header(String id_periode) {
//        String id = "PRD001";
        int j_data = 3;
        int j_kolom = 0;
        int j_header = 0;
//        if (pilih_periode.getSelectedItem() != ""){

        String sql_data = "SELECT kriteria FROM kriteria_perperiode a "
                + "LEFT JOIN kriteria b ON a.id_kriteria=b.id_kriteria "
                + "WHERE a.id_periode='" + id_periode + "' ORDER BY b.id_kriteria";
        String sql_count = "SELECT count(*) FROM kriteria_perperiode a "
                + "LEFT JOIN kriteria b ON a.id_kriteria=b.id_kriteria "
                + "WHERE a.id_periode='" + id_periode + "'";
        if (id_periode != "" && id_periode != null) {
            try {
                statement = (Statement) con.createStatement();
                resultSet = statement.executeQuery(sql_count);

                while (resultSet.next()) {
                    j_kolom = Integer.parseInt(resultSet.getString(1));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.out.println("" + e.getMessage());
            }
        }

        j_header = j_data + j_kolom;

        Object[] header = new Object[j_header];

        header[0] = "Id Pegawai";
        header[1] = "Nama";
        header[2] = "JK";

        if (id_periode != "" && id_periode != null) {
            try {
                statement = (Statement) con.createStatement();
                resultSet = statement.executeQuery(sql_data);

                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1));
                    header[j_data] = resultSet.getString(1);
                    j_data++;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.out.println("" + e.getMessage());
            }
        }

        return header;
    }

    public int jumlah_kriteria_perperiode(String id_periode) {
        int jumlah = 0;
        if (id_periode != "" && id_periode != null) {
            String sql = "SELECT count(*) as jumlah from kriteria_perperiode where id_periode='"+id_periode+"'";
            try {
                statement2 = (Statement) con.createStatement();
                resultSet2 = statement2.executeQuery(sql);
                while (resultSet2.next()) {
                    jumlah = Integer.parseInt(resultSet2.getString(1));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.out.println("" + e.getMessage());
            }
        }
        return jumlah;
    }

    private void tabel_data_mentah() {
        if (pilih_periode.getSelectedItem() != "") {
            String id_periode = (String) pilih_periode.getSelectedItem();
            int j_data = 0;
            j_data = jumlah_kriteria_perperiode(id_periode);
            Object header[] = data_header((String) pilih_periode.getSelectedItem());
            DefaultTableModel defaultTableModel = new DefaultTableModel(null, header);
            jTable1.setModel(defaultTableModel);

            //menghapus tabel sebelum menampilkan data
            int baris = jTable1.getRowCount();
            for (int i = 0; i < baris; i++) {
                defaultTableModel.removeRow(i);
            }
            String sql = "SELECT id_pegawai, nama, jk FROM pegawai";
            try {
                statement = (Statement) con.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int j_kolom_awal = 3;
                    
                    int j_kolom = j_kolom_awal + j_data;
                    String[] kolom = new String[j_kolom];
                    kolom[0] = resultSet.getString(1);
                    kolom[1] = resultSet.getString(2);
                    kolom[2] = resultSet.getString(3);
                    String sql_nilai = "SELECT nilai from bk_pegawai "
                            + "where id_pegawai='" + kolom[0] + "' AND id_periode='" + id_periode + "' ORDER BY id_kriteria";
                    try {
                        statement1 = (Statement) con.createStatement();
                        resultSet1 = statement1.executeQuery(sql_nilai);
                        while (resultSet1.next()) {
                            kolom[j_kolom_awal] = resultSet1.getString(1);
                            j_kolom_awal++;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                        System.out.println("" + e.getMessage());
                    }
                    defaultTableModel.addRow(kolom);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.out.println("" + e.getMessage());
            }
        }
    }
    
    private void tabel_data_matrik() {
        if (pilih_periode.getSelectedItem() != "") {
            String id_periode = (String) pilih_periode.getSelectedItem();
            int j_data = 0;
            j_data = jumlah_kriteria_perperiode(id_periode);
            Object header[] = data_header((String) pilih_periode.getSelectedItem());
            DefaultTableModel defaultTableModel = new DefaultTableModel(null, header);
            jTable2.setModel(defaultTableModel);

            //menghapus tabel sebelum menampilkan data
            int baris = jTable2.getRowCount();
            for (int i = 0; i < baris; i++) {
                defaultTableModel.removeRow(i);
            }
            String sql = "SELECT id_pegawai, nama, jk FROM pegawai";
            try {
                statement = (Statement) con.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int j_kolom_awal = 3;
                    
                    int j_kolom = j_kolom_awal + j_data;
                    String[] kolom = new String[j_kolom];
                    kolom[0] = resultSet.getString(1);
                    kolom[1] = resultSet.getString(2);
                    kolom[2] = resultSet.getString(3);
                    String sql_nilai = "SELECT nilai_bk from bk_pegawai "
                            + "where id_pegawai='" + kolom[0] + "' AND id_periode='" + id_periode + "' ORDER BY id_kriteria";
                    try {
                        statement1 = (Statement) con.createStatement();
                        resultSet1 = statement1.executeQuery(sql_nilai);
                        while (resultSet1.next()) {
                            kolom[j_kolom_awal] = resultSet1.getString(1);
                            j_kolom_awal++;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                        System.out.println("" + e.getMessage());
                    }
                    defaultTableModel.addRow(kolom);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.out.println("" + e.getMessage());
            }
        }
    }
    
    private void tabel_data_normalisasi() {
        if (pilih_periode.getSelectedItem() != "") {
            String id_periode = (String) pilih_periode.getSelectedItem();
            int j_data = 0;
            j_data = jumlah_kriteria_perperiode(id_periode);
            Object header[] = data_header((String) pilih_periode.getSelectedItem());
            DefaultTableModel defaultTableModel = new DefaultTableModel(null, header);
            jTable3.setModel(defaultTableModel);

            //menghapus tabel sebelum menampilkan data
            int baris = jTable3.getRowCount();
            for (int i = 0; i < baris; i++) {
                defaultTableModel.removeRow(i);
            }
            String sql = "SELECT id_pegawai, nama, jk FROM pegawai";
            try {
                statement = (Statement) con.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int j_kolom_awal = 3;
                    
                    int j_kolom = j_kolom_awal + j_data;
                    String[] kolom = new String[j_kolom];
                    kolom[0] = resultSet.getString(1);
                    kolom[1] = resultSet.getString(2);
                    kolom[2] = resultSet.getString(3);
                    String sql_nilai = "SELECT a.nilai_bk/b.n_max from bk_pegawai a LEFT JOIN n_max b ON a.id_pegawai=b.id_pegawai "
                            + "where a.id_pegawai='" + kolom[0] + "' AND a.id_periode='" + id_periode + "' GROUP BY a.id_periode,a.id_pegawai, a.id_kriteria ORDER BY a.id_pegawai ASC";
                    try {
                        statement1 = (Statement) con.createStatement();
                        resultSet1 = statement1.executeQuery(sql_nilai);
                        while (resultSet1.next()) {
                            kolom[j_kolom_awal] = resultSet1.getString(1);
                            j_kolom_awal++;
                        }
                    } catch (Exception e) {
//                        JOptionPane.showMessageDialog(null, e.getMessage());
                        System.out.println("" + e.getMessage());
                    }
                    defaultTableModel.addRow(kolom);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.out.println("" + e.getMessage());
            }
        }
    }
    
    private void tabel_data_hasil_akhir() {
        if (pilih_periode.getSelectedItem() != "") {
            String id_periode = (String) pilih_periode.getSelectedItem();
            int j_data = 0;
            j_data = jumlah_kriteria_perperiode(id_periode);
            Object header[] = data_header((String) pilih_periode.getSelectedItem());
            DefaultTableModel defaultTableModel = new DefaultTableModel(null, header);
            jTable4.setModel(defaultTableModel);

            //menghapus tabel sebelum menampilkan data
            int baris = jTable4.getRowCount();
            for (int i = 0; i < baris; i++) {
                defaultTableModel.removeRow(i);
            }
            String sql = "SELECT id_pegawai, nama, jk FROM pegawai ";
            try {
                statement = (Statement) con.createStatement();
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int j_kolom_awal = 3;
//                   int j_kolom_akhir=1;
                    int j_kolom = j_kolom_awal + j_data;
                    String[] kolom = new String[j_kolom];
                    kolom[0] = resultSet.getString(1);
                    kolom[1] = resultSet.getString(2);
                    kolom[2] = resultSet.getString(3);
                    String sql_nilai = "SELECT hasil_kali FROM hasil_kali "
                            + "where id_pegawai='" + kolom[0] + "' AND id_periode='" + id_periode + "'";
                    try {
                        statement1 = (Statement) con.createStatement();
                        resultSet1 = statement1.executeQuery(sql_nilai);
                        while (resultSet1.next()) {
                            kolom[j_kolom_awal] = resultSet1.getString(1);
                            j_kolom_awal++;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                        System.out.println("" + e.getMessage());
                    }
                    defaultTableModel.addRow(kolom);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.out.println("" + e.getMessage());
            }
        }
    }

    public final void pilih_periode() {
        pilih_periode.addItem("");
        try {
            resultSet = statement.executeQuery("select id_periode from kriteria_perperiode group by id_periode");
            while (resultSet.next()) {
                String sa = resultSet.getString("id_periode");
                pilih_periode.addItem(sa);

            }
        } catch (Exception e) {
        }
    }

    private void pilihperiode() {
        try {
            sql = "select * from kriteria_perperiode where id_periode='" + pilih_periode.getSelectedItem() + "' GROUP BY id_periode";
            statement = (Statement) con.createStatement();
            resultSet = statement.executeQuery(sql);
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
        pilih_periode = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setVisible(true);

        pilih_periode.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pilih_periodeItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel1.setText("Periode    :");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel2.setText("Bobot Kriteria Per Pegawai");

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
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
        jTable1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jTable1ComponentResized(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel3.setText("Rating Kecocokan (Matriks Keputusan)");

        jTable2.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton1.setText("Tampil");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel4.setText("Normalisasi Matriks Keputusan");

        jTable3.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable3);

        jButton2.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton2.setText("Tampil");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel5.setText("Hasil Akhir");

        jTable4.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable4);

        jButton3.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton3.setText("Tampil");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton3KeyPressed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton4.setText("Tampilkan Laporan");

        jButton5.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton5.setText("Keluar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(282, 282, 282)
                        .addComponent(jButton2))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pilih_periode, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton5)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jInternalFrame1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 31, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(pilih_periode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 22, Short.MAX_VALUE)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jButton2))
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jButton1))
                        .addGap(3, 3, 3)))
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
tabel_data_hasil_akhir();      // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void pilih_periodeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pilih_periodeItemStateChanged
        pilihperiode();
        tabel_data_mentah();
    }//GEN-LAST:event_pilih_periodeItemStateChanged

    private void jTable1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTable1ComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1ComponentResized

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton3KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        tabel_data_matrik();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        tabel_data_normalisasi();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(laman_seleksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(laman_seleksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(laman_seleksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(laman_seleksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new laman_seleksi().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JComboBox pilih_periode;
    // End of variables declaration//GEN-END:variables
}

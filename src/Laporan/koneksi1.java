/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Laporan;

import seleksipegawai.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author zuni
 */
public class koneksi1 {

    Connection con;
    private static Connection mysqlkonek;
    private static String DB_NAME = "pegawai1";
    private static String USER = "root";
    private static String PASS = "";
    public static Connection koneksiDB() throws SQLException {
        if (mysqlkonek == null) {
            try {
                String url = "jdbc:mysql://localhost/"+DB_NAME;
                String user = USER;
                String pass = PASS;
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                mysqlkonek = (Connection) DriverManager.getConnection(url, user, pass);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal Koneksi");
            }


        }
        return mysqlkonek;
    }
    public Connection koneksi(){
    try {
        Class.forName("com.mysql.jdbc.Driver");
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/"+DB_NAME, USER, PASS);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Koneksi");
        }
        return con;
    }
}

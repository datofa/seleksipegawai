/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seleksipegawai;

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
public static Connection koneksiDB() throws SQLException{
      if(mysqlkonek==null){
          try{
          String url= "jdbc:mysql://localhost/pegawai1";
          String user="spk";
          String pass="spk";
          DriverManager.registerDriver(new com.mysql.jdbc.Driver());
          mysqlkonek = (Connection) DriverManager.getConnection(url,user,pass);
          
      }
      catch(Exception e){
          JOptionPane.showMessageDialog(null, "Gagal Koneksi");
      }
     
  
} return mysqlkonek;
}

}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class koneksi {
    
    Connection Koneksi=null;
    public static Connection koneksiDb(){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection koneksi =  DriverManager.getConnection("jdbc:mysql://localhost/db_library","root","");
            return koneksi;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }    
}

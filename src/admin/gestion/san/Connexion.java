/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.gestion.san;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class Connexion {
    private static String chemin = "jdbc:mysql://localhost:3306/JavaGestionArticle";
    public static Connection Connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("classfroname "+e.getMessage());
        }
        Connection conn;
        conn = null;
        try {
            conn = DriverManager.getConnection(chemin,"root","");
        } catch (SQLException e) {
            System.out.println("Drivermangerconnection"+e.getMessage());
        }
        /*System.out.println(conn);*/
        return conn;
    }
    
}

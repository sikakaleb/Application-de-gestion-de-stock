/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.dao.san;

import admin.entities.san.Admin;
import admin.entities.san.Mouvement;
import admin.gestion.san.Connexion;
import net.proteanit.sql.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static admin.gestion.san.FArticle.*;

/**
 *
 * @author HP
 */
public class UtilisateurDao {
    public static boolean LoginAdmin(String username, char[] password) {
        String pwd = String.valueOf(password);
        boolean resultat;
        resultat = false;
        String request;
        request = " Select * From Admin Where username = ? and password =?";
        PreparedStatement statement = null;
        Connection connect = Connexion.Connect();
        try {
            statement = connect.prepareStatement(request);
        } catch (SQLException e) {
            System.out.println("stement error55 " + e.getMessage());
        }
        try {
            statement.setString(1, username);
            statement.setString(2, pwd);
        } catch (Exception e) {
            System.out.println("stament build " + e.getLocalizedMessage());
        }
        ResultSet res = null;
        try {
            res = statement.executeQuery();
            if (res.next()) {
                resultat = true;
            }
        } catch (Exception e) {
            System.out.println("exucute error " + e.getMessage());
        }
        return resultat;
    }

    public static ArrayList<Integer> Update_JTable(String temp) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        String req = temp;
        if (temp == " ") {
            String request = "Select codeArt, libelle, prix, qteStock, dateAjout From Article Where disponible =true order by codeArt desc ";
            PreparedStatement statement = null;
            Connection connect = Connexion.Connect();
            ResultSet result = null;
            try {
                statement = connect.prepareStatement(request);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                result = statement.executeQuery();
            } catch (Exception e) {
                System.out.println("exucute error " + e.getMessage());
            }
            jTable.setModel(DbUtils.resultSetToTableModel(result));
            try {
                while (result.next()) {
                    list.add(result.getInt(1));
                }
                connect.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            return list;
        } else {
            String request = "Select codeArt, libelle, prix, qteStock, dateAjout From Article  where libelle like  ? and disponible =true  order by codeArt desc ";
            PreparedStatement statement = null;
            Connection connect = Connexion.Connect();
            ResultSet res = null;
            try {
                statement = connect.prepareStatement(request);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                statement.setString(1, "%" + req + "%");
                res = statement.executeQuery();
            } catch (Exception e) {
                System.out.println("exucute error " + e.getMessage());
            }
            jTable.setModel(DbUtils.resultSetToTableModel(res));
            try {
                while (res.next()) {
                    list.add(res.getInt(1));
                }
                connect.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            return list;
        }

    }

    public static Void ListeSeuil() {

        String request = "Select codeArt, libelle, prix, qteStock, dateAjout From Article where qteStock < seuil and disponible=? ";
        PreparedStatement statement = null;
        Connection connect = Connexion.Connect();
        ResultSet res = null;
        try {
            statement = connect.prepareStatement(request);
            statement.setBoolean(1, true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            res = statement.executeQuery();
        } catch (Exception e) {
            System.out.println("exucute error " + e.getMessage());
        }
        jTable.setModel(DbUtils.resultSetToTableModel(res));
        try {
            connect.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static Void InnitFu() {
        String request = "Select sum(qteStock) as Sq , sum(prix*qteStock) as Sp From Article where disponible=?  ";
        PreparedStatement statement = null;
        Connection connect = Connexion.Connect();
        ResultSet res = null;
        String r1 = null, r2 = null;

        try {
            statement = connect.prepareStatement(request);
            statement.setBoolean(1, true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            res = statement.executeQuery();
        } catch (Exception e) {
            System.out.println("exucute error " + e.getMessage());
        }
        try {
            if (res.next()) {
                r1 = res.getInt(1) + "";
                r2 = res.getDouble(2) + "";

            }

        } catch (Exception e) {
            System.out.println("EZO GBLé " + e.getMessage());
        }

        jLabTQte.setText(r1);
        jLabTprix.setText(r2);
        return null;

    }

    public static Void On_Click() {
        int row = jTable.getSelectedRow();
        int temp = list.indexOf(row);
        String request = " Select * from Article Where codeArt =?";
        PreparedStatement statement = null;
        ResultSet res = null;
        try {
            Connection connect = Connexion.Connect();
            statement = connect.prepareStatement(request);
            statement.setInt(1, temp);
            res = statement.executeQuery();
        } catch (Exception e) {
            System.out.println("EZO GBLé " + e.getMessage());
        }
        try {
            if (res.next()) {
                jTextMoLib.setText(res.getString(2) + "");
                jTextMoPrix.setText(res.getDouble(3) + "");
                jTextMoQte.setText(res.getInt(5) + "");
                jDateMoCho.setDate(res.getDate(4));
            }
        } catch (Exception e) {
            System.out.println("EZO GBLé " + e.getMessage());
        }
        return null;

    }

    public static int Create_Admin(Admin admin) {
        int resultat;
        resultat = 0;
        String request;
        request = "Insert INTO Admin(  username, password ) Values( ?,?)";
        PreparedStatement statement = null;
        Connection connect = Connexion.Connect();
        try {
            statement = connect.prepareStatement(request);
        } catch (Exception e) {
            System.out.println("1statement" + e.getMessage());
        }

        try {
            statement.setString(1, admin.getAdminName());
            statement.setString(2, admin.getPassword());
        } catch (Exception e) {
            System.out.println("2121 insert" + e.getMessage());
        }

        try {
            resultat = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("54526 executeupdate" + e.getMessage());
        }
        try {
            connect.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultat;
    }

    public static int Create_MVT(Mouvement admin) {
        int resultat;
        resultat = 0;
        String request;
        request = "Insert INTO Mouvement(   codeArt, operation,dateMVT,qte ) Values( ?,?,?,?)";
        PreparedStatement statement = null;
        Connection connect = Connexion.Connect();
        try {
            statement = connect.prepareStatement(request);
        } catch (Exception e) {
            System.out.println("1statement" + e.getMessage());
        }

        try {
            // statement.setInt(1, admin.getIdMVT());
            statement.setInt(1, admin.getCodeArt());
            statement.setString(2, admin.getOperation());
            statement.setDate(3, new java.sql.Date(admin.getDate().getTime()));
            statement.setInt(4, admin.getQte());
        } catch (Exception e) {
            System.out.println("2121 insert" + e.getMessage());
        }

        try {
            resultat = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("54526 execute utili update" + e.getMessage());
        }
        try {
            connect.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultat;
    }

    public static int Temp() {
        int resultat;
        resultat = 0;
        String request;
        request = "Select MAX(codeArt) From Article";
        PreparedStatement statement = null;
        ResultSet res = null;
        Connection connect = Connexion.Connect();
        try {
            statement = connect.prepareStatement(request);
        } catch (Exception e) {
            System.out.println("1statement" + e.getMessage());
        }

        try {
            res = statement.executeQuery();
            if (res.next()) {
                resultat = res.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("54526 execute utili update" + e.getMessage());
        }
        try {
            connect.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return resultat;
    }

    public static Void VenteListe() {

        String request = "Select A.codeArt, A.libelle, A.prix, M.qte, M.dateMVT From Article A , Mouvement M where A.codeArt=M.codeArt and operation=? "
                +"and M.qte is not null";
        PreparedStatement statement = null;
        Connection connect = Connexion.Connect();
        ResultSet res = null;
        try {
            statement = connect.prepareStatement(request);
            statement.setString(1, "SALE");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            res = statement.executeQuery();
        } catch (Exception e) {
            System.out.println("exucute error " + e.getMessage());
        }
        jTableVente.setModel( DbUtils.resultSetToTableModel(res));
        try {
            connect.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}

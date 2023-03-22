package admin.dao.san;


import admin.entities.san.Article;
import admin.gestion.san.Connexion;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class ArticleDao {
    
    public static int Create(Article article){
        int resultat; 
        resultat = 0;
        String request;
        request = "Insert INTO Article(  libelle, prix , dateAjout, qteStock, seuil, disponible) Values( ?,?,?,?,?,?)";
        PreparedStatement statement = null;
        Connection connect = Connexion.Connect();
        try {
            statement = connect.prepareStatement(request);
        } catch (Exception e) {
            System.out.println("1statement"+e.getMessage());
        }
        
        try {
            statement.setString(1, article.getLibelle());
            statement.setDouble(2, article.getPrix());
            statement.setDate(3, new java.sql.Date(article.getDate_ajout().getTime() ) );
            statement.setInt(4, article.getQte_stock());
            statement.setInt(5, article.getSEUIL());
            statement.setBoolean(6, article.isDisponible());
        } catch (Exception e) {
            System.out.println("2121 insert"+e.getMessage());
        }
        
        try {
            resultat = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("54526 execute update"+e.getMessage());
        }
        try {
            connect.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return resultat;
    }
    
    public static boolean existsArticles(String libelle, double price){
        boolean resultat;
        resultat = false;
        String request;
        request = "Select codeArt, libelle, prix from Article Where libelle =? and  prix = ? ";
        PreparedStatement statement = null;
        Connection connect = Connexion.Connect();
        ResultSet res = null;
        try {
            statement = connect.prepareStatement(request);
        } catch (Exception e) {
            System.out.println("89523"+e.getMessage());
        }
        try {
            statement.setString(1, libelle);
            statement.setDouble(2, price);
            
        } catch (Exception e) {
            System.out.println("44125"+e.getMessage());
        }
        
        try {
            res = statement.executeQuery();
            if(res.next()){
                resultat =true;
            }
        } catch (SQLException e) {
            System.out.println("58612"+e.getMessage());
        }
        try {
            connect.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return resultat;
    }
    public static Article Read(int code ){
        Article article = new Article();
        boolean resultat = false;
        String request;
        request = "Select * from Article where codeArt =?";
        PreparedStatement statement = null;
        Connection connect = Connexion.Connect();
        ResultSet res= null;
        try {
            statement = connect.prepareStatement(request);
        } catch (Exception e) {
            System.out.println("333."+e.getMessage());
        }
        try {
            statement.setInt(1, code);
        } catch (Exception e) {
            System.out.println("444."+e.getMessage());
        }
        try {
           res= statement.executeQuery();
           if(res.next()){
               article.setCode(res.getInt(1));
               article.setLibelle(res.getString(2));
               article.setPrix(res.getDouble(3));
               article.setDate_ajout(res.getDate(4));
               article.setQte_stock(res.getInt(5));
               article.setDisponible(res.getBoolean(7));               
           }
            
        } catch (Exception e) {
            System.out.println("555"+e.getMessage());
        }
        try {
            connect.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
         return article;   
    }
   
    public static int Update( Article article,int nbre){
        int resultat;
        resultat = 0;
        String request =null;
        if(nbre==1){
             request = "UPDATE Article  SET libelle =?, prix=?  WHERE Article.codeArt = ? and disponible=?";
        }else  if(nbre==2){
             request = "UPDATE Article SET qteStock=?  WHERE Article.codeArt = ? and disponible=?";
        }else if(nbre==3){
             request = "UPDATE Article SET qteStock=?  WHERE Article.codeArt = ? and disponible=?";
        }
        
        PreparedStatement statement =null;
        Connection connect = Connexion.Connect();
        try {
            statement = connect.prepareStatement(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            if (nbre==1){
            statement.setString(1, article.getLibelle());
            statement.setDouble(2, article.getPrix());
           // statement.setDate(3, (java.sql.Date)  article.getDate_ajout());
           // statement.setInt(4, article.getQte_stock());
            statement.setInt(3, article.getCode());
            statement.setBoolean(4, article.isDisponible());
            }else if (nbre==2){
            //statement.setString(1, article.getLibelle());
            //statement.setDouble(2, article.getPrix());
           // statement.setDate(3, (java.sql.Date)  article.getDate_ajout());
           statement.setInt(1, article.getQte());
            statement.setInt(2, article.getCode());
            statement.setBoolean(3, article.isDisponible());
            } else if (nbre==3){
            //statement.setString(1, article.getLibelle());
            //statement.setDouble(2, article.getPrix());
           // statement.setDate(3, (java.sql.Date)  article.getDate_ajout());
           statement.setInt(1, article.getQte());
            statement.setInt(2, article.getCode());
             statement.setBoolean(3, article.isDisponible());
            }
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
        try {
            resultat = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       try {
            connect.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultat;
    }
    
    public static int Delete(int code){
        int result =0;
        String request;
        request = "Delete From Article Where codeArt=? and disponible =true";
        PreparedStatement statement = null;
        Connection connect= Connexion.Connect();
        try {
           statement = connect.prepareStatement(request);
           statement.setInt(1, code);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            result = statement.executeUpdate();
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
        try {
            connect.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    public static boolean Delete(Article article){
        int result=0;
        boolean bool = false;
        String request ="Update Article Set disponible =? where codeArt=?";
        PreparedStatement statement = null;
        Connection connect = Connexion.Connect();
        try {
            statement = connect.prepareStatement(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
         try {
             statement.setBoolean(1, false);
            statement.setInt(2, article.getCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            result = statement.executeUpdate();
            if(result !=0){
                bool =true;
            }
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
        try {
            connect.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bool;
    }
    
}

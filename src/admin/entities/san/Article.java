/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.entities.san;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Article {
    private int code;
    private String libelle;
    private double prix;
    private int qte;
    private Date date_ajout;
    private int qte_stock = 0 ;
    private boolean disponible = false;
    private static int  nbrearticle =0;
    private final static int SEUIL =5;

    public Article() {
         this.code=++nbrearticle;
    }
    

    public Article(int code, String libelle, double prix, int qte, Date date_ajout) {
        this.code = code;
        this.libelle = libelle;
        this.prix = prix;
        this.qte = qte;
        this.date_ajout = date_ajout;
        this.disponible = true;
    }

    public Article(String libelle, double prix, int qte, Date date_ajout) {
        this.code=++nbrearticle;
        this.libelle = libelle;
        this.prix = prix;
        this.qte = qte;
        this.qte_stock+=qte;
        this.date_ajout = date_ajout;
        this.disponible = true;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }

    public int getQte_stock() {
        return qte_stock;
    }

    public void setQte_stock(int qte_stock) {
        this.qte_stock = qte_stock;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public static int getNbrearticle() {
        return nbrearticle;
    }

    public static void setNbrearticle(int nbrearticle) {
        Article.nbrearticle = nbrearticle;
    }

    @Override
    public String toString() {
        return "Articles{" + "code=" + code + ", libelle=" + libelle + ", prix=" + prix + ", qte=" + qte + ", date_ajout=" + date_ajout + ", qte_stock=" + qte_stock + ", disponible=" + disponible + '}';
    }

  

    public int getSEUIL() {
        return SEUIL; //To change body of generated methods, choose Tools | Templates.
    }

    public Object getDateCreation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }

    


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
public class Mouvement {
    private int idMVT;
    private int codeArt;
    private int qte;
    private String operation;
    private Date date;
    private static int nbreMVT=0;

   

    public Mouvement( int codeArt, String operation, Date date) {
        this.idMVT=++nbreMVT;
        this.codeArt = codeArt;
        this.operation = operation;
        this.date = date;
    }

    public Mouvement(int codeArt, int qte, String operation, Date date) {
        this.idMVT=++nbreMVT;
        this.codeArt = codeArt;
        this.qte = qte;
        this.operation = operation;
        this.date = date;
    }
    
    

    public int getIdMVT() {
        return idMVT;
    }

    public void setIdMVT(int idMVT) {
        this.idMVT = idMVT;
    }

    public int getCodeArt() {
        return codeArt;
    }

    public void setCodeArt(int codeArt) {
        this.codeArt = codeArt;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static int getNbreMVT() {
        return nbreMVT;
    }

    public static void setNbreMVT(int nbreMVT) {
        Mouvement.nbreMVT = nbreMVT;
    }

   
    
}

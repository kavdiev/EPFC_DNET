package model;
// Generated Apr 22, 2013 9:08:56 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Location generated by hbm2java
 */
public class Location  implements java.io.Serializable {


     private Integer idlocation;
     private Bienimmo bienimmo;
     private User user;
     private Date date;
     private int prix;

    public Location() {
    }

    public Location(Bienimmo bienimmo, User user, Date date, int prix) {
       this.bienimmo = bienimmo;
       this.user = user;
       this.date = date;
       this.prix = prix;
    }
   
    public Integer getIdlocation() {
        return this.idlocation;
    }
    
    public void setIdlocation(Integer idlocation) {
        this.idlocation = idlocation;
    }
    public Bienimmo getBienimmo() {
        return this.bienimmo;
    }
    
    public void setBienimmo(Bienimmo bienimmo) {
        this.bienimmo = bienimmo;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public int getPrix() {
        return this.prix;
    }
    
    public void setPrix(int prix) {
        this.prix = prix;
    }




}



package model;
// Generated Apr 22, 2013 9:08:56 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Bienimmo generated by hbm2java
 */
public class Bienimmo  implements java.io.Serializable {


     private Integer idBienImmo;
     private User user;
     private int nbChambres;
     private byte garage;
     private byte piscine;
     private int postcode;
     private Set locations = new HashSet(0);

    public Bienimmo() {
    }

	
    public Bienimmo(User user, int nbChambres, byte garage, byte piscine, int postcode) {
        this.user = user;
        this.nbChambres = nbChambres;
        this.garage = garage;
        this.piscine = piscine;
        this.postcode = postcode;
    }
    public Bienimmo(User user, int nbChambres, byte garage, byte piscine, int postcode, Set locations) {
       this.user = user;
       this.nbChambres = nbChambres;
       this.garage = garage;
       this.piscine = piscine;
       this.postcode = postcode;
       this.locations = locations;
    }
   
    public Integer getIdBienImmo() {
        return this.idBienImmo;
    }
    
    public void setIdBienImmo(Integer idBienImmo) {
        this.idBienImmo = idBienImmo;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public int getNbChambres() {
        return this.nbChambres;
    }
    
    public void setNbChambres(int nbChambres) {
        this.nbChambres = nbChambres;
    }
    public byte getGarage() {
        return this.garage;
    }
    
    public void setGarage(byte garage) {
        this.garage = garage;
    }
    public byte getPiscine() {
        return this.piscine;
    }
    
    public void setPiscine(byte piscine) {
        this.piscine = piscine;
    }
    public int getPostcode() {
        return this.postcode;
    }
    
    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }
    public Set getLocations() {
        return this.locations;
    }
    
    public void setLocations(Set locations) {
        this.locations = locations;
    }




}


package model;
// Generated Apr 22, 2013 9:08:56 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private Integer idUser;
     private String nom;
     private String password;
     private int postcode;
     private byte admin;
     private Set bienimmos = new HashSet(0);
     private Set locations = new HashSet(0);

    public User() {
    }

	
    public User(String nom, String password, int postcode, byte admin) {
        this.nom = nom;
        this.password = password;
        this.postcode = postcode;
        this.admin = admin;
    }
    public User(String nom, String password, int postcode, byte admin, Set bienimmos, Set locations) {
       this.nom = nom;
       this.password = password;
       this.postcode = postcode;
       this.admin = admin;
       this.bienimmos = bienimmos;
       this.locations = locations;
    }
   
    public Integer getIdUser() {
        return this.idUser;
    }
    
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public int getPostcode() {
        return this.postcode;
    }
    
    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }
    public byte getAdmin() {
        return this.admin;
    }
    
    public void setAdmin(byte admin) {
        this.admin = admin;
    }
    public Set getBienimmos() {
        return this.bienimmos;
    }
    
    public void setBienimmos(Set bienimmos) {
        this.bienimmos = bienimmos;
    }
    public Set getLocations() {
        return this.locations;
    }
    
    public void setLocations(Set locations) {
        this.locations = locations;
    }




}


/*
 * To change this template, choose Consts | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author kavdiev
 */
@Entity
//@Table(name = "UsertTB")
public class User implements Serializable {

    @Id
    @GeneratedValue
    int idU;
    String nom;
    String password;
    int postCode;
    boolean admin;
    @Transient
    boolean anonymus;
    // -- faut trouver une autre solution à fetchType.eager....
    @OneToMany(mappedBy = "proprio", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Appart> apparts = new ArrayList<>();
    @OneToMany(mappedBy = "locataire", cascade = CascadeType.ALL)
    private List<LocationActive> locations = new ArrayList<>();

    public User() {
    }

    public User(int idU, String nom, String password) {
        this.idU = idU;
        this.nom = nom;
        this.password = password;
    }

    public User(String nom, String password) {
        this.nom = nom;
        this.password = password;
    }

    public User(String nom, String password, int postCode) {
        this.nom = nom;
        this.password = password;
        this.postCode = postCode;
    }

    public User(boolean anonymus) {
        this.anonymus = anonymus;
        if (anonymus) {
            this.nom = "Anonymus"+this.getClass();
        }
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Appart> getApparts() {
        return apparts;
    }

    public void setApparts(List<Appart> apparts) {
        this.apparts = apparts;
    }

    public List<LocationActive> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationActive> locations) {
        this.locations = locations;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public boolean sameUser(User u) {

        if (this.idU == u.idU) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public void setAdmin() {
        this.admin = true;
    }

    public boolean isAnonymus() {
        return anonymus;
    }

    public void setAnonymus(boolean anonymus) {
        this.anonymus = anonymus;
    }

    public int countApparts() {
        return this.getApparts().size();
    }

    public int countMyApparts() {
        if (this.apparts != null && !this.apparts.isEmpty()) {
            return this.apparts.size();
        }
        return 0;
    }

    public int countRentRequests() {
        int i = 0;
        if (this.apparts != null && !this.apparts.isEmpty()) {

            /* for (Appart a : this.apparts) {
             if (a.locataire != null) {
             System.out.println(" locataire : " + a.locataire.getNom());
             i++;
             }
             } */
        }
        return i;
    }
}

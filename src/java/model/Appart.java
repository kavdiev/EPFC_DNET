/*
 * To change this template, choose Tools | Templates
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
public class Appart implements Serializable {

    @Id
    @GeneratedValue
    int idA;
    String type;
    int superficie;
    int pieces;
    int loyer;
    boolean garage;
    boolean piscine;
    int postCode;
    String rue;
    String pays;
    @ManyToOne
    User proprio;
    @OneToMany(mappedBy = "appart", cascade = CascadeType.ALL)
    private List<LocationActive> locations = new ArrayList<>();

    public Appart() {

    }

    public Appart(int idA, String type, int superficie, int pieces, int loyer, boolean garage, boolean piscine, int postCode, String rue, String pays, User proprio) {
        this.idA = idA;
        this.type = type;
        this.superficie = superficie;
        this.pieces = pieces;
        this.loyer = loyer;
        this.garage = garage;
        this.piscine = piscine;
        this.postCode = postCode;
        this.rue = rue;
        this.pays = pays;
        this.proprio = proprio;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public int getLoyer() {
        return loyer;
    }

    public void setLoyer(int loyer) {
        this.loyer = loyer;
    }

    public boolean isGarage() {
        return garage;
    }

    public void setGarage(boolean garage) {
        this.garage = garage;
    }

    public boolean isPiscine() {
        return piscine;
    }

    public void setPiscine(boolean piscine) {
        this.piscine = piscine;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public User getProprio() {
        return proprio;
    }

    public boolean isProprio(User u) {
        return this.proprio.sameUser(u);
    }

    public boolean isProprio(int idU) {
        return this.proprio.idU == idU;
    }

    public void setProprio(User proprio) {
        this.proprio = proprio;
    }

    public List<LocationActive> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationActive> locations) {
        this.locations = locations;
    }
}

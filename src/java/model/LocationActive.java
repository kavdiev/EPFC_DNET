/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
//import java.util.Calendar;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author kavdiev
 */
// nom de table User n'est pas autorisé ..... par norme SQL je sais plus trop quoi ...
@Entity
@Table(name = "LocationActive")
public class LocationActive implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    User locataire;
    @ManyToOne
    Appart appart;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateIn;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateOut;
    boolean approuved;

    public LocationActive() {
    }

    public LocationActive(User locataire, Appart appart, Date dateIn, Date dateOut, Long id) {
        this.locataire = locataire;
        this.appart = appart;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.id = id;
    }

    public LocationActive(User locataire, Appart appart, Date dateIn, Date dateOut) {
        this.locataire = locataire;
        this.appart = appart;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
    }

    public LocationActive(Date dateIn, Date dateOut) {
        this.dateIn = dateIn;
        this.dateOut = dateOut;
    }

    public LocationActive(User locataire, Appart appart) {
        this.locataire = locataire;
        this.appart = appart;
    }

    public User getLocataire() {
        return locataire;
    }

    public void setLocataire(User locataire) {
        this.locataire = locataire;
    }

    public Appart getAppart() {
        return appart;
    }

    public void setAppart(Appart appart) {
        this.appart = appart;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isApprouved() {
        return approuved;
    }

    public void setApprouved(boolean approuved) {
        this.approuved = approuved;
    }
    
}

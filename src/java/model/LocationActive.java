/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
//import java.util.Calendar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    int yearIn;
    int weekIn;
    int yearOut;
    int weekOut;
    boolean approuved;
    @Transient
    public List<Integer> weeks;

    public LocationActive() {
    }

    {
        weeks = new ArrayList<>();
        for (int i = 1; i < 53; i++) {
            weeks.add(i);
        }
    }

    public LocationActive(User locataire, Appart appart, int yearIn, int weekIn, int yearOut, int weekOut) {
        this.locataire = locataire;
        this.appart = appart;
        this.yearIn = yearIn;
        this.weekIn = weekIn;
        this.yearOut = yearOut;
        this.weekOut = weekOut;
    }

    public LocationActive(int weekIn, int weekOut) {
        this.weekIn = weekIn;
        this.weekOut = weekOut;
    }
  
    public List<Integer> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<Integer> weeks) {
        this.weeks = weeks;
    }

    public int getYearIn() {
        return yearIn;
    }

    public void setYearIn(int yearIn) {
        this.yearIn = yearIn;
    }

    public int getWeekIn() {
        return weekIn;
    }

    public void setWeekIn(int weekIn) {
        this.weekIn = weekIn;
    }

    public int getYearOut() {
        return yearOut;
    }

    public void setYearOut(int yearOut) {
        this.yearOut = yearOut;
    }

    public int getWeekOut() {
        return weekOut;
    }

    public void setWeekOut(int weekOut) {
        this.weekOut = weekOut;
    }

    // a supprimer
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

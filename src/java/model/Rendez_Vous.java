/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 *
 * @author Administrateur
 */
public class Rendez_Vous implements Serializable{

    private static int nbRdv = 0;
    
    private int id;
    private String heure;
    private Lieu lieu;
    private Calendar date;

    public Rendez_Vous() {
        this.id = ++nbRdv;
    }
 
    public String dateToString() {      
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(date.getTime());
    }

    public Rendez_Vous chargerRdvRandom() {
        Random random = new Random();
        dateRandom(random);
        lieu = new Lieu("Lieu du RDV" + id);
        heure = "" + random.nextInt(25) + "h";
        return this;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
        System.out.println(dateToString());
    }
    
    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private void dateRandom(Random random) {
        int annee = Calendar.getInstance().get(Calendar.YEAR);
        int mois = random.nextInt(12);
        int jour = random.nextInt(28) + 1;
        
        date = new GregorianCalendar(annee, mois, jour);
    }
}

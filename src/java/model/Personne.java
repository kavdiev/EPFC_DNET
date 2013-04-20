/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Administrateur
 */
public class Personne implements Serializable{

    private static int nbPers = 0;
    
    private int id;
    private String nom;
    private String prenom;
    private List<Rendez_Vous> rendez_vous = new ArrayList();
     
    private final int NBMAX_RDV = 4;

    public Personne() {
        this.id = ++nbPers;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public List<Rendez_Vous> getRendez_vous() {
        return rendez_vous;
    }

    public void setRendez_vous(List<Rendez_Vous> rendez_vous) {
        this.rendez_vous = rendez_vous;
    }

    public int nbRdv() {
        return rendez_vous.size();
    }

    public void addRdv(Rendez_Vous rdv) {
        rendez_vous.add(rdv);
    }

    public void removeRdv(int idrdv) {
        Rendez_Vous rdvToRemove = null;
        for (Rendez_Vous rdv : rendez_vous) {
            if (rdv.getId() == idrdv) {
                rdvToRemove = rdv;
                break;
            }
        }
        if (rdvToRemove != null)
            rendez_vous.remove(rdvToRemove);
    }
    
    public Personne chargerPersRandom() {
        this.nom = "Nom"+id;
        this.prenom = "Prénom"+id;
        int nbrdv = new Random().nextInt(NBMAX_RDV);
        for (int i = 0; i < nbrdv; i++) {
            rendez_vous.add(new Rendez_Vous().chargerRdvRandom());
        }
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Personne other = (Personne) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + '}';
    }
    
    
}

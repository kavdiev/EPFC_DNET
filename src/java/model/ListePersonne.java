/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrateur
 */
@Component
public class ListePersonne {

    private List<Personne> personnes;
    private final int NBMAX_PERS = 5;

    public ListePersonne() {
        personnes = new ArrayList<Personne>();
    }

    public void chargerListeRandom() {
        personnes.clear();
        Random random = new Random();
        int nbpers = random.nextInt(NBMAX_PERS);
        for (int i = 0; i < nbpers; i++) {
            personnes.add(new Personne().chargerPersRandom());
        }
    }
   
    public List<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes;
    }

    public Personne find(int id) {
        for (Personne p : personnes) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean delete(Personne pers) {
        return personnes.remove(pers);
    }

    public void save(Personne pers) {
        Personne p = find(pers.getId());
        if (p == null) {
            personnes.add(pers);
        } else {
            p.setNom(pers.getNom());
            p.setPrenom(pers.getPrenom());
        }
    }

    public boolean exist(Personne pers) {
        return personnes.contains(pers);
    }
}

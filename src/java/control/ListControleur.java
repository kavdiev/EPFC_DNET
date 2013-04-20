/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import model.ListePersonne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Administrateur
 */
@Controller
public class ListControleur {

    private ListePersonne lstpers;
 
    @Autowired
    public void setLstpers(ListePersonne lstpers) {
        this.lstpers = lstpers;
    }

    public ListControleur() {
    }
    
    @RequestMapping(value="/liste.htm", method=RequestMethod.POST)
    public ModelAndView menuListe(@RequestParam(value="initListe",required=false) String init) {
        if (init != null)
            lstpers.chargerListeRandom();
        return new ModelAndView("listePersonne","personnes",lstpers.getPersonnes());
    }
    
}
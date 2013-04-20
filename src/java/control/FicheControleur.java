/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Lieu;
import model.ListePersonne;
import model.Personne;
import model.Rendez_Vous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/** 
 *
 * @author Administrateur
 */
@Controller
@RequestMapping("/fiche.htm")
@SessionAttributes("personne")
public class FicheControleur {

    private ListePersonne lstpers;

    @Autowired
    public void setLstpers(ListePersonne lstpers) {
        this.lstpers = lstpers;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@RequestParam(required = false, value = "id") String id, ModelMap model) {
        String vue;
        Personne p;
        if (id == null) {
            p = new Personne();
            vue = "formNewPersonne";
        } else {
            p = lstpers.find(Integer.parseInt(id));
            System.out.println("GET - " + p.getPrenom());
            vue = "formPersonne";
        }
        model.addAttribute(p);
        return vue;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView validation(HttpServletRequest request, @ModelAttribute("personne") Personne p) {
        String vue = "formPersonne";
        if (request.getParameter("addedRdv") != null) {
            if (!addRdv(request, p)) {
                return new ModelAndView("erreur");
            }
        } else {
            if (request.getParameter("Supprimer") != null) {
                lstpers.delete(p);
                return new ModelAndView("listePersonne","personnes",lstpers.getPersonnes());
            } else if (request.getParameter("RetourListe") != null) {
                return new ModelAndView("listePersonne","personnes",lstpers.getPersonnes());
            } else if (request.getParameter("Enregistrer") != null) {
                saveRDV(p, request);
                lstpers.save(p);
            } else if (request.getParameter("Creer") != null) {
                lstpers.save(p);
            } else if (request.getParameter("AjoutRdv") != null) {
                vue = "newRdv";
            } else {
                Enumeration<String> args = request.getParameterNames();
                final String nomparam = "rdv_suppr";
                while (args.hasMoreElements()) {
                    String cmd = args.nextElement();
                    if (cmd.contains(nomparam)) {
                        int idrdv = Integer.parseInt(cmd.substring(nomparam.length()));
                        p.removeRdv(idrdv);
                        break;
                    }
                }
            }
        }
        return new ModelAndView(vue);
    }

    private boolean addRdv(HttpServletRequest request, Personne p) {
        try {
            if (request.getParameter("Annuler") == null) {
                String dateStr = request.getParameter("dateRdv");
                if (dateStr != null) {
                    Rendez_Vous rdv = new Rendez_Vous();
                    String[] dateTab = dateStr.split("/");
                    int jour = Integer.parseInt(dateTab[0]);
                    int mois = Integer.parseInt(dateTab[1]);
                    int annee = Integer.parseInt(dateTab[2]);
                    System.out.println("jour : " + jour + " mois : " + mois + " année : " + annee);
                    rdv.setDate(new GregorianCalendar(annee, mois - 1, jour));
                    rdv.setHeure(request.getParameter("heureRdv"));
                    rdv.setLieu(new Lieu(request.getParameter("lieuRdv")));
                    p.addRdv(rdv);
                    lstpers.save(p);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void saveRDV(Personne p, HttpServletRequest request) {
        for (Rendez_Vous rdv : p.getRendez_vous()) {
            String heure = request.getParameter("heure" + rdv.getId());
            String str_lieu = request.getParameter("lieu" + rdv.getId());
            rdv.setHeure(heure);
            rdv.getLieu().setDescription(str_lieu);
        }
    }

    public ModelAndView testarg(HttpServletRequest request) {
        Enumeration<String> args = request.getParameterNames();
        List<String> lstargs = new ArrayList<String>();
        while (args.hasMoreElements()) {
            String cmd = args.nextElement();
            lstargs.add(cmd);
        }
        return new ModelAndView("testargs", "lstargs", lstargs);
    }
}
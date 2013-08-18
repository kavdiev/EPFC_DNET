/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.HibernateAppartDao;
import dao.HibernateLocationActiveDao;
import dao.HibernateUserDao;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Appart;
import model.LocationActive;
import model.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tools.Consts;
import tools.Errors;

/**
 *
 * @author baxter
 *
 * controleur appart
 *
 */
@Controller
public class CtrlAppart extends genericCtrl {

    @Autowired
    HibernateAppartDao hAppart;
    @Autowired
    HibernateUserDao hUser;
    @Autowired
    HibernateLocationActiveDao hLoc;

    public CtrlAppart() {
    }

    //  il faut placer des messages d'erreur dans le model
    //--------------- demande d'afficher un seul appart
    // si on n'a pas de id ( donc on ne sait pas quel appart à affciher ... redirect index)
    @RequestMapping(value = "/appart.htm", method = RequestMethod.GET)
    public ModelAndView getForm(HttpServletRequest request,
            @RequestParam(required = false, value = "id") String id,
            ModelMap model) {
        System.out.println("in get show appart");
        String vue = "index";
        if (id != null) {
            Appart a = (Appart) hAppart.selectOne(Integer.parseInt(id));
            super.addAppart2LastVisited(a, request);
            if (isLoged(request)) {
                if (!a.isProprio(getSessionUser(request))) {
                    vue = Consts.RENT_REQUEST_VUE;
                    SearchForm searcher = super.getSearchForm(request);
                    model.addAttribute("rent", new LocationActive(searcher.getWeekIn(),searcher.getWeekOut()) );
                } else {
                    // proprio
                    vue = Consts.APPART_VUE;
                }
            } else {
                // verification facultative ...  j'aime pas trop il faut restructurer les ifs
                if (isAnonymus(request)) {
                    vue = Consts.APPART_VUE;
                } else {
                    model.addAttribute(Consts.MSG, Errors.getErrorMsg("1"));
                }
            }
            model.addAttribute(Consts.APPART, a);
        } else {
            model.addAttribute(Consts.MSG, Errors.getErrorMsg("a00"));
            vue = Consts.MAIN_PAGE_VUE;
        }
        return new ModelAndView(vue, Consts.MODEL, model);
    }

    /*------------- list of apparts ----------*/
    // pas complet manque liste des appart d'un autre user...
    @RequestMapping(value = "/apparts.htm", method = RequestMethod.GET)
    public ModelAndView getListForm(HttpServletRequest request,
            @RequestParam(required = false, value = "id") String id,
            ModelMap model) {
        System.out.println("in get show appart");
        String vue = Consts.INDEX_VUE;
        String action = request.getParameter("tool");
        if (action.equals("lasts")) {
            List<Appart> apparts = super.getLastVisited(request);
            model.addAttribute("apparts", apparts);
            model.addAttribute(Consts.LABEL,"Last visited");
            System.out.println(" label :");
            vue = Consts.APPARTS;
        } else {
            if (isLoged(request)) {
                if (action != null) {
                    vue = "apparts";
                    switch (action) {
                        case "myapparts": {

                            List<Appart> apparts = (List<Appart>) (List<?>) hAppart.selectUsersApparts(getSessionUser(request).getIdU());
                            model.addAttribute("apparts", apparts);
                            break;
                        }
                        case "showusersapparts": {
                            if (id != null) {
                                List<Appart> apparts = (List<Appart>) (List<?>) hAppart.selectUsersApparts(Integer.parseInt(id));
                                model.addAttribute("apparts", apparts);

                            } else {
                                vue = Consts.MAIN_PAGE_VUE;
                                model.addAttribute(Consts.SEARCH_FORM, new SearchForm());
                                model.addAttribute(Consts.MSG, Errors.getErrorMsg("0"));
                            }
                            break;
                        }
                    }
                }
            } else {
                model.addAttribute(Consts.MSG, Errors.getErrorMsg("1"));
            }
        }
        return new ModelAndView(vue, Consts.MODEL, model);
    }

    /*---- new appart  ------------------------------*/
    @RequestMapping(value = "/newappart.htm", method = RequestMethod.GET)
    public ModelAndView getNewForm(HttpServletRequest request, ModelMap model) {
        System.out.println("in get new appart");
        String vue = Consts.INDEX_VUE;
        if (isLoged(request)) {
            model.addAttribute("appart", new Appart());
            vue = "newAppart";
        }
        return new ModelAndView(vue, Consts.MODEL, model);
    }

    // ---------- post methode   (submite )
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView submitForm(HttpServletRequest request,
            ModelMap model,
            @ModelAttribute("appart") Appart a) {
        System.out.println("in post");
        String vue = Consts.INDEX_VUE;

        String action = request.getParameter("action");
        // si pas d'action ni appart ni user id .. alors c'est direct_acces .. donc redirect index
        if ((action != null) && (a != null) && (isLoged(request))) {
            vue = Consts.MAIN_PAGE_VUE;
            String sId = request.getParameter("id"); // id de l'appart en question

            switch (action) {
                case "appartAdded": {
                    System.out.println(" new appart added ");
                    a.setProprio(getSessionUser(request));
                    hAppart.save(a);
                    break;
                }
                case "Reserver": {
                    if (sId != null) {
                        System.out.println(" rent request ");
                        a = (Appart) hAppart.selectOne(Integer.parseInt(sId));
                        if (!a.isProprio(getSessionUser(request))) {

                            hAppart.save(a);
                        } else {
                            model.addAttribute(Consts.MSG, Errors.getErrorMsg("a03"));
                        }
                    } else {
                        model.addAttribute(Consts.MSG, Errors.getErrorMsg("a02"));
                    }
                    break;
                }
                case "Confirmer": {
                    System.out.println(" rent confirm");
                    if (sId != null) {
                        a = (Appart) hAppart.selectOne(Integer.parseInt(sId));
                        if (a.isProprio(getSessionUser(request))) { //verif si je suis bien proprio de l'appart
                            hAppart.save(a);
                        } else {
                            model.addAttribute(Consts.MSG, Errors.getErrorMsg("a04"));
                        }
                    } else {
                        model.addAttribute(Consts.MSG, Errors.getErrorMsg("a02"));
                    }
                    break;
                }
                case "Update": {
                    System.out.println(" update no yet implemented");
                    if (sId != null) {
                        Appart dbAppart = (Appart) hAppart.selectOne(Integer.parseInt(sId));
                        // on reccuper l'appart de la db
                        // on lui applique les modif

                        if (dbAppart.isProprio(getSessionUser(request))) { //verif si je suis bien proprio de l'appart

                            dbAppart.setLoyer(a.getLoyer());
                            dbAppart.setPieces(a.getPieces());
                            dbAppart.setPostCode(a.getPostCode());
                            dbAppart.setSuperficie(a.getSuperficie());
                            // on sauvegarde les modifications 
                            hAppart.save(dbAppart);
                        } else {
                            model.addAttribute(Consts.MSG, Errors.getErrorMsg("a04"));
                        }
                    } else {
                        model.addAttribute(Consts.MSG, Errors.getErrorMsg("a02"));
                    }

                    break;
                }
                case "Supprimer": {
                    System.out.println(" delete not yet implemented");

                    break;
                }
            }
        }

        model.addAttribute(Consts.SEARCH_FORM, getSearchForm(request));
        return new ModelAndView(vue, Consts.MODEL, model);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.HibernateAppartDao;
import dao.HibernateUserDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Appart;
import model.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tools.Consts;
import tools.Errors;

/**
 *
 * @author kavdiev
 */
@Controller
public class CtrlSearch extends genericCtrl {

    @Autowired
    HibernateUserDao hUser;
    @Autowired
    HibernateAppartDao hAppart;

    public CtrlSearch() {
    }
    //    SimpleDateFormat dateFormater = new SimpleDateFormat ("dd/MM/yyyy");
    // ---------- post methode   (submite )

    @RequestMapping(value = "/search.htm", method = RequestMethod.POST)
    public ModelAndView submitForm(HttpServletRequest request,
            ModelMap model,
            @ModelAttribute("searchForm") SearchForm searcher) {
        System.out.println("in post search");
        String vue = "index";
        // if (request.getParameter("appartAdded") != null) {
        if ((searcher != null) || (isLoged(request))) {
            System.out.println("loged in srch w-in="+searcher.getWeekIn()+" w-out"+searcher.getWeekOut());
            
            List<Appart> apparts = null;
            searcher.resetLevel();
            super.setSearchForm(searcher, request);
           // String querry = searcher.buildQuerry(super.getSessionUser(request).getIdU());
            if (searcher.isStrict()) {
                System.out.println("strict");
                apparts = hAppart.customSearch(searcher,super.getSessionUser(request));

            } else {
                System.out.println("no strict");
                while (searcher.getLevel() >= 0) {
                    apparts = hAppart.customSearch(searcher,super.getSessionUser(request));
                    if (apparts != null && !apparts.isEmpty()) {
                        break;
                    }
                    searcher.levelDown();
                }
            }
            if (apparts == null || apparts.isEmpty()) {
                System.out.println("apparts empty "+Errors.getErrorMsg("s01"));
                model.addAttribute(Consts.MSG, Errors.getErrorMsg("s01"));
            }
            model.addAttribute("apparts", apparts);
            model.addAttribute(Consts.SEARCH_FORM, searcher);
            vue = Consts.MAIN_PAGE_VUE;
        }
        return new ModelAndView(vue, Consts.MODEL, model);
    }
}

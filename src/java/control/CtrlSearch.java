/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.HibernateAppartDao;
import dao.HibernateUserDao;
import java.text.SimpleDateFormat;
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
    Errors error = new Errors();

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
            List<Appart> apparts = null;
            searcher.resetLevel();
            if (searcher.isStrict()) {
                apparts = hAppart.customSearch(searcher.buildQuerry(super.getSessionUser(request).getIdU()));
                System.out.println("strict");
            } else {
                System.out.println("no strict");
                while (searcher.getLevel() >= 0) {
                    apparts = hAppart.customSearch(searcher.buildQuerry(super.getSessionUser(request).getIdU()));
                    if (apparts != null && !apparts.isEmpty()) {
                        break;
                    }
                    searcher.levelDown();
                }
            }
            if (apparts == null || apparts.isEmpty()) {
                model.addAttribute(Consts.MSG, error.getErrorMsg("s01"));
            }
            model.addAttribute("apparts", apparts);
            model.addAttribute(Consts.SEARCH_FORM, searcher);
            vue = Consts.MAIN_PAGE_VUE;
        }
        // }
        return new ModelAndView(vue, Consts.MODEL, model);
    }
}

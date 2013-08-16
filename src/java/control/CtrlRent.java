/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.HibernateAppartDao;
import dao.HibernateLocationActiveDao;
import dao.HibernateUserDao;
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
 * @author kavdiev
 */
@Controller
public class CtrlRent extends genericCtrl {

    @Autowired
    HibernateAppartDao hAppart;
    @Autowired
    HibernateUserDao hUser;
    @Autowired
    HibernateLocationActiveDao hLoc;

    @RequestMapping(value = "/rentRequest.htm", method = RequestMethod.GET)
    public ModelAndView getForm(HttpServletRequest request,
            @RequestParam(required = false, value = "id") String id,
            ModelMap model) {
        System.out.println("in get show appart");
        String vue = "index";
        if ((isLoged(request)) && (id != null)) {
            Appart a = (Appart) hAppart.selectOne(Integer.parseInt(id));
            if (!a.isProprio(getSessionUser(request))) {
                vue = Consts.RENT_REQUEST_VUE;
            } else {
                vue = Consts.APPART_VUE;
            }
            model.addAttribute(Consts.APPART, a);
        }
        return new ModelAndView(vue, Consts.MODEL, model);
    }

    // ---------- post methode   (submite )
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView submitForm(HttpServletRequest request,
            ModelMap model,
            @ModelAttribute("rent") LocationActive rent) {
        System.out.println("in post");
        String vue = Consts.INDEX_VUE;

        String action = request.getParameter("requestRent");
        System.out.println("action=" + action);
        if (isLoged(request)) {
            if (action != null && rent != null) {
                vue = Consts.MAIN_PAGE_VUE;
                String sId = request.getParameter("idR"); // id de l'appart en question
                System.out.println("sid=" + sId);
                rent.setLocataire(super.getSessionUser(request)); // il faut encore une verif sur appart ... ehhh ou le prendre de currentAppart
                rent.setAppart((Appart) hAppart.selectOne(Integer.parseInt(sId)));
                System.out.println(" id "+rent.getId()+" locataire "+rent.getLocataire().getNom()+" appart "+rent.getAppart().getIdA());
                if (!hLoc.save(rent)) {
                    model.addAttribute(Consts.MSG, Errors.getErrorMsg("2"));
                }
                model.addAttribute(Consts.SEARCH_FORM, getSearchForm(request));
            }
        } else {
            model.addAttribute(Consts.MSG, Errors.getErrorMsg("1"));
        }
        return new ModelAndView(vue, Consts.MODEL, model);
    }
}

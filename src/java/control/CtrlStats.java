/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.HibernateAppartDao;
import dao.HibernateLocationActiveDao;
import dao.HibernateUserDao;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tools.Consts;
import tools.Errors;

/**
 *
 * @author baxter
 */
@Controller
public class CtrlStats  extends genericCtrl {
    @Autowired
    HibernateAppartDao hAppart;
    @Autowired
    HibernateUserDao hUser;
    @Autowired
    HibernateLocationActiveDao hLoc;
      public CtrlStats() {
    }
      
    @RequestMapping(value = "/stats.htm", method = RequestMethod.GET)
    public ModelAndView getForm(HttpServletRequest request,
            ModelMap model) {
        System.out.println("in get form stats");
        String vue = "stats";
        if (isSessionUserAdmin(request)) {
            
            //count users
             model.addAttribute("countUsers",hUser.count());
             //count admins
             model.addAttribute("countAdmins",hUser.countAdmins());
            //last user
             model.addAttribute("lastUser",hUser.getLast());
            //count apparts
            model.addAttribute("countApparts",hAppart.count());
            //count rents
            model.addAttribute("countRents",hLoc.count());
            
        } else {
            model.addAttribute(Consts.SEARCH_FORM, getSearchForm(request));
            model.addAttribute(Consts.MSG, Errors.getErrorMsg("u04"));
            vue = Consts.MAIN_PAGE_VUE;
        }
        return new ModelAndView(vue, Consts.MODEL, model);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.HibernateAppartDao;
import dao.HibernateLocationActiveDao;
import dao.HibernateUserDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Appart;
import model.LocationActive;
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
        System.out.println("in get rent");
        String vue = "index";
        if ((isLoged(request)) && (id != null)) {
            Appart a = (Appart) hAppart.selectOne(Integer.parseInt(id));
            if (!a.isProprio(getSessionUser(request))) {
                vue = Consts.RENT_REQUEST_VUE;
                model.addAttribute("reservations", hLoc.getAllReservationFromNow(a.getIdA()));
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
                System.out.println(" id " + rent.getId() + " locataire " + rent.getLocataire().getNom() + " appart " + rent.getAppart().getIdA());
                if (!hLoc.isFree(rent)) {
                    System.out.println("n'est pas libre");
                    model.addAttribute(Consts.MSG, Errors.getErrorMsg("l01"));
                    // il faut un redirect vers RentRequest page ?
                } else {
                    if (hLoc.save(rent)) {
                        model.addAttribute(Consts.MSG, Errors.getErrorMsg("l02"));
                    } else {
                        model.addAttribute(Consts.MSG, Errors.getErrorMsg("2"));
                    }
                }
                model.addAttribute(Consts.SEARCH_FORM, getSearchForm(request));
            }
        } else {
            model.addAttribute(Consts.MSG, Errors.getErrorMsg("1"));
        }
        return new ModelAndView(vue, Consts.MODEL, model);
    }

    @RequestMapping(value = "/toApprove.htm", method = RequestMethod.GET)
    public ModelAndView getFormToApprove(HttpServletRequest request,
            @RequestParam(required = false, value = "tool") String tool,
            @RequestParam(required = false, value = "id") String sId,
            ModelMap model) {
        System.out.println("in approve  tool = " + tool);
        String vue = Consts.INDEX_VUE;

        if (isLoged(request) && tool != null) {

            switch (tool) {
                case "approuve":
                    if ((sId != null) || hLoc.aproveOne(Integer.parseInt(sId))) {
                        model.addAttribute(Consts.MSG, Errors.getErrorMsg("l03"));
                    } else {
                        model.addAttribute(Consts.MSG, Errors.getErrorMsg("2"));
                    }
                    model.addAttribute("label", "Reservations à confirmer ");
                    model.addAttribute(Consts.RESERVATIONS, hLoc.selectToAprove(getSessionUser(request).getIdU()));
                    break;
                case "refuser":
                    if ((sId != null) || hLoc.delete(Integer.parseInt(sId))) {
                        model.addAttribute(Consts.MSG, Errors.getErrorMsg("l04"));
                    } else {
                        model.addAttribute(Consts.MSG, Errors.getErrorMsg("2"));
                    }
                    model.addAttribute("label", "Reservations à confirmer ");
                    model.addAttribute(Consts.RESERVATIONS, hLoc.selectToAprove(getSessionUser(request).getIdU()));
                    break;
                case "reservations":


                    model.addAttribute(Consts.PENDING, hLoc.getAllMyReservations(getSessionUser(request).getIdU(), Consts.PENDING_STATUS));
                    model.addAttribute(Consts.RESERVED, hLoc.getAllMyReservations(getSessionUser(request).getIdU(), Consts.RESERVED_STATUS));
                    model.addAttribute(Consts.REFUSED, hLoc.getAllMyReservations(getSessionUser(request).getIdU(), Consts.REFUSED_STATUS));
                    model.addAttribute("label", "Mes reservations");
                    break;
                case "toaprove":

                    model.addAttribute("label", "Reservations à confirmer ");
                    model.addAttribute(Consts.RESERVATIONS, hLoc.selectToAprove(getSessionUser(request).getIdU()));
                    break;
            }
            vue = Consts.TO_APPROVE_VUE;

        } else {
            model.addAttribute(Consts.SEARCH_FORM, getSearchForm(request));
        }

        return new ModelAndView(vue, Consts.MODEL, model);
    }
// ---------- post methode   (submite )
 /*   @RequestMapping(value = "/toApprove.htm", method = RequestMethod.POST)
     public ModelAndView submitApprove(HttpServletRequest request,
     ModelMap model,
     @ModelAttribute("rent") LocationActive rent) {
     System.out.println("in post");
     String vue = Consts.INDEX_VUE;

     String action = request.getParameter("requestRent");
     if (isLoged(request)) {

     } else {
     model.addAttribute(Consts.MSG, Errors.getErrorMsg("1"));
     }
     return new ModelAndView(vue, Consts.MODEL, model);
     } */
}

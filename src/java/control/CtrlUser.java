/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.HibernateUserDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.User;
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

//----------- info
// modelmap => une map qui contien des variables pour les recuperer dans jsp.
/**
 *  // l'utilisateur courrant est dans la session et se nomme cuser
 *
 * @author baxter
 */
@Controller
public class CtrlUser extends genericCtrl {

    @Autowired
    HibernateUserDao hUser;
    // HiberBDDConnector bdd;

    public CtrlUser() {
    }

    @RequestMapping(value = "/user.htm", method = RequestMethod.GET)
    public ModelAndView setupForm(
            @RequestParam(required = false, value = "tool") String tool,
            @RequestParam(required = false, value = "id") String sId,
            HttpServletRequest request,
            ModelMap model) {
        String vue = Consts.INDEX_VUE;
        if (tool != null) {
            switch (tool) {
                case "newUser":
                    vue = Consts.NEW_USER_VUE;
                    model.addAttribute(Consts.USER, new User());
                    break;
                case "show": // add  check if current user is admin if not redirect  mainpage
                    // seulement admin pu voir les autres users
                    if (isLoged(request)) {
                        if (sId != null) {
                            if (isSessionUserAdmin(request)) {
                                vue = Consts.USER_VUE;
                                model.addAttribute(Consts.USER, hUser.selectOne(Integer.parseInt(sId)));
                            } else {
                                model.addAttribute(Consts.SEARCH_FORM, getSearchForm(request));
                                model.addAttribute(Consts.MSG, Errors.getErrorMsg("u01"));
                                vue = Consts.MAIN_PAGE_VUE;
                            }
                        }
                    }else {
                        model.addAttribute(Consts.MSG, Errors.getErrorMsg("1"));
                    }
                    break;
                case "login":
                    vue = Consts.LOGIN_VUE;
                    break;
                case "noUser":
                    vue = Consts.MAIN_PAGE_VUE;
                    setAnonymusUser(request);
                    model.addAttribute(Consts.SEARCH_FORM, getSearchForm(request));
                    break;
            }
        } else {
            model.addAttribute(Consts.MSG, Errors.getErrorMsg("0"));
        }
        return new ModelAndView(vue, Consts.MODEL, model);
    }

//-------------- users 
    @RequestMapping(value = "/users.htm", method = RequestMethod.GET)
    public ModelAndView setupListForm(
            @RequestParam(required = false, value = "tool") String tool,
            HttpServletRequest request,
            ModelMap model) {
        String vue = Consts.INDEX_VUE;
        List<User> users = null;
        if (tool != null && isLoged(request)) {
            if (isSessionUserAdmin(request)) {
                vue = "users";
                switch (tool) {
                    case "showall":
                        users = (List<User>) (List<?>) hUser.selectAll();
                        model.addAttribute("label", "Utilisateurs");
                        break;
                    case "showadmins":
                        users = (List<User>) (List<?>) hUser.selectAllAdmins();
                        model.addAttribute("label", "Admins");
                        break;
                    case "showproprios":
                        users = (List<User>) (List<?>) hUser.selectAllProprios();
                        model.addAttribute("label", "Proprios");
                        break;
                    default:
                        model.addAttribute(Consts.SEARCH_FORM,getSearchForm(request));
                        vue = Consts.MAIN_PAGE_VUE;
                        break;
                    // case  pour autre....
                    // show all admins
                    // show...
                }
                model.addAttribute("users", users);
            } else {
                model.addAttribute(Consts.SEARCH_FORM, getSearchForm(request));
                vue = Consts.MAIN_PAGE_VUE;
            }
        }
        return new ModelAndView(vue, Consts.MODEL, model);
    }

    /**
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView menuListe(HttpServletRequest request,
            ModelMap model,
            @ModelAttribute("user") User u,
            @RequestParam(required = false, value = "userid") String userId) {
        System.out.println("in post");
        String vue = Consts.INDEX_VUE;
        if (isLoged(request)) {
            if (request.getParameter("setadmin") != null) {
                vue = Consts.MAIN_PAGE_VUE;
                System.out.println("set admin");
                // il faut etre un admin pour setter un autre user comme admin
                if (isSessionUserAdmin(request)) {
                    u = (User) hUser.selectOne(u.getNom()); // oui c'est tres tordu ... va etre modifié
                    if (u.isAdmin()) {
                        model.addAttribute(Consts.MSG, Errors.getErrorMsg("u05"));
                    } else {
                        u.setAdmin();
                        hUser.save(u);
                    }
                } else {
                    model.addAttribute(Consts.MSG, Errors.getErrorMsg("u04"));
                }
                model.addAttribute(Consts.SEARCH_FORM, getSearchForm(request));
            }
        } else if (request.getParameter("login") != null) {
            String login, password;
            login = request.getParameter("username");
            password = request.getParameter("password");
            System.out.println("username " + login);

            String errorId = hUser.login(login, password);
            if (Errors.IsOk(errorId)) {
                vue = Consts.MAIN_PAGE_VUE;
                u = (User) hUser.selectOne(login);
                //  model.addAttribute("user", u);
                this.connect(u, request); // place current user dans session
                model.addAttribute(Consts.SEARCH_FORM, getSearchForm(request));
            } else {
                vue = Consts.LOGIN_VUE;
                model.addAttribute(Consts.MSG, Errors.getErrorMsg(errorId));
            }
        } else if (request.getParameter("userAdded") != null) {
            if (u == null) {
                System.out.println("go index");
            } else {
                String errorId = hUser.userExist(u.getNom());
                if (Errors.IsOk(errorId)) {
                    hUser.save(u);
                    model.addAttribute(Consts.MSG, Errors.getErrorMsg("u00"));
                } else {
                    vue = Consts.NEW_USER_VUE;
                    model.addAttribute(Consts.MSG, Errors.getErrorMsg(errorId));
                    model.addAttribute(Consts.USER, new User());
                }
            }
        }
        return new ModelAndView(vue, Consts.MODEL, model);
    }
}

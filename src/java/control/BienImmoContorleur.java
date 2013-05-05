/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Bienimmo;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author baxter
 */
@Controller
@RequestMapping("/bienimmo.htm")
public class BienImmoContorleur {
    
    public BienImmoContorleur () {
    }
    
     HiberBDDConnector bdd = new HiberBDDConnector();

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@RequestParam(required = false, value = "id") String id, ModelMap model) {
        String vue;
        //User u = new User();
        vue = "newUser";
        //model.addAttribute(u);
        return vue;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView menuListe(HttpServletRequest request, @ModelAttribute("newUser") Bienimmo bien) {
       /* if (request.getParameter("userAdded") != null) {
            if (u != null) {
                bdd.insertUser(u);
            }
        } */
        List<Bienimmo> apparts = bdd.getAllBienimmo();
        return new ModelAndView("bienimmolist", "apparts", apparts);
    }
}

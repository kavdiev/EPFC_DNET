/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.User;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/user.htm")
public class UserControleur {
HiberBDDConnector bdd = new HiberBDDConnector ();
    
    @RequestMapping(method = RequestMethod.POST)
     public ModelAndView menuListe(@RequestParam(value="listUser",required=false) String init) {
        List <User> users= bdd.getAllUsers();
        // il faut un else
        return new ModelAndView("userList","users",users);
    }
    
}

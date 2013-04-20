/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Administrateur
 */
@Controller
public class AccueilControleur {

    @RequestMapping("/index.htm")
    public String menuApplic() {
        return "index";
    }
  
}

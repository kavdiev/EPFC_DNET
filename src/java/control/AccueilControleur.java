/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.servlet.http.HttpServletRequest;
import model.SearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tools.Consts;

/**
 *
 * @author Administrateur
 */
@Controller
public class AccueilControleur extends genericCtrl {

    @RequestMapping("/index.htm")
    public ModelAndView menuApplic(
            @RequestParam(required = false, value = "logout") String logout,
            HttpServletRequest request,
            ModelMap model) {
        
        String vue = Consts.INDEX_VUE;
        model.addAttribute(Consts.MSG, "Hello à tous");
        if (logout != null) {
          this.disconnect(request);
           model.addAttribute(Consts.MSG, "Vous etes dé-logué");
        }
        else {
          if (isLoged(request)){
             vue = Consts.MAIN_PAGE_VUE;
             model.addAttribute(Consts.SEARCH_FORM, new SearchForm());
          }
        }
        return new ModelAndView(vue, Consts.MODEL, model);
    }
}
//----------- global infos
// id => est l'id de l'objet qu'on utilise..
// peut etre appart ou user
//les methodes GET c'est pour les appel de chargement d'une vue (jsp)
// on peut avoir plusieurs GET (comme sur CtrlAppart )
// POST, si page n'sest pas precisé, une methode de submite.. modification, un truc qu'on applic à la page sur la quelle on est ..
// c'est à dire... c'est le controleur qui nous a fourni la vue en cours, qui va traiter le post.
// pas trouvé la possibilité d'avoir plusieurs post.  donc utiliser un parametr comme "tool" et un swich case pour voir ce qu'on nous demande...
// sur Location active ou sur appart il faut un flag en plus
// genre rentrequest  comme ca...  dans la recherche des apparts cet appart ne doit pas apparetre ou au moins avoir un une info genre reservé 
// et aussi quand le proprio va sur "Mes apparts" il peux confimer l'appart
//


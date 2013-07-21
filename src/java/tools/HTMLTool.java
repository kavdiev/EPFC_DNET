/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import model.Appart;
import model.User;

/**
 *
 * @author baxter
 */
public class HTMLTool {

    public HTMLTool() {
    }

    public String showMenu(User u) {
        String out;
        out = " <a href=\"" + Consts.NEW_APPART_URL + "\">New Appart</a> <br>";
        if (u.getApparts() != null) {
            out = out + " <a href=\"" + Consts.LIST_APPART_URL + "?tool=myapparts\">Mes Apparts</a> <br>";
        }
        if (u.countRentRequests() > 0) {
            out = out + " <a href=\"" + Consts.LIST_APPART_URL + "?tool=toapprove\">Reservations </a> <br>";
        }
        if (u.isAdmin()) {
            out = out + " <a href=\"" + Consts.LIST_USERS_URL + "?tool=showall\"> Show Users </a> <br>";
            out = out + " <a href=\"" + Consts.LIST_USERS_URL + "?tool=showadmins\">Show Admins </a> <br>";
            out = out + " <a href=\"" + Consts.LIST_USERS_URL + "?tool=showproprios\">Show Proprios </a> <br>";
        }
        return out;
    }

    public String showMenu(Appart a, User u) {
        String out = "";
        if (a.isProprio(u.getIdU())) {
            out = "<input type=\"submit\" name=\"action\" value=\"Update\"> &nbsp;";
            out = out + "<input type=\"submit\" name=\"action\" value=\"Supprimer\">";
        } else {
            out = "<input type=\"submit\" name=\"requestRent\" value=\"Reserver\">";
        }

        return out;
    }

    public String showInfo(User u) {
        String out;
        out = "<h> Nom: " + u.getNom() + "</h> <br>";
        out = out + "<h> Post Code: " + u.getPostCode() + "</h> <br>";
        out = out + "<h> Mes apparts: " + u.countMyApparts() + "</h> <br>";
        out = out + "<h> Reservations : " + u.countRentRequests() + "</h> <br>";
        out = out + " <a href=\"index.htm?logout=true\"> Logout </a> <br>";
        return out;
    }

    public String showInfo(Appart a) {
        return " empty";
    }
}


/*
             // s'il ya un cocataire => une reservation ... ( oui le nom mal donné)
            if (a.locataire != null) {
                out = out + "<input type=\"submit\" name=\"action\" value=\"Confirmer\">";
            }
 */
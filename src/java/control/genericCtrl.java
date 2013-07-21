/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Appart;
import model.SearchForm;
import model.User;
import tools.Consts;

/**
 * genericCTRL va avoir - isLogedin (function pour verifier s'il ya un user dans
 * la session
 *
 * - logout (function pour fermer la session)
 *
 * --
 *
 * pn ne garde dans la session que les objet qui ont du sense à être gardé...
 *
 * current user et search form... tout le reste en reconstruit de BD...
 * - currentUser
 * - currentAppart
 * - currentSearchForm
 * - une file d'appart consultées. ( ou juste les ID ) genre 10 derniers
 * - Anonymus user 
 *
 * @author baxter
 */
public class genericCtrl {

    HttpSession session;

    protected boolean isLoged(HttpServletRequest request) {
        session = request.getSession();
        User user = (User) session.getAttribute(Consts.CURRENT_USER);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    protected void disconnect(HttpServletRequest request) {
        session = request.getSession();
        session.invalidate();
    }

    protected void connect(User u, HttpServletRequest request) {
        session = request.getSession();
        session.setAttribute(Consts.CURRENT_USER, u);
    }

    protected void setSearchForm(SearchForm sf, HttpServletRequest request) {
        session = request.getSession();
        session.setAttribute(Consts.SEARCH_FORM, sf);
    }

    protected SearchForm getSearchForm(HttpServletRequest request) {
        session = request.getSession();
        SearchForm sf = (SearchForm) session.getAttribute(Consts.SEARCH_FORM);
        if (sf != null) {
            return sf;
        } else {
            return new SearchForm();
        }
    }

    protected void setCurrentAppart(Appart a, HttpServletRequest request) {
        session = request.getSession();
        session.setAttribute(Consts.CURRENT_APPART, a);
    }

    protected Appart getCurrentAppart(HttpServletRequest request) {
        session = request.getSession();
        return (Appart) session.getAttribute(Consts.CURRENT_APPART);
    }

    protected User getSessionUser(HttpServletRequest request) {
        session = request.getSession();
        return (User) session.getAttribute(Consts.CURRENT_USER);
    }

    protected boolean isSessionUserAdmin(HttpServletRequest request) {
        session = request.getSession();
        User u = (User) session.getAttribute(Consts.CURRENT_USER);
        if (u != null) {
            return u.isAdmin();
        } else {
            return false;
        }
    }
    // set  ..?
}

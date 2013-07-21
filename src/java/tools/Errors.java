/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.HashMap;

/**
 *
 * @author baxter
 */
public class Errors {

    HashMap errorMap = new HashMap();
    String ok = "ok";

    {
        errorMap.put("0", "ERRROR.... inconue");
        errorMap.put("1", "ERRROR.... vous n'etes pas logué");
    //    errorMap.put("1", "Bienvenue, Register or Log-in ");
        
        errorMap.put("u00", "Enrigestrement effectué");
        errorMap.put("u01", "User n'existe pas");
        errorMap.put("u02", "Wrong password");
        errorMap.put("u03","user existe déjà");
        errorMap.put("u04","vous n'êtez pas autorisé pour effectuer cette action");
        errorMap.put("u05","User est déjà admin");

        errorMap.put("a00", "Appart non trouvé");
        errorMap.put("a01", "Appart est déjà reservé");
        errorMap.put("a02", "Appart: error ID est manquant");
        errorMap.put("a03", "Vous ne pouvez pas louer votre propre appartement");
        errorMap.put("a04", "ce n'est pas votre appart");
        

        errorMap.put("s01", "Resultat de recherche est vide");

    }

    public Errors() {
    }

    public String getErrorMsg(String errorId) {
        return (String) errorMap.get(errorId);
    }

    public Boolean IsOk(String errorId) {
        if (ok.equals(errorId)) {
            return true;
        }
        return false;
    }
}

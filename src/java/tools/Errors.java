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
public final class Errors {

    static HashMap<String, String> errorMap = new HashMap<String, String>() {
        {
            put("0", "ERRROR.... inconue");
            put("1", "ERRROR.... vous n'etes pas logué");
            put("2", "ERRROR.... problem DB");

            //    errorMap.put("1", "Bienvenue, Register or Log-in ");

            put("u00", "Enrigestrement effectué");
            put("u01", "User n'existe pas");
            put("u02", "Wrong password");
            put("u03", "user existe déjà");
            put("u04", "vous n'êtez pas autorisé pour effectuer cette action");
            put("u05", "User est déjà admin");

            put("a00", "Appart non trouvé");
            put("a01", "Appart est déjà reservé");
            put("a02", "Appart: error ID est manquant");
            put("a03", "Vous ne pouvez pas louer votre propre appartement");
            put("a04", "ce n'est pas votre appart");

            put("s01", "Resultat de recherche est vide");
            
            put("l01", "appartement n'est pas disponible");
            put("l02", "Reservation sauvée");
            put("l03", "Reservation aceptée");
            put("l04", "Reservation refusée");
        }
    };
    static String ok = "ok";

    public Errors() {
    }

    public static String getErrorMsg(String errorId) {
        return (String) errorMap.get(errorId);
    }

    public static Boolean IsOk(String errorId) {
        if (ok.equals(errorId)) {
            return true;
        }
        return false;
    }
}

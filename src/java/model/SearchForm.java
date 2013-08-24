/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.HibernateAppartDao;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author baxter
 */
@Component
@SuppressWarnings("empty-statement")
public class SearchForm {

    private final int SEARCH_LEVEL = 5;
    // list de champs possibles pour une recherche .....
    /// un boolean pour strict Search 
    //- -si True  -- recherche strict en function de tous les champs remplis
    //   si False --  recherche n'a rien dené ... on vire un champs.. un par un poour trouver au moin un appart
    //            -- si tous champs sont vide ... get all apparts  order by id (or date) DESC
    // placer du javascript pour faire un check genre min - max n'est pas invercé ... bla bla 
    // 
    boolean strict = true;
    int pieces;
    String type; //???
    int superficie;
    int loyerMax;
    int loyerMin;
    boolean garage;
    boolean piscine;
    int postCode;
    String rue;
    String pays;
    int yearIn;
    int weekIn;
    int yearOut;
    int weekOut;
    public List<Integer> weeks;
    @Autowired
    HibernateAppartDao db;
    private int level = SEARCH_LEVEL;

    public SearchForm() {
    }

    public SearchForm(int pieces, int superficie, int loyerMax, int loyerMin, boolean garage, boolean piscine, int postCode) {

        this.pieces = pieces;
        this.superficie = superficie;
        this.loyerMax = loyerMax;
        this.loyerMin = loyerMin;
        this.garage = garage;
        this.piscine = piscine;
        this.postCode = postCode;
    }

    public SearchForm(int pieces, String type, int superficie, int loyerMax, int loyerMin, boolean garage, boolean piscine, int postCode, String rue, String pays, int yearIn, int weekIn, int yearOut, int weekOut) {
        this.pieces = pieces;
        this.type = type;
        this.superficie = superficie;
        this.loyerMax = loyerMax;
        this.loyerMin = loyerMin;
        this.garage = garage;
        this.piscine = piscine;
        this.postCode = postCode;
        this.rue = rue;
        this.pays = pays;
        this.yearIn = yearIn;
        this.weekIn = weekIn;
        this.yearOut = yearOut;
        this.weekOut = weekOut;
    }

    {
        Calendar cal = Calendar.getInstance();
        if (this.weekIn == 0) {
            this.weekIn = cal.get(Calendar.WEEK_OF_YEAR);
            System.out.println("out = " + weekIn);
        }
        if (this.weekOut == 0) {
            this.weekOut = cal.get(Calendar.WEEK_OF_YEAR) + 1;
            System.out.println("out = " + weekOut);
        }
        weeks = new ArrayList<>();
        for (int i = 1; i < 53; i++) {
            weeks.add(i);
        }
    }

    public List<Integer> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<Integer> weeks) {
        this.weeks = weeks;
    }

    public boolean isStrict() {
        return strict;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public int getLoyerMax() {
        return loyerMax;
    }

    public void setLoyerMax(int loyerMax) {
        this.loyerMax = loyerMax;
    }

    public int getLoyerMin() {
        return loyerMin;
    }

    public void setLoyerMin(int loyerMin) {
        this.loyerMin = loyerMin;
    }

    public boolean isGarage() {
        return garage;
    }

    public void setGarage(boolean garage) {
        this.garage = garage;
    }

    public boolean isPiscine() {
        return piscine;
    }

    public void setPiscine(boolean piscine) {
        this.piscine = piscine;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public HibernateAppartDao getDb() {
        return db;
    }

    public void setDb(HibernateAppartDao db) {
        this.db = db;
    }

    public int getYearIn() {
        return yearIn;
    }

    public void setYearIn(int yearIn) {
        this.yearIn = yearIn;
    }

    public int getWeekIn() {
        return weekIn;
    }

    public void setWeekIn(int weekIn) {
        this.weekIn = weekIn;
    }

    public int getYearOut() {
        return yearOut;
    }

    public void setYearOut(int yearOut) {
        this.yearOut = yearOut;
    }

    public int getWeekOut() {
        return weekOut;
    }

    public void setWeekOut(int weekOut) {
        this.weekOut = weekOut;
    }

    public int getLevel() {
        return level;
    }

    public void resetLevel() {
        this.level = SEARCH_LEVEL;
    }

    public void levelDown() {
        if (level > 0) {
            level--;
        }
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String buildQuerry(int idU) {
        String out = "from Appart where ";
        //les apparts où je ne suis pas proprio
        if (idU != 0) {
            out = out + " proprio_idU !=" + idU + " ";
        }
        // faut trouver à quel niveau on vire la date... ou c'est critaire persistant ?
        List<String> str = new ArrayList();
        if (level >= 5) {
            if (garage) {
                str.add("garage='1'");
            }
            if (piscine) {
                str.add("piscine='1'");
            }
        }
        if (level >= 4) {
            if (superficie > 0) {
                str.add("superficie='" + superficie + "'");
            }
        }
        if (level >= 3) {
            if (postCode > 0) {
                str.add("postCode='" + postCode + "'");
            }
        }
        if (level >= 2) {
            if (pieces > 0) {
                str.add("pieces='" + pieces + "'");
            }

        }
        if (level >= 1) {
            if (loyerMin > 0) {
                str.add("loyer>='" + loyerMin + "'");
            }
            if (loyerMax > 0) {
                str.add("loyer<='" + loyerMax + "'");
            }
             // ca a l'aire de marcheR..  vraiment il faut penser objet... c'est pas evident...
            str.add(" idA not in ( select appart from LocationActive where weekIn<=" + this.weekOut + " AND weekOut>=" + this.weekIn + ")");
        }

        // date search in location actives
        if (!str.isEmpty()) {
            out = out + parseQuerry(str);
        }

        // out = out +" AND idA not in "+ buildDateQuerry();
        System.out.println(" out :" + out);
        return out;
    }

    private String parseQuerry(List<String> str) {
        String out = " and";
        for (int i = 0; i < str.size(); i++) {
            out = out + " " + str.get(i);
            if (i != str.size() - 1) {
                out = out + " and ";
            }
        }

        return out;
    }
    /*  private String buildDateQuerry() {
     return "( select appart_idA from LocationActive where LocationActive.dateIn<='"+this.dateOut+"' AND LocationActive.dateOut>='"+this.dateIn+"')";
     } */

    /*  private Date str2Date(String input) {
     String tmp[] = input.split("-");
     if (tmp.length == 3) {
     int annee = Integer.parseInt(tmp[0]);
     int mois = Integer.parseInt(tmp[1]);
     int jour = Integer.parseInt(tmp[2]);


     return new Date(annee, mois, jour);
     } else {
     return null;
     }
     } */
}

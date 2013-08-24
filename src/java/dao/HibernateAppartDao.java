/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Appart;
import model.LocationActive;
import model.SearchForm;
import model.User;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author baxter
 */
@Component
public class HibernateAppartDao implements IGenericDao {

    public HibernateAppartDao() {
    }
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public boolean delete(long id) {
        Appart a = (Appart) hibernateTemplate.get(Appart.class, id);
        if (a != null) {
            try {
                hibernateTemplate.delete(a);
                return true;
            } catch (Exception e) {
                System.err.println("Problème DB : " + e);
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean save(Object a) {
        System.err.println("save appart");
        if (a != null) {
            try {
                hibernateTemplate.saveOrUpdate((Appart) a);
                System.err.println("saved ");
                return true;
            } catch (Exception e) {
                System.err.println("Problème DB : " + e);
                return false;
            }
        }
        return false;
    }

    @Override
    public List<Object> selectAll() {
        return hibernateTemplate.find(" from Appart");
    }

    public List<Appart> selectUsersApparts(int userId) {
        return hibernateTemplate.find(" from Appart where proprio_idU =" + userId);
    }

    public List<Appart> customSearch(String querry) {
        return hibernateTemplate.find(querry);
    }

    public List<Appart> customSearch(SearchForm searcher, User u) {
        DetachedCriteria crit = DetachedCriteria.forClass(Appart.class);

        switch (searcher.getLevel()) {
            case 5:
                if (searcher.isGarage()) {
                    crit.add(Restrictions.eq("garage", 1));
                }
                if (searcher.isPiscine()) {
                    crit.add(Restrictions.eq("piscine", 1));
                }
            case 4:
                if (searcher.getSuperficie() > 0) {
                    crit.add(Restrictions.ge("superficie", searcher.getSuperficie()));
                }

            case 3:
                if (searcher.getPostCode() > 0) {
                    crit.add(Restrictions.ge("postCode", searcher.getPostCode()));
                }

            case 2:
                if (searcher.getPieces() > 0) {
                    crit.add(Restrictions.ge("pieces", searcher.getPieces()));
                }
                
            case 1:
                if (searcher.getLoyerMax() > 0 && searcher.getLoyerMax() > 0) {
                    crit.add(Restrictions.between("loyer", searcher.getLoyerMin(), searcher.getLoyerMax()));
                } else if (searcher.getLoyerMax() > 0) {
                    crit.add(Restrictions.le("loyer", searcher.getLoyerMax()));
                } else if (searcher.getLoyerMin() > 0) {
                    crit.add(Restrictions.ge("loyer", searcher.getLoyerMin()));
                }
        }
        
        if (u.getIdU() !=0){
         crit.add(Restrictions.ne("proprio",u));
        }
        // Period est utilisé toujour.
        
        


        //Criterion weekB = Restrictions.between("weekIn", loc.getWeekIn(), loc.getWeekOut()); // ehh à verifier  tout ca
        //Criterion weekE = Restrictions.between("weekOut", loc.getWeekIn(), loc.getWeekOut());
        // Criterion appart = Restrictions.eq("appart", loc.appart);
        //http://docs.jboss.org/hibernate/envers/3.6/javadocs/org/hibernate/criterion/Restrictions.html


        return hibernateTemplate.findByCriteria(crit);
    }

    @Override
    public Object selectOne(long id) {
        Appart a = null;
        try {
            a = (Appart) hibernateTemplate.find(" from Appart where idA=" + id).get(0);

        } catch (Exception e) {
            System.out.println("Problème DB  : " + e);
        }
        return a;
    }

    @Override
    public Object selectOne(String name) {
        System.out.println(" can't be aplied for appart  :");
        return null;
        // throw new UnsupportedOperationException("Not supported in Appart"); 
    }

    @Override
    public int count() {
        return DataAccessUtils.intResult(hibernateTemplate.find("select count(*) from Appart"));
    }

    @Override
    public Object getLast() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

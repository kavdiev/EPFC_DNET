/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.LocationActive;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author kavdiev
 */
@Component
public class HibernateLocationActiveDao implements IGenericDao {

    public HibernateLocationActiveDao() {
    }
    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    @Autowired
    HibernateAppartDao hAppart;

    @Override
    public boolean delete(long id) {
        LocationActive loc = (LocationActive) hibernateTemplate.get(LocationActive.class, id);
        if (loc != null) {
            try {
                hibernateTemplate.delete(loc);
                return true;
            } catch (Exception e) {
                System.err.println("Problème DB : " + e);
                return false;
            }
        }
        return false;
    }

    public boolean isFree(LocationActive loc) {
        Criteria crit= hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(LocationActive.class);
        Criterion weekB = Restrictions.between("weekIn", loc.getWeekIn(), loc.getWeekOut()); // ehh à verifier  tout ca
        Criterion weekE = Restrictions.between("weekOut", loc.getWeekIn(), loc.getWeekOut());
        //http://docs.jboss.org/hibernate/envers/3.6/javadocs/org/hibernate/criterion/Restrictions.html
        crit.add(weekB); //// remplace le tamplete habituelle
        crit.add(weekE);
        
        List<LocationActive> locations = hibernateTemplate.find(" from LocationActive where idA=" + loc.getAppart().getIdA()+" "); // ajouter semaine in et out.. + year
        if (locations.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean save(Object loc) {
        System.err.println("save location active");
        if (loc != null) {
            try {
                hibernateTemplate.saveOrUpdate((LocationActive) loc);
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
        return hibernateTemplate.find(" from LocationActive");
    }

    public List<LocationActive> selectToAprove(int idU) {
        
        return hibernateTemplate.find("from LocationActive  where approuved=0 AND appart_idA not in ( from Appart where proprio_idU="+idU+")");
    }

    @Override
    public Object selectOne(long id) {
        LocationActive loc = null;
        try {
            System.out.println("loc id  : " + id);
            loc = (LocationActive) hibernateTemplate.find(" from LocationActive where id=" + id).get(0);

        } catch (Exception e) {
            System.out.println("Problème DB  : " + e);
        }
        return loc;
    }

    @Override
    public Object selectOne(String name) {
        System.out.println(" can't be aplied for location active  :");
        return null;
    }

    @Override
    public int count() {
        return DataAccessUtils.intResult(hibernateTemplate.find("select count(*) from LocationActive"));
    }

    @Override
    public Object getLast() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

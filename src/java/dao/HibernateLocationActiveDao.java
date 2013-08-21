/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Calendar;
import java.util.List;
import model.LocationActive;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
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
    private SessionFactory sessionFactory;
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
        //faut verifier si lce type de recherche convient bien 
        //Criteria crit= hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(LocationActive.class);
        DetachedCriteria crit = DetachedCriteria.forClass(LocationActive.class);
        Criterion weekB = Restrictions.between("weekIn", loc.getWeekIn(), loc.getWeekOut()); // ehh à verifier  tout ca
        Criterion weekE = Restrictions.between("weekOut", loc.getWeekIn(), loc.getWeekOut());
        Criterion appart = Restrictions.eq("appart", loc.appart);
        //http://docs.jboss.org/hibernate/envers/3.6/javadocs/org/hibernate/criterion/Restrictions.html
        crit.add(weekB);
        crit.add(weekE);
        crit.add(appart);

        List<LocationActive> locations = hibernateTemplate.findByCriteria(crit);
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
        return hibernateTemplate.find("from LocationActive  where approuved=0 AND appart_idA  in ( from Appart where proprio_idU=" + idU + ")");
    }

    public List<LocationActive> getAllReservationFromNow(int idA) {
        Calendar cal = Calendar.getInstance();
        return hibernateTemplate.find("from LocationActive  where appart_idA =" + idA + " and weekIn>=" + cal.get(Calendar.WEEK_OF_YEAR) + " order by weekIn");
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

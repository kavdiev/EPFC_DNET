/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.LocationActive;
import org.springframework.beans.factory.annotation.Autowired;
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
}
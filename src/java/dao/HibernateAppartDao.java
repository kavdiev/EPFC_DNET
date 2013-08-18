/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Appart;
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
        return false;}

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
        return false;}

    @Override
    public List<Object> selectAll() {
        return hibernateTemplate.find(" from Appart");
    }
    
    public List<Appart> selectUsersApparts(int userId) {
        return hibernateTemplate.find(" from Appart where proprio_idU ="+userId);
    }
    
    public List <Appart> customSearch (String querry) {
        return hibernateTemplate.find(querry);
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

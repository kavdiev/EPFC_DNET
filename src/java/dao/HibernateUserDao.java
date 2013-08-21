/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author Administrateur
 */
@Component
public class HibernateUserDao implements IGenericDao {

    public HibernateUserDao() {
    }
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public boolean delete(long id) {
        User u = (User) hibernateTemplate.get(User.class, id);
        if (u != null) {
            try {
                hibernateTemplate.delete(u);
                return true;
            } catch (Exception e) {
                System.err.println("Problème DB : " + e);
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean save(Object u) {
        System.err.println("save user");
        if (u != null) {
            try {
                hibernateTemplate.saveOrUpdate((User) u);
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
        return hibernateTemplate.find(" from User");
    }

    public List<Object> selectAllAdmins() {
        return hibernateTemplate.find(" from User where admin=1");
    }

    public List<Object> selectAllProprios() {
        return hibernateTemplate.find(" from User where idU in (select proprio from Appart)");
    }

    @Override
    public Object selectOne(long id) {
        User u = null;
        try {
            System.out.println("user id  : " + id);
            u = (User) hibernateTemplate.find(" from User where idU=" + id).get(0);

        } catch (Exception e) {
            System.out.println("Problème DB  : " + e);
        }
        System.out.println("user name  : " + u.getNom());
        return u;
    }

    @Override
    public Object selectOne(String name) {
        User u = null;
        try {
            u = (User) hibernateTemplate.find(" from User where nom='" + name + "'").get(0);

        } catch (Exception e) {
            System.out.println("Problème DB  : " + e);
        }
        return u;
    }

    public String login(String name, String password) {

        User u = (User) selectOne(name);
        if (u != null) {
            if (!u.getPassword().equals(password)) {
                return "u02";
            }
        } else {
            return "u01";
        }
        return "ok";
    }

    public String userExist(String name) {
        User u = (User) selectOne(name);
        if (u != null) {
            return "u03";
        }

        return "ok";  // ok => n'existe pas
    }

    @Override
    public int count() {
        return DataAccessUtils.intResult(hibernateTemplate.find("select count(*) from User"));
    }
//  cette maniere est plus efficace elle n'instancie pas d'objet user. (lazy possibilité d'utiliser le selectAdmins de plus haut et regarder la taille de la liste

    public int countAdmins() {
        return DataAccessUtils.intResult(hibernateTemplate.find("select count(*) from User where admin=1"));
    }

    @Override
    public Object getLast() {
        return (User) hibernateTemplate.find(" from User where idU= (select max(idU) from User)").get(0);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author baxter
 */
public class HiberBDDConnector {

    public HiberBDDConnector() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }
    private static Session session;
    private Query query;
    private static SessionFactory sessionFactory;

    private void connect() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    private void stop() {
        if (session.isConnected()) {
            session.close();
        }
    }

    public List<User> getAllUsers() {
        connect();
        query = session.createQuery("from User");
        return query.list();
    }

    public List<User> getAllAdmins() {
        session.beginTransaction();
        query = session.createQuery("from User where admin='1'");
        return query.list();
    }
}

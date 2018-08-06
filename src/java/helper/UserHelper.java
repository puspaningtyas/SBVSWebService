/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.User;
import util.SBVSHibernateUtil;

/**
 *
 * @author user only
 */
public class UserHelper {

    public UserHelper() {
    }

    public void saveDataUser(String email, String name,
            String institution,String address,String phone) {
        Session session = SBVSHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        User utbl = new User();
        if (!email.contains("") || email != null) {
            utbl.setEmail(email);
            utbl.setName(name);
            utbl.setInstitution(institution);
            utbl.setAddress(address);
            utbl.setPhone(phone);
            session.saveOrUpdate(utbl);
            tx.commit();
        }
        session.close();
    }

    public List<User> userList() {
        Session session = SBVSHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<User> list = null;
        Query q = session.createQuery("from User u");
        list = q.list();
        tx.commit();
        session.close();
        return list;
    }

    public boolean isUserExist(String email) {
        Session session = SBVSHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<User> list = null;
        String query = "from User u where u.email=:em ";
        Query q = session.createQuery(query);
        q.setParameter("em", email);
        list = q.list();
        tx.commit();
        session.close();
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}

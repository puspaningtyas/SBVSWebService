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
import pojos.UserCompound;
import util.SBVSHibernateUtil;

/**
 *
 * @author user only
 */
public class UserCompoundHelper {

    public UserCompoundHelper() {
    }
    
    public UserCompound isCompoundActive(String compound){
        Session session = SBVSHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        List<UserCompound> list = null;
        String query = "from UserCompound u where u.compound=:cmp ";
        Query q = session.createQuery(query);
        q.setParameter("cmp", compound);
        list = q.list();
        tx.commit();
        session.close();
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return new UserCompound(compound, "no email","not found");
        }
    }
    
    public void saveDataUserCompound(String compound,String email, String result) {
        Session session = SBVSHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        UserCompound utbl = new UserCompound();
        if (!compound.contains("") || compound != null) {
            utbl.setEmail(email);
            utbl.setCompound(compound);
            utbl.setResult(result);
            session.saveOrUpdate(utbl);
            tx.commit();
        }
        session.close();
    }
    
}

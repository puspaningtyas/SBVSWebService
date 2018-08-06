/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Log;
import pojos.UserCompound;
import util.SBVSHibernateUtil;

/**
 *
 * @author user only
 */
public class LogHelper {

    public LogHelper() {
    }

    public void saveDataLog(Date time, String function, String email,
            String compound, String status, String id,
            Integer processingTime) {
        Session session = SBVSHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Log log = new Log();
        log.setTime(time);
        log.setEmail(email);
        log.setFunction(function);
        log.setCompound(compound);
        log.setStatus(status);
        log.setId(id);
        log.setProcessingTime(processingTime);
        session.saveOrUpdate(log);
        tx.commit();
        session.close();
    }

    public void saveLog(String input) {
        Session session = SBVSHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar cal = Calendar.getInstance();
        String tgl = dateFormat.format(cal.getTime());
        Date date = cal.getTime();
        System.out.println(date);
        System.out.println(tgl);

        LogHelper helperLog = new LogHelper();
        UserCompoundHelper helper = new UserCompoundHelper();
        long startTime = System.currentTimeMillis();
        long stopTime = System.currentTimeMillis();
        int processTime = (int) stopTime - (int) startTime;

        helperLog.saveDataLog(date, "isCompoundActive", "no email", input,
                "just reply", tgl, processTime);

        tx.commit();
        session.close();
    }
}

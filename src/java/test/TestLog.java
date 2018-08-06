/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import helper.LogHelper;
import helper.UserCompoundHelper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import static javax.print.attribute.Size2DSyntax.MM;
import pojos.UserCompound;

/**
 *
 * @author user only
 */
public class TestLog {

    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar cal = Calendar.getInstance();
        String tgl = dateFormat.format(cal.getTime());
        Date date = cal.getTime();
        System.out.println(date);
        System.out.println(tgl);

        LogHelper helperLog = new LogHelper();
        UserCompoundHelper helper = new UserCompoundHelper();
        long startTime = System.currentTimeMillis();
        String compound = "ccccco";
        UserCompound result = helper.isCompoundActive(compound);
        long stopTime = System.currentTimeMillis();
        int processTime = (int)stopTime-(int)startTime;
        
        helperLog.saveDataLog(date, "isCompoundActive", "no email", compound,
                "reply:" + result.toString(), tgl,processTime);
        System.out.println("Processing time1: "+(stopTime-startTime));
        System.out.println("Processing time2: "+processTime);
        System.out.println(result);
    }
}

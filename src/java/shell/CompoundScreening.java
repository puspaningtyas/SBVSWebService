/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shell;

import helper.LogHelper;
import helper.UserCompoundHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojos.UserCompound;

/**
 *
 * @author user only
 */
public class CompoundScreening {
    public static final String command="/home/puspa/sbvs/sbvs.sh";
    public static UserCompound execute(String compound, String email){
        // set log for start time
//        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//        Calendar cal = Calendar.getInstance();
//        String tgl = dateFormat.format(cal.getTime());
//        Date date = cal.getTime();
//        LogHelper helperLog = new LogHelper();
//        long startTime = System.currentTimeMillis();
//        
        // set compound user
        UserCompound result=new UserCompound();
        result.setCompound(compound);
        result.setEmail(email);
        result.setResult("not found");
        
        // access linux command prompt
        String screeningResult = BashExecutor.executeCommand(command,compound);
        result.setResult(screeningResult);
        // save result to database
        UserCompoundHelper helper = new UserCompoundHelper();
        helper.saveDataUserCompound(result.getCompound(), 
                result.getEmail(), result.getResult());
//        long stopTime = System.currentTimeMillis();
//            int processTime = (int) stopTime - (int) startTime;
//            helperLog.saveDataLog(date, "isCompoundActive_with_bash", result.getEmail(),
//                   result.getCompound(),
//                    "reply:" + result.toString(), tgl, processTime);
        return result;
    }
    
    public static final String conversionCommand="/var/lib/tomcat7/check_comp.sh";
    public static void compoundConversion(String infileName, String outfile){
        BashExecutor.executeCommand2(conversionCommand,infileName,outfile);
    }
    
    public static final String conversionCommandSMI2MOL2="/var/lib/tomcat7/conv_smi2mol2.sh";
    public static void compoundConversionSMI2MOL2(String inSMIfileName,
            String temp74SMIfileName,
            String tempMOL2fileName){
        BashExecutor.executeCommand3(conversionCommandSMI2MOL2,inSMIfileName,
                temp74SMIfileName,
                tempMOL2fileName);
    }
    
    public static void compoundConversionSporesMOL2(
            String tempMol2fileName,
            String sporesMol2fileName){
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(new File("/home/puspa/sbvs"));
        builder.command("/bin/bash", "-c","babel","--gen3d",
                "-ismi",tempMol2fileName,"-omol2",sporesMol2fileName);
        Process process;
        StringBuffer output = new StringBuffer();
        String outputString = new String();
        try {
            process = builder.start();
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            outputString = output.toString();
        } catch (IOException ex) {
            Logger.getLogger(CompoundScreening.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public static final String conversionCommandSporesMOL2="/var/lib/tomcat7/conv_SPORES_mol2.sh";
    public static void compoundConversionSporesMOL2versi2(
            String tempMol2fileName,
            String sporesMol2fileName){
        BashExecutor.executeCommand2(conversionCommandSporesMOL2,
                tempMol2fileName,sporesMol2fileName);
        
    }
}

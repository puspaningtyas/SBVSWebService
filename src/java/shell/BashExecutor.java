/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user only
 */
public class BashExecutor {
    public static final String dir ="/home/puspa/sbvs/";
    
    
    public static String executeCommand(String command, String parameter) {
        String[] cmdScript = new String[]{"/bin/bash", command, parameter};
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(cmdScript);
            p.waitFor();
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static String executeCommand2(String command, 
            String parameter1, String parameter2) {
        String[] cmdScript = new String[]{"/bin/bash", command, parameter1,parameter2};
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(cmdScript);
            p.waitFor();
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
    
    public static String executeCommand3(String command, 
            String parameter1, String parameter2,
            String parameter3) {
        String[] cmdScript = new String[]{"/bin/bash", command, 
            parameter1,parameter2,parameter3};
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(cmdScript);
            p.waitFor();
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
    
    public static String executeCommand4(String command, 
            String parameter1, String parameter2,
            String parameter3, String parameter4) {
        String[] cmdScript = new String[]{"/bin/bash", command, 
            parameter1,parameter2,parameter3,parameter4};
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(cmdScript);
            p.waitFor();
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
    
    // return file name
    public static File saveInputCompound(String parameter,String tgl) throws FileNotFoundException {
//        String fileName=null;
        File file = null;
        try {
            String fileName = "in" + tgl + ".smi";
            file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(parameter.getBytes());
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(BashExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file;
    }
    
    // execute shell command
    public static String executeShellCommand (String command){
        StringBuffer output = new StringBuffer();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
    
    // return content of compound file
    public static String readCompoundFile(File file) throws FileNotFoundException {
        String result=new String();
        try {
            FileInputStream fis = new FileInputStream(file);
            int input;
            while((input = fis.read())!=-1){
                result=result+(char)input;
            }
            fis.close();
        } catch (IOException ex) {
            Logger.getLogger(BashExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static String testExecuteMain(String input) {
        return input;
    }
}

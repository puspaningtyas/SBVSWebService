/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import helper.LogHelper;
import helper.UserCompoundHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import pojos.UserCompound;
import shell.BashExecutor;
import shell.CompoundScreening;

/**
 * REST Web Service
 *
 * @author user only
 */
@Path("compound")
public class UserCompoundResource {

    private UserCompoundHelper helper;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserCompoundResource
     */
    public UserCompoundResource() {
        helper = new UserCompoundHelper();
    }

    /**
     * Retrieves representation of an instance of service.UserCompoundResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UserCompoundResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @GET
    @Path("getCompoundStatus")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCompoundStatus(@QueryParam("compound") String compound,
            @QueryParam("email") String email) {
        // set log for start time
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar cal = Calendar.getInstance();
        String tgl = dateFormat.format(cal.getTime());
        Date date = cal.getTime();
        LogHelper helperLog = new LogHelper();
        long startTime = System.currentTimeMillis();
        // call service
        Gson gson = new Gson();
        UserCompound userCompound = null;
        userCompound = helper.isCompoundActive(compound);
        // set log for finish time
        long stopTime = System.currentTimeMillis();
        int processTime = (int) stopTime - (int) startTime;
        helperLog.saveDataLog(date, "isCompoundActive", email, compound,
                "reply:" + userCompound.toString(), tgl, processTime);

        return gson.toJson(userCompound);
    }

    @POST
    @Path("getCompoundActiveStatus")
    @Consumes(MediaType.APPLICATION_JSON)
    public String getCompoundActiveStatus(String input) {
        // set log for start time
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar cal = Calendar.getInstance();
        String tgl = dateFormat.format(cal.getTime());
        Date date = cal.getTime();
        LogHelper helperLog = new LogHelper();
        long startTime = System.currentTimeMillis();
        // call service
        Gson gson = new Gson();
        UserCompound tempCompound = gson.fromJson(input, UserCompound.class);
        UserCompound userCompound = null;
        String fileOut = new String();
        String directory = "/home/puspa/sbvs";
        String directory1 = "/home/puspa/sbvs1";
        try {
            //standardize input
            File file = BashExecutor.saveInputCompound(tempCompound.getCompound(), tgl);
            fileOut = directory + "/t" + file.getName();
            CompoundScreening.compoundConversion(file.getAbsolutePath(),
                     fileOut);
            tempCompound.setCompound(BashExecutor.readCompoundFile(new File(fileOut)));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserCompoundResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        // cek input
        userCompound = helper.isCompoundActive(tempCompound.getCompound());
        boolean isNotFound = false;
        if (userCompound.getResult() == "not found") {
            CompoundScreening.compoundConversionSMI2MOL2(fileOut,
                    directory + "/tm" + tgl + ".smi",
                    directory + "/tm" + tgl + ".mol2");
            CompoundScreening.compoundConversionSporesMOL2versi2(
                    directory + "/tm" + tgl + ".mol2",
                    directory + "/tin" + tgl + ".mol2");

            userCompound = CompoundScreening.execute(tempCompound.getCompound(),
                    tempCompound.getEmail());
            isNotFound = true;
        }
        // set log for finish time
        long stopTime = System.currentTimeMillis();
        int processTime = (int) stopTime - (int) startTime;
        if (isNotFound == false) {
            helperLog.saveDataLog(date, "isCompoundActive", tempCompound.getEmail(),
                    tempCompound.getCompound(),
                    "reply:" + userCompound.toString(), tgl, processTime);
        } else {
            helperLog.saveDataLog(date, "isCompoundActive_with_bash", tempCompound.getEmail(),
                    tempCompound.getCompound(),
                    "reply:" + userCompound.toString(), tgl, processTime);
        }
//        helperLog.saveLog(input);
        return gson.toJson(userCompound.getResult());
    }
}

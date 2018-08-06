/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import helper.UserHelper;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pojos.User;

/**
 * REST Web Service
 *
 * @author user only
 */
@Path("user")
public class UserResource {

    private UserHelper helper;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
        helper = new UserHelper();
    }

    /**
     * Retrieves representation of an instance of service.UserResource
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
     * PUT method for updating or creating an instance of UserResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @POST
    @Path("saveDataUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveDataUser(String data) {
        Gson gson = new Gson();
        User userTabel = gson.fromJson(data, User.class);
        try {
            helper.saveDataUser(userTabel.getEmail(), userTabel.getName(),
                    userTabel.getInstitution(), userTabel.getAddress(),
                    userTabel.getPhone());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return Response.status(200)
                .entity(userTabel)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }
}

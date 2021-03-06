/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.common.net.MediaType;
import com.google.gson.Gson;
import helper.PasienHelper;
import javafx.scene.media.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import pojos.Pasien;

/**
 *
 * @author asus
 */
@Path("pasien")
public class PasienResource {
     @Context
    private UriInfo context;

    /**
     * Creates a new instance of lokasiResource
     */
    public PasienResource() {
    }

    /**
     * Retrieves representation of an instance of services.lokasiResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getJson() {
        //TODO return proper representation object
        PasienHelper p = new PasienHelper();
        Gson gson = new Gson();
        return Response.status(Response.Status.OK)
                .entity(gson.toJson(p.bacaSemuaPasien()))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods",
                        "GET,POST,HEAD,OPTIONS,PUT")
                .header("Access-Control-Allow-Headers",
                        "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers")
                .header("Access-Exposed-Headers",
                        "Access-Control-Allow-Origin,Access-Control-Allow-Credentials")
                .header("Access-Support-Credentials",
                        "true")
                .header("Access-Control-Max-Age", "20")
                .header("Access-Preflight-Maxage", "20")
                .build();
    }
      
    @GET
    @Path("cariPasien")
   @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response CariPasien(
            @QueryParam("nik") String nik){
       PasienHelper helper = new PasienHelper();
       Pasien hasil = helper.cariPasien(nik);
       Gson gson = new Gson();
         return Response.status(200)
                .entity(gson.toJson(hasil))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods",
                        "GET,POST,HEAD,OPTIONS,PUT")
                .header("Access-Control-Allow-Headers",
                        "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers")
                .header("Access-Exposed-Headers",
                        "Access-Control-Allow-Origin,Access-Control-Allow-Credentials")
                .header("Access-Support-Credentials",
                        "true")
                .header("Access-Preflight-Maxage", "2000")
                .build();
    }

    /**
     * PUT method for updating or creating an instance of lokasiResource
     *
     * @param content representation for the resource
//     */
//    @PUT
//    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
//    public void putJson(String content) {
 //   }
    
@POST
@Path("addPasien")
 @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
public Response AddNewPasien(String data){
    Gson gson=new Gson();
    Pasien pasien= gson.fromJson(data,Pasien.class);
    PasienHelper helper =new PasienHelper();
    helper.addNewPasien(
        pasien.getNik(),
            pasien.getNama(),
            pasien.getAlamat(),
            pasien.getNik(),
            pasien.getTanggalLahir(),
            pasien.getKelamin()
            );
    return Response.status(200).entity(pasien).build();
}

    

 
    
}


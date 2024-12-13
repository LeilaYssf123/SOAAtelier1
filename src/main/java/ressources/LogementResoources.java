package ressources;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/logement")

public class LogementResoources {
   static LogementBusiness logementBusiness = new LogementBusiness();
    //web service : list logement=>json
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        return  Response
                .ok()
                .entity(this.logementBusiness.getLogements())
                .build();

    }

    //Ajouter

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)

    public  Response addLogement(Logement logement){
        if(this.logementBusiness.addLogement(logement)){
        return  Response.status(201).entity("created!").build();

    }else
            return  Response.status(200).entity("Error!").build();

    }

    //update
    @PUT
    @Path("/update/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateLogement(@PathParam("reference") int reference, Logement logement){
        if(this.logementBusiness.updateLogement(reference, logement)){
            return Response.status(201).entity("updated!").build();
        }else
            return Response.status(200).entity("error ").build();
    }

    //Delete
    @DELETE
    @Path("/delete/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteLogement(@PathParam("reference") int reference){
        if(this.logementBusiness.deleteLogement(reference)){
            return Response.status(200).entity("deleted!").build();
        }else
            return Response.status(200).entity("error ").build();
    }
    //get logement par delegation

    @GET
    @Path("/delegation/{delegation}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDelegation(@PathParam("delegation") String delegation){
        return Response.ok(logementBusiness.getLogementsByDeleguation(delegation)).build();
    }

    //get logement par reference
    @GET
    @Path("/REF/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReference(@PathParam("reference") int reference){
        List<Logement> logements = this.logementBusiness.getLogementsListeByref(reference);
        if (logements.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).entity("aucune logemet avec cette reference ").build();
        }else
            return Response.ok(logements).build();
    }


    }



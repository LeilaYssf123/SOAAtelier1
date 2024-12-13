package ressources;

import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/RendezVous")
public class RendezVousRessources {
    RendezVousBusiness rendezVousBusiness = new RendezVousBusiness();




    //ajout RendezVous
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/newRDV")
    public Response newRendezVous(RendezVous rendezVous) {
        if (this.rendezVousBusiness.addRendezVous(rendezVous)) {
            return Response.status(201).entity("Rendez Vous ajouteé!").build();
        }else
            return Response.status(400).entity("Error d'ajout !").build();
    }

    //get tout les  RendezVous
    @GET
    @Path("/listRDV")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        return  Response
                .ok()
                .entity(this.rendezVousBusiness.getListeRendezVous())
                .build();

    }

    //get rendezVous par reference
    @GET
    @Path("/RDV/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousBylogementRef(@PathParam("reference") int reference) {
        List<RendezVous> rendezVousList = rendezVousBusiness.getListeRendezVousByLogementReference(reference);
        return Response.status(200).entity(rendezVousList).build();
    }


    //delete RendezVous
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteRendezVous(@PathParam("id") int id) {
        if (this.rendezVousBusiness.deleteRendezVous(id)) {
            return Response.status(200).entity("rendez-vous suppriméé ! ").build();
        }else
            return Response.status(400).entity("Erreur de suppression !").build();
    }

    //update rdv
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateRendezVous(@PathParam("id") int id, RendezVous rendezVous) {
        if(rendezVousBusiness.updateRendezVous(id, rendezVous)) {
            return Response.status(200).entity("Rendez-vous updated ! ").build();
        } else {
            return Response.status(400).entity("Rendez-vous non trouvé !").build();
        }
    }



}

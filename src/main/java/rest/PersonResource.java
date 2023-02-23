package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import dtos.RenameMeDTO;
import facades.FacadeExample;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PostLoad;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final PersonFacade FACADE = PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPersons() {
        return Response.ok().entity(GSON.toJson(FACADE.getAllPersons())).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") int id) {
        PersonDTO pDTO = FACADE.getPerson(id);
        return Response.ok().entity(GSON.toJson(pDTO)).build();
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok().entity(GSON.toJson(FACADE.getAllPersons())).build();
    }

    @PUT
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") int id, String content) {
        PersonDTO pdto = GSON.fromJson(content, PersonDTO.class);
        pdto.setId(id);
        PersonDTO updated = FACADE.editPerson(pdto);
        return Response.ok().entity(GSON.toJson(updated)).build();
    }


    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(String content) {
        PersonDTO pdto = GSON.fromJson(content, PersonDTO.class);
        PersonDTO newPdto = FACADE.addPerson(pdto.getFirstName(), pdto.getLastName(), pdto.getPhone());
        return Response.ok().entity(GSON.toJson(newPdto)).build();
    }
}

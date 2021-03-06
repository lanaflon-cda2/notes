package com.douwe.notes.resource;

import com.douwe.notes.entities.Niveau;
import com.douwe.notes.entities.Semestre;
import com.douwe.notes.service.ServiceException;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Vincent Douwe <douwevincent@yahoo.fr>
 */
public interface INiveauResource {
    
    @POST
    @Produces(value = "application/json")
    Niveau createNiveau(Niveau niveau);

    @GET
    @Produces(value = "application/json")
    List<Niveau> getAllNiveaux() throws ServiceException;

    @GET
    @Path(value = "{id : \\d+}")
    @Produces(value = "application/json")
    Niveau getNiveau(@PathParam(value = "id")long id);

    @PUT
    @Path(value = "{id : \\d+}")
    @Produces(value = "application/json")
    Niveau updateNiveau(@PathParam(value = "id")long id, Niveau niveau);

    @DELETE
    @Path(value = "{id : \\d+}")
    void deleteNiveau(@PathParam(value = "id")long id);
    
    @GET
    @Path(value = "{id : \\d+}/semestres")
    @Produces(value = "application/json")
    List<Semestre> getAllSemestre(@PathParam(value = "id")long id);
}

package com.douwe.notes.resource;

import com.douwe.notes.entities.Cycle;
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
public interface ICycleResource {

    @POST
    @Produces(value = "application/json")
    Cycle createCycle(Cycle cycle);

    @GET
    @Produces(value = "application/json")
    List<Cycle> getAllCycle();

    @GET
    @Path(value = "{id : \\d+}")
    @Produces(value = "application/json")
    Cycle getCycle(@PathParam(value = "id")long id);

    @PUT
    @Path(value = "{id : \\d+}")
    @Produces(value = "application/json")
    Cycle updateCycle(@PathParam(value = "id")long id, Cycle cycle);

    @DELETE
    @Path(value = "{id : \\d+}")
    void deleteCycle(@PathParam(value = "id")long id);

}

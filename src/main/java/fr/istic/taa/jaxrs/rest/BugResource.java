package fr.istic.taa.jaxrs.rest;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import fr.istic.taa.jaxrs.dao.generic.BugDao;
import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.Bug;
import fr.istic.taa.jaxrs.domain.User;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
@Path("/bugs")
@Produces({"application/json"})
public class BugResource {
	private BugDao dao;
	public BugResource() {
		this.dao = new BugDao();
	}
	
  @GET
  @Path("/")
  public List<Bug> getBugs()  {
      return this.dao.findAll();
  }
  
  @GET
  @Path("/{id}")
  public Bug getBugById(@PathParam("id") Long id)  {
      return this.dao.findOne(id);
  }

  @POST
  @Path("/")
  @Consumes("application/json")
  public Response addBug(
		  @Parameter(description = "User object that needs to be added to the store", required = true)
		  @Valid
  Bug bug) {
	  dao.save(bug);
    return Response.ok().entity(bug).build();
  }
}

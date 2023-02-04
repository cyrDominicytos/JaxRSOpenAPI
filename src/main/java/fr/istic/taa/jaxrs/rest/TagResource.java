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

import fr.istic.taa.jaxrs.dao.generic.TagDao;
import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.domain.User;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */

@Path("/tags")
@Produces({"application/json"})
public class TagResource {
	private TagDao dao;
	public TagResource() {
		this.dao = new TagDao();
	}
	
  @GET
  @Path("/")
  public List<Tag> getTags()  {
      return this.dao.findAll();
  }
  
  @GET
  @Path("/{id}")
  public Tag getTagById(@PathParam("id") Long id)  {
      return this.dao.findOne(id);
  }

  @POST
  @Path("/")
  @Consumes("application/json")
  public Response addUser(
		  @Parameter(description = "User object that needs to be added to the store", required = true)
		  @Valid
		  Tag tag) {
	  dao.save(tag);
    return Response.ok().entity(tag).build();
  }
}
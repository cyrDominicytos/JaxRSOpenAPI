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

import fr.istic.taa.jaxrs.dao.generic.FeatureDao;
import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.Feature;
import fr.istic.taa.jaxrs.domain.User;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */

@Path("/features")
@Produces({"application/json"})
public class FeatureResource {
	private FeatureDao dao;
	public FeatureResource() {
		this.dao = new FeatureDao();
	}
	
  @GET
  @Path("/")
  public List<Feature> getFeatures()  {
      return this.dao.findAll();
  }
  
  @GET
  @Path("/{id}")
  public Feature getUserById(@PathParam("id") Long id)  {
      return this.dao.findOne(id);
  }

  @POST
  @Path("/")
  @Consumes("application/json")
  public Response addFeature(
		  @Parameter(description = "User object that needs to be added to the store", required = true)
		  @Valid
		  Feature feature) {
	  dao.save(feature);
    return Response.ok().entity(feature).build();
  }
}
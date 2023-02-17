package fr.istic.taa.jaxrs.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import fr.istic.taa.jaxrs.dao.generic.SupportDao;
import fr.istic.taa.jaxrs.dao.generic.SupportDao;
import fr.istic.taa.jaxrs.domain.Support;
import fr.istic.taa.jaxrs.dto.SupportDto;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */

@Path("/supports")
@Produces({"application/json"})
public class SupportResource {
	private SupportDao dao;
	public SupportResource() {
		this.dao = new SupportDao();
	}
	
  @GET
  @Path("/")
  public List<SupportDto> getSupports()  {
	  List<SupportDto> list = new ArrayList<>();
	  for(Support support: this.dao.findAll()){
		  list.add(new SupportDto.SupportDtoBuilder(support).build());
	  }
	  return list;
  }
  
  @GET
  @Path("/{id}")
  public SupportDto getSupportById(@PathParam("id") Long id)  {
      Support u =  this.dao.findOne(id);
      return new SupportDto.SupportDtoBuilder(u).build();
  }

  @POST
  @Path("/")
  @Consumes("application/json")
  public Response addSupport(
	  @Parameter(description = "Support object that needs to be added to the store", required = true)
	  @Valid
	  SupportDto SupportDto) {
	  try{
		  Support support = new Support();
		  support.setEmail(SupportDto.getEmail());
		  support.setName(SupportDto.getName());
		  support.setGrad(SupportDto.getGrad());
		  dao.save(support);
		  SupportDto.setId(support.getId());
		  return Response.ok().entity(SupportDto).build();
	  } catch (Exception e) {
		  return  Response.status(500).entity("You can not have two Supports with the same mail").build();	  
	  }
  }
}

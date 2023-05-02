package fr.istic.taa.jaxrs.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import fr.istic.taa.jaxrs.dao.generic.SupportDao;
import fr.istic.taa.jaxrs.dao.generic.SupportDao;
import fr.istic.taa.jaxrs.domain.Support;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.SupportCreateDto;
import fr.istic.taa.jaxrs.dto.SupportDto;
import fr.istic.taa.jaxrs.dto.UserCreateDto;
import fr.istic.taa.jaxrs.dto.UserDto;
import fr.istic.taa.jaxrs.services.DefaultValidator;
import fr.istic.taa.jaxrs.services.OldDataFormator;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.ConstraintViolation;

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
  public Response getSupports()  {
	  List<SupportDto> list = new ArrayList<>();
	  for(Support support: this.dao.findAll()){
		  list.add(new SupportDto(support));
	  }
	  return Response.ok().entity(list).build();
  }
  
  @GET
  @Path("/{id}")
  public Response getSupportById(@PathParam("id") Long id)  {      
      try {
    	  Support s  =  this.dao.findOne(id);
    	  if(s!=null) {
    		  SupportDto sDto = new SupportDto(s);
    		  return Response.ok().entity(sDto).build();  
    	  }else {
    		  return Response.status(Response.Status.NOT_FOUND).entity("There is no support with the id="+id).build(); 
    	  }
	  }catch(Exception e) {
		  return Response.status(500)
                  .entity(e.getMessage())
                  .build();
	  }
  }

  @POST
  @Path("/")
  @Consumes("application/json")
  public Response addSupport(
	  @Parameter(description = "Support object that needs to be added to the store", required = true)
	  SupportCreateDto supportDto) {
	  try {
		  DefaultValidator<SupportCreateDto> validator = new DefaultValidator<>();
		  Set<ConstraintViolation<SupportCreateDto>> violations = validator.getValidator().validate(supportDto);
	      if (violations.size()>0) {
	    	  return validator.toResponse(violations);
	      }else {
	    	  Support support = new Support();
			  support.setEmail(supportDto.getEmail());
			  support.setName(supportDto.getName());
			  support.setGrad(supportDto.getGrad());
			  support.setPassword(supportDto.getPassword());
			  dao.save(support);
			  support.setRole("Support");    	  
			  SupportDto dto = new SupportDto(support);
			  return Response.ok().entity(dto).build(); 
	      }
	  }catch(Exception e) {
		  return Response.status(Response.Status.BAD_REQUEST)
                  .entity(e.getMessage())
                  .build();
	  }
  }
  
  @PUT
  @Path("/{id}")
  @Consumes("application/json")
  public Response updateSupport(
		  @PathParam("id") Long id, 
		  @Parameter(description="Support object that needs to be updated", required = true) SupportCreateDto supportDto) {
	  try {
		  //Check if the tag id is valid
    	  Support support = this.dao.findOne(id);
    	  if(support==null) 
    		  return Response.status(Response.Status.NOT_FOUND).entity("There is no User with the id="+id).build(); 
    	  
		  //Validate the constraints on the supportDto
		  DefaultValidator<SupportCreateDto> validator = new DefaultValidator<>();
		  Set<ConstraintViolation<SupportCreateDto>> violations = validator.getValidator().validate(supportDto);
	      if (violations.size()>0)
	    	  return validator.toResponse(violations);
	      
		  support.setEmail(supportDto.getEmail());
		  support.setName(supportDto.getName());
		  support.setGrad(supportDto.getGrad());
		  support.setPassword(supportDto.getPassword());
		  dao.save(support);   	  
		  SupportDto dto = new SupportDto(support);
		  return Response.ok().entity("The Support is updated successfully").build();  

	  }catch(Exception e) {
		  return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
	  }
  }
  
  @DELETE
  @Path("/{id}")
  public Response deleteSupport(@PathParam("id") Long id) {
	  try {
		  Support support = dao.findOne(id);
		  if(support==null)
			  return Response.status(Response.Status.NOT_FOUND).entity("There is no Support with the id="+id).build();  
		 
		  dao.delete(support);
		  return Response.ok().entity(new OldDataFormator<SupportDto>(new SupportDto(support),"The Support has been deleted successfully" )).build();
	  }catch(Exception e) {
		  return Response.status(500).entity(e.getMessage()).build();
	  }
  }
}

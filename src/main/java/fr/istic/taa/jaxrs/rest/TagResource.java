package fr.istic.taa.jaxrs.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import fr.istic.taa.jaxrs.dto.TagCreateDto;
import fr.istic.taa.jaxrs.dto.TagDto;
import fr.istic.taa.jaxrs.dto.UserCreateDto;
import fr.istic.taa.jaxrs.dto.UserDto;
import fr.istic.taa.jaxrs.services.DefaultValidator;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;

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
  public List<TagDto> getTags()  {      
      List<TagDto> list = new ArrayList<>();
	  for(Tag tag: this.dao.findAll()){
		  list.add(new TagDto(tag));
	  }
	  return list;
  }
  
  @GET
  @Path("/{id}")
  public Response getTagById(@PathParam("id") Long id)  {
      try {
    	  Tag tag = this.dao.findOne(id);
    	  if(tag!=null) {
    		  TagDto tagDto = new TagDto(tag);
    		  return Response.ok().entity(tagDto).build();  
    	  }else {
    		  return Response.status(Response.Status.NOT_FOUND).entity("There is no Tag with the id="+id).build(); 
    	  }
	  }catch(Exception e) {
		  return Response.status(500).entity(e.getMessage()).build();
	  }
  }

  @POST
  @Path("/")
  @Consumes("application/json")
  public Response addTag(
		  @Parameter(description = "Tag object that needs to be added to the store", required = true) TagCreateDto tagDto) {
	  try {
		  DefaultValidator<TagCreateDto> validator = new DefaultValidator<>();
		  Set<ConstraintViolation<TagCreateDto>> violations = validator.getValidator().validate(tagDto);
	      if (violations.size()>0) {
	    	  return validator.toResponse(violations);
	      }else {
	    	  Tag tag = new Tag();
	    	  tag.setName(tagDto.getName());
	    	  dao.save(tag);
	    	  return Response.ok().entity("The tag is created successfully").build();
	      }
	  }catch(Exception e) {
		  return Response.status(Response.Status.BAD_REQUEST)
                  .entity(e.getMessage())
                  .build();
	  }
   
  }
}
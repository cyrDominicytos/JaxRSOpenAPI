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

import fr.istic.taa.jaxrs.dao.generic.TagDao;
import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.dto.TagCreateDto;
import fr.istic.taa.jaxrs.dto.TagDto;
import fr.istic.taa.jaxrs.services.DefaultValidator;
import fr.istic.taa.jaxrs.services.OldDataFormator;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.ConstraintViolation;

/** 
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
	    	  tag.setBackgroundColor(tagDto.getBackgroundColor());
	    	  tag.setTextColor(tagDto.getTextColor());
	    	  dao.save(tag);
	    	  return Response.ok().entity(new TagDto(tag)).build();
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
  public Response updateTag(
		  @PathParam("id") Long id,  
		  @Parameter(description = "Tag object that needs to be updated", required = true) TagCreateDto tagDto)  {
      try {
    	  	  //Check if the tag id is valid
	    	  Tag tag = this.dao.findOne(id);
	    	  if(tag!=null) {
	    		  //Validate the constraints on the tag object
	    		  DefaultValidator<TagCreateDto> validator = new DefaultValidator<>();
	    		  Set<ConstraintViolation<TagCreateDto>> violations = validator.getValidator().validate(tagDto);
	    	      if (violations.size()>0) 
	    	    	  return validator.toResponse(violations); //one or many constraints are violated, return an error
	    	      
	    		  tag.setName(tagDto.getName());
	    		  tag.setBackgroundColor(tagDto.getBackgroundColor());
		    	  tag.setTextColor(tagDto.getTextColor());
				  dao.update(tag);
				  return Response.ok().entity("The Tag is updated successfully").build();  
    	  }else {
    		  return Response.status(Response.Status.NOT_FOUND).entity("There is no Tag with the id="+id).build(); 
    	  }
	  }catch(Exception e) {
		  return Response.status(500).entity(e.getMessage()).build();
	  }
  }
  
  @DELETE
  @Path("/{id}")
  public Response deleteTag(@PathParam("id") Long id) {
	  try {
		  Tag tag = dao.findOne(id);
		  if(tag==null)
		  {
			  return Response.status(Response.Status.NOT_FOUND).entity("There is no Tag with the id="+id).build();  
		  }
		  //check if this tag is not in use into a ticket
		  if(dao.canBeDeleted(id)){
			  dao.delete(tag);
			  return Response.ok().entity(new OldDataFormator<TagDto>(new TagDto(tag),"The Tag has been deleted successfully" )).build();
		  }else {
			  return Response.status(Response.Status.UNAUTHORIZED).entity("This tag is now in use in one or many ticket, it can not be deleted").build();
		  }
	  }catch(Exception e) {
		  return Response.status(500).entity(e.getMessage()).build();
	  }
  }
}
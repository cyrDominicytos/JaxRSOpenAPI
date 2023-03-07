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

import fr.istic.taa.jaxrs.dao.generic.BugDao;
import fr.istic.taa.jaxrs.dao.generic.TagDao;
import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.Bug;
import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.BugCreateDto;
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
		  @Parameter(description = "Bug object that needs to be added to the store", required = true) BugCreateDto bugDto) {
	  try {
		  DefaultValidator<BugCreateDto> validator = new DefaultValidator<>();
		  Set<ConstraintViolation<BugCreateDto>> violations = validator.getValidator().validate(bugDto);
	      if (violations.size()>0) {
	    	  return validator.toResponse(violations);
	      }else {
	    	  
	    	  Bug bug = new Bug();
	    	  bug.setBody(bugDto.getBody());
	    	  
	    	  List<Long> tagsId  = bugDto.getTagsId();
	    	  //retieve tags and assign them to the Ticket (bug)
	    	  TagDao tagDao = new TagDao();
	    	  List<Tag> assignedTags = tagDao.findAllExistingElementList(tagsId);
	    	  if(assignedTags.size()==0)
	    		  return Response.status(Response.Status.BAD_REQUEST).entity("You have to add one or more tags to your bug").build();
	    	  
	    	  bug.setTags(assignedTags);
			  dao.save(bug);
			  return Response.ok().entity("New Bug Ticket created successfully").build(); 
	      }
	  }catch(Exception e) {
		  return Response.status(Response.Status.BAD_REQUEST)
                  .entity(e.getMessage())
                  .build();
	  }
  }
  
}

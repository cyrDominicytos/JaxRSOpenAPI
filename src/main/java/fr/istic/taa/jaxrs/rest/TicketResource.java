package fr.istic.taa.jaxrs.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import fr.istic.taa.jaxrs.dao.generic.TicketDao;
import fr.istic.taa.jaxrs.dao.generic.SupportDao;
import fr.istic.taa.jaxrs.dao.generic.TagDao;
import fr.istic.taa.jaxrs.dao.generic.TicketDao;
import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.Support;
import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.TicketCreateDto;
import fr.istic.taa.jaxrs.dto.TicketListDto;
import fr.istic.taa.jaxrs.dto.UserCreateDto;
import fr.istic.taa.jaxrs.dto.UserDto;
import fr.istic.taa.jaxrs.services.DefaultValidator;
import fr.istic.taa.jaxrs.services.State;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
@Path("/tickets")
@Produces({"application/json"})
public class TicketResource {
	private TicketDao dao;
	public TicketResource() {
		this.dao = new TicketDao();
	}
	
  @GET
  @Path("/")
  public Response getTickets()  {       
	  try {
		  List<TicketListDto> list = new ArrayList<>();
		  for(Ticket t: this.dao.findAll()){
			  list.add(new TicketListDto(t));
		  }
		  
		  return Response.ok().entity(list).build();
	  }catch(Exception e) {
		  return Response.status(500).entity(e.getMessage()).build();
	  }
  }
  
  @GET
  @Path("/{id}")
  public Response getTicketById(@PathParam("id") Long id)  { 
	 try {
		  Ticket t =  this.dao.findOne(id);
    	  if(t!=null) {
    		  TicketListDto tDto = new TicketListDto(t);
    		  return Response.ok().entity(tDto).build();  
    	  }else {
    		  return Response.status(Response.Status.NOT_FOUND).entity("There is no ticket with the id="+id).build(); 
    	  }
	  }catch(Exception e) {
		  return Response.status(500).entity(e.getMessage()).build();
	  }
  }

  @POST
  @Path("/")
  @Consumes("application/json")
  public Response addTicket(
		  @Parameter(description = "Ticket object that needs to be added to the store", required = true) TicketCreateDto ticketDto) {
	  try {
		  DefaultValidator<TicketCreateDto> validator = new DefaultValidator<>();
		  Set<ConstraintViolation<TicketCreateDto>> violations = validator.getValidator().validate(ticketDto);
	      if (violations.size()>0) {
	    	  return validator.toResponse(violations);
	      }else {
	    	  
	    	  Ticket ticket = new Ticket();
	    	  ticket.setContent(ticketDto.getContent());
	    	  ticket.setTitle(ticketDto.getTitle());
	    	  
	    	  /**
	    	   * Assign the appropriate user (the owner of the ticket) to the ticket
	    	   */
	    	  UserDao uDao = new UserDao();
	    	  User user = uDao.findOne(ticketDto.getUser_id());
	    	  if(user==null)
	    		  return Response.status(Response.Status.BAD_REQUEST).entity("The user id that you assign to the ticket doesn't exist.").build();
	    	  ticket.setUser(user);
	    	  
	    	  /**
	    	   * retieve submited tags, check if there are valid 
	    	   * and then assign them to the Ticket.
	    	   */
	    	  List<Long> tagsId  = ticketDto.getTagsId();
	    	  TagDao tagDao = new TagDao();
	    	  List<Tag> assignedTags = tagDao.findAllExistingElementList(tagsId);
	    	  if(assignedTags.size()==0)
	    		  return Response.status(Response.Status.BAD_REQUEST).entity("You have to add at least one valid tag to your Ticket.").build();
	    	  
	    	  ticket.setTags(assignedTags);
			  dao.save(ticket);
			  return Response.ok().entity(new TicketListDto(ticket)).build(); 
	      }
	  }catch(Exception e) {
		  return Response.status(Response.Status.BAD_REQUEST)
                  .entity(e.getMessage())
                  .build();
	  }
  }
  
  @PUT
  @Path("/assign_support/{id}")
  @Consumes("application/json")
  public Response assignSupport(
  		  @PathParam("id") Long id, 
  		  @Parameter(description="The list of support id that you want to affect to the ticket", required = true) List<Long> supportIds
  		  ) {
  	  try {
  		  //Check if the tag id is valid
    	  Ticket ticket = this.dao.findOne(id);
    	  if(ticket==null) 
    		  return Response.status(Response.Status.NOT_FOUND).entity("There is no ticket with the id="+id).build(); 
    	  
    	  /**
    	   * retieve submited support id, check if there are valid 
    	   * and then assign them to the Ticket.
    	   */
    	  SupportDao supportDao = new SupportDao();
    	  List<Support> validSupports = supportDao.findAllExistingElementList(supportIds);
    	  if(validSupports.size()==0)
    		  return Response.status(Response.Status.BAD_REQUEST).entity("You have to add at least one valid support to your Ticket.").build();
    	  
    	  ticket.setAssignedSupport(validSupports);
  		  dao.update(ticket);
  		  return Response.ok().entity(new TicketListDto(ticket)).build(); 
  		  //return Response.ok().entity("The User is updated successfully").build();  
  		  
  	 }catch(Exception e) {
  		  return Response.status(Response.Status.BAD_REQUEST)
                  .entity(e.getMessage())
                  .build();
  	  }
  }
   
}
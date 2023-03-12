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

import fr.istic.taa.jaxrs.dao.generic.TicketDao;
import fr.istic.taa.jaxrs.dao.generic.TagDao;
import fr.istic.taa.jaxrs.dao.generic.TicketDao;
import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.TicketCreateDto;
import fr.istic.taa.jaxrs.dto.TicketListDto;
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
	    	  
	    	  /**
	    	   * Assign the appropriate user (the owner of the ticket) to the ticket
	    	   */
	    	  UserDao uDao = new UserDao();
	    	  User user = uDao.findOne(ticketDto.getUser_id());
	    	  if(user==null)
	    		  return Response.status(Response.Status.BAD_REQUEST).entity("The user id that you assign to the ticket is not valid").build();
	    	  ticket.setUser(user);
	    	  
	    	  /**
	    	   * retieve submited tags, check if there are valid 
	    	   * and then assign them to the Ticket (Ticket)
	    	   */
	    	  List<Long> tagsId  = ticketDto.getTagsId();
	    	  TagDao tagDao = new TagDao();
	    	  List<Tag> assignedTags = tagDao.findAllExistingElementList(tagsId);
	    	  if(assignedTags.size()==0)
	    		  return Response.status(Response.Status.BAD_REQUEST).entity("You have to add at least one valid tag to your Ticket").build();
	    	  
	    	  ticket.setTags(assignedTags);
			  dao.save(ticket);
			  return Response.ok().entity("New Ticket created successfully").build(); 
	      }
	  }catch(Exception e) {
		  return Response.status(Response.Status.BAD_REQUEST)
                  .entity(e.getMessage())
                  .build();
	  }
  }
  
}

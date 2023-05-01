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

import fr.istic.taa.jaxrs.dao.generic.MessageDao;
import fr.istic.taa.jaxrs.dao.generic.TicketDao;
import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.Message;
import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.MessageCreateDto;
import fr.istic.taa.jaxrs.dto.MessageDto;
import fr.istic.taa.jaxrs.dto.TagCreateDto;
import fr.istic.taa.jaxrs.dto.TagDto;
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

@Path("/messages")
@Produces({"application/json"})
public class MessageResource {
	private MessageDao dao;
	public MessageResource() {
		this.dao = new MessageDao();
	}
	
  @GET
  @Path("/")
  public Response getMessages()  {
	  try {
		  
		  List<Message> messages = this.dao.findAll();
	      List<MessageDto> list = new ArrayList<>();
	      for(Message m: messages) {
	    	  list.add(new MessageDto(m));
	      }
	      return Response.ok().entity(list).build();
	  }catch(Exception e) {
		  return Response.status(500).entity(e.getMessage()).build();
	  }
     
  }
  
  @GET
  @Path("/ticket/{id}")
  public Response getMessageByTicketId(@PathParam("id") Long id)  {
	  try {
		  List<Message> messages = this.dao.findTicketMessages(id);
	      List<MessageDto> list = new ArrayList<>();
	      for(Message m: messages) {
	    	  list.add(new MessageDto(m));
	      }
	      return Response.ok().entity(list).build();
	  }catch(Exception e) {
		  return Response.status(500).entity(e.getMessage()).build();
	  }
  }
  
  @GET
  @Path("/{id}")
  public Response getMessageById(@PathParam("id") Long id)  {
	  try {
		  Message m =  this.dao.findOne(id);
		  if(m!=null) {
			  return Response.ok().entity(new MessageDto(m)).build();  
		  }else {
			  return Response.status(Response.Status.NOT_FOUND).entity("There is no message with the id="+id).build(); 
		  }
	  }catch(Exception e) {
		  return Response.status(500).entity(e.getMessage()).build();
	  }
  }

  @POST
  @Path("/")
  @Consumes("application/json")
  public Response addMessage(
		  @Parameter(description = "Message object that needs to be added to the store", required = true)
		  
		  MessageCreateDto mesDto) {
	  try {
		  DefaultValidator<MessageCreateDto> validator = new DefaultValidator<>();
		  Set<ConstraintViolation<MessageCreateDto>> violations = validator.getValidator().validate(mesDto);
	      if (violations.size()>0) {
	    	  return validator.toResponse(violations);
	      }else {

	    	  /**
	    	   * Assign the appropriate user (the owner of the message) to the message
	    	   */
	    	  UserDao uDao = new UserDao();
	    	  User user = uDao.findOne(mesDto.getUserId());
	    	  if(user==null)
	    		  return Response.status(Response.Status.BAD_REQUEST).entity("The user id that you assign to the message doesn't exist.").build();
	    	
	    	  /**
	    	   * Assign the appropriate ticket (the related ticket) to the message
	    	   */
	    	  TicketDao tDao = new TicketDao();
	    	  Ticket t = tDao.findOne(mesDto.getTicketId());
	    	  if(t==null)
	    		  return Response.status(Response.Status.BAD_REQUEST).entity("The ticket id that you assign to the message doesn't exist.").build();
	    	
	    	 Message m = new Message();
	    	 m.setContent(mesDto.getContent());
	    	 m.setUser(user);
	    	 m.setTicket(t);
	    	 dao.save(m);
	    	 return Response.ok().entity(new MessageDto(m)).build();
	      }
	  }catch(Exception e) {
		  return Response.status(Response.Status.BAD_REQUEST)
                  .entity(e.getMessage())
                  .build();
	  }
  }
}
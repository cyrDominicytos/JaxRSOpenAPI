package fr.istic.taa.jaxrs.rest;

import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import fr.istic.taa.jaxrs.dao.generic.MessageDao;
import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.Message;
import fr.istic.taa.jaxrs.domain.User;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */

@Path("/users")
@Produces({"application/json"})
public class MessageResource {
	private MessageDao dao;
	public MessageResource() {
		this.dao = new MessageDao();
	}
	
  @GET
  @Path("/")
  public List<Message> getMessages()  {
      return this.dao.findAll();
  }
  
  @GET
  @Path("/{id}")
  public Message getMessageById(@PathParam("id") Long id)  {
      return this.dao.findOne(id);
  }

  @POST
  @Path("/")
  @Consumes("application/json")
  public Response addMessage(
		  @Parameter(description = "Message object that needs to be added to the store", required = true)
		  @Valid
		  Message mes) {
	  dao.save(mes);
    return Response.ok().entity(mes).build();
  }
}
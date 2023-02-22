package fr.istic.taa.jaxrs.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.messageinterpolation.*;
import org.hibernate.validator.resourceloading.AggregateResourceBundleLocator;

import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.UserCreateDto;
import fr.istic.taa.jaxrs.dto.UserDto;
import fr.istic.taa.jaxrs.exceptions.CustomValidationException;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */

@Path("/users")
@Produces({"application/json"})
public class UsersResource {
	private UserDao dao;
	public UsersResource() {
		this.dao = new UserDao();
	}
	
  @GET
  @Path("/")
  public List<UserDto> getUsers()  {
	  List<UserDto> list = new ArrayList<>();
	  for(User user: this.dao.findAll()){
		  list.add(new UserDto.UserDtoBuilder(user).build());
	  }
	  return list;
  }
  
  @GET
  @Path("/{id}")
  public UserDto getUserById(@PathParam("id") Long id)  {
      User u =  this.dao.findOne(id);
      return new UserDto.UserDtoBuilder(u).build();
  }

  @POST
  @Path("/")
 // @Consumes("application/json")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addUser(
	  @Parameter(description = "User object that needs to be added to the store", required = true)
	  @Valid UserCreateDto userDto) {
	 try{
		  User user = new User();
		  user.setEmail(userDto.getEmail());
		  user.setName(userDto.getName());
		  dao.save(user);
		  UserDto dto = new UserDto.UserDtoBuilder(user).build();
		  return Response.ok().entity(dto).build();
	 } catch (Exception e) {
		 	//e.ge
		  return  Response.status(500).entity(e).build();	  
	  }
  }
  
  static {
      ResourceBundle bundle = ResourceBundle.getBundle("ValidationMessages");
      ValidationMessagesResolver.getInstance().addMessageInterpolator(new ResourceBundleMessageInterpolator(new AggregateResourceBundleLocator(Arrays.asList(bundle))));
  }
}
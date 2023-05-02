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

import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.domain.Support;
import fr.istic.taa.jaxrs.dto.UserConnectDto;
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

@Path("/users")
@Produces({"application/json"})
public class UserResource{
	private UserDao dao;
	public UserResource() {
		this.dao = new UserDao();
	}
	
  @GET
  @Path("/")
  public Response getUsers()  {
	  List<UserDto> list = new ArrayList<>();
	  for(User user: this.dao.findAll()){
		  list.add(new UserDto(user));
	  }
	  return Response.status(Response.Status.OK).entity(list).build(); 
  }
  
  @GET
  @Path("/{id}")
  public Response getUserById(@PathParam("id") Long id)  {
      try {
    	  User u =  this.dao.findOne(id);
    	  if(u!=null) {
    		  UserDto uDto = new UserDto(u);
    		  return Response.ok().entity(uDto).build();  
    	  }else {
    		  return Response.status(Response.Status.NOT_FOUND).entity("There is no user with the id="+id).build(); 
    	  }
	  }catch(Exception e) {
		  return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	  }
  }

  @POST
  @Path("/")
  @Consumes("application/json")
  public Response addUser(@Parameter(description = "User object that needs to be added to the store", required = true) UserCreateDto userDto) {
	  try {
		  DefaultValidator<UserCreateDto> validator = new DefaultValidator<>();
		  Set<ConstraintViolation<UserCreateDto>> violations = validator.getValidator().validate(userDto);
	      if (violations.size()>0) {
	    	  return validator.toResponse(violations);
	      }else {
	    	  User user = new User();
			  user.setEmail(userDto.getEmail());
			  user.setName(userDto.getName());
			  user.setPassword(userDto.getPassword()); 
			  dao.save(user);
			  user.setRole("User");
			  UserDto dto = new UserDto(user);
			  return Response.ok().entity(dto).build(); 
	      }
	  }catch(Exception e) {
		  return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                  .entity(e.getMessage() +"=> Email/Password incorrect")
                  .build();
	  }
  }
  
  @POST
  @Path("/login")
  @Consumes("application/json")
  public Response connectUser(
		  @Parameter(description = "The user email and password as an object", required = true) UserConnectDto userDto ) {
	  try {
		  DefaultValidator<UserConnectDto> validator = new DefaultValidator<>();
		  Set<ConstraintViolation<UserConnectDto>> violations = validator.getValidator().validate(userDto);
	      if (violations.size()>0) {
	    	  return validator.toResponse(violations);
	      }else {
	    	  User user = this.dao.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
	    	  if(user==null)
	    		  return Response.status(Response.Status.FORBIDDEN).entity("Email/Password incorrect").build(); 
			 
			  UserDto dto = new UserDto(user);
			  return Response.ok().entity(dto).build(); 
	      }
	  }catch(Exception e) {
		  return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                  .entity(e.getMessage())
                  .build();
	  }
  }
  
  @PUT
  @Path("/{id}")
  @Consumes("application/json")
  public Response updateUser(
		  @PathParam("id") Long id, 
		  @Parameter(description="User object that needs to be added to the store", required = true) UserCreateDto userDto) {
	  try {
		  //Check if the tag id is valid
    	  User user = this.dao.findOne(id);
    	  if(user!=null) {
    		  //Validate the constraints on the user object
    		  DefaultValidator<UserCreateDto> validator = new DefaultValidator<>();
    		  Set<ConstraintViolation<UserCreateDto>> violations = validator.getValidator().validate(userDto);
    	      if (violations.size()>0) 
    	    	  return validator.toResponse(violations); //one or many constraints are violated, return an error
    	      
    	      user.setEmail(userDto.getEmail());
			  user.setName(userDto.getName());
			  user.setPassword(userDto.getPassword());
			  dao.update(user);
			  return Response.ok().entity("The User is updated successfully").build();  
		  }else {
			  return Response.status(Response.Status.NOT_FOUND).entity("There is no User with the id="+id).build(); 
		  }
	  }catch(Exception e) {
		  return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	  }
  }
  
  @DELETE
  @Path("/{id}")
  public Response deleteUser(@PathParam("id") Long id) {
	  try {
		  User user = dao.findOne(id);
		  if(user==null)
		  {
			  return Response.status(Response.Status.NOT_FOUND).entity("There is no User with the id="+id).build();  
		  }
		 
		  dao.delete(user);
		  return Response.ok().entity(new OldDataFormator<UserDto>(new UserDto(user),"The User has been deleted successfully" )).build();
	  }catch(Exception e) {
		  return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	  }
  }
  
}
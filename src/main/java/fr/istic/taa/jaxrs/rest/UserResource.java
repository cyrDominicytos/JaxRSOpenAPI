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
import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.TagCreateDto;
import fr.istic.taa.jaxrs.dto.TagDto;
import fr.istic.taa.jaxrs.dto.UserCreateDto;
import fr.istic.taa.jaxrs.dto.UserDto;
import fr.istic.taa.jaxrs.dto.UserDto.UserDtoBuilder;
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
  public List<UserDto> getUsers()  {
	  List<UserDto> list = new ArrayList<>();
	  for(User user: this.dao.findAll()){
		  list.add(new UserDto.UserDtoBuilder(user).build());
	  }
	  return list;
  }
  
  @GET
  @Path("/{id}")
  public Response getUserById(@PathParam("id") Long id)  {
      try {
    	  User u =  this.dao.findOne(id);
    	  if(u!=null) {
    		  UserDto uDto = new UserDto.UserDtoBuilder(u).build();
    		  return Response.ok().entity(uDto).build();  
    	  }else {
    		  return Response.status(Response.Status.NOT_FOUND).entity("There is no user with the id="+id).build(); 
    	  }
	  }catch(Exception e) {
		  return Response.status(500).entity(e.getMessage()).build();
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
			  dao.save(user);
			  UserDto dto = new UserDto.UserDtoBuilder(user).build();
			  return Response.ok().entity(dto).build(); 
	      }
	  }catch(Exception e) {
		  return Response.status(Response.Status.BAD_REQUEST)
                  .entity(e.getMessage())
                  .build();
	  }
  }
  
  @PUT
  @Path("/")
  @Consumes("application/json")
  public Response updateUser(@PathParam("id") Long id, @Parameter(description="User object that needs to be added to the store", required = true) UserCreateDto userDto) {
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
			  dao.update(user);
			  return Response.ok().entity("The User is updated successfully").build();  
		  }else {
			  return Response.status(Response.Status.NOT_FOUND).entity("There is no User with the id="+id).build(); 
		  }
	  }catch(Exception e) {
		  return Response.status(Response.Status.BAD_REQUEST)
                  .entity(e.getMessage())
                  .build();
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
		  return Response.ok().entity(new OldDataFormator<UserDto>(new UserDtoBuilder(user).build(),"The Tag has been deleted successfully" )).build();
	  }catch(Exception e) {
		  return Response.status(500).entity(e.getMessage()).build();
	  }
  }
  
}
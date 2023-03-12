package fr.istic.taa.jaxrs.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.ws.rs.core.Response;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 * 
 * This class will customize a response for violated constraint.  
 * (It will be used to return more readable message errors for the client) 
 */

public class DefaultValidator<T> {
				
		private final Validator validator;
	    public Validator getValidator() {
			return validator;
		}

		
	    public DefaultValidator() {
	    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	        validator = factory.getValidator();
	    }
	    
	    /**
	     * Return appropriate response with a hasmap of [constraintViolated=>appropriate message]
	     * @param violations, the violated constraint
	     * @return
	     */
	    public Response toResponse(Set<ConstraintViolation<T>> violations ) {
			 return Response.status(Response.Status.BAD_REQUEST)
	                 .entity(prepareMessage(violations))
	                 .build();
		}

	    /**
	     * Retrieve all violated constraint and its message and return as array of key value [constraintViolated=>appropriate message]
	     * @param violations, the violated constraint
	     * @return a hasmap of [constraintViolated=>appropriate message]
	     */
		private HashMap<String, String> prepareMessage(Set<ConstraintViolation<T>> violations ) {
		     Iterator<ConstraintViolation<T>> it = violations.iterator();
		     HashMap<String, String> errors = new HashMap<String, String>();
		      while(it.hasNext()) {
		    	  ConstraintViolation<T> constraintViolation =  it.next();
		    	  errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
		      }
		      return errors;
		  }
	}


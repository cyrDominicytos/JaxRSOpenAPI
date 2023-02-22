package fr.istic.taa.jaxrs.rest;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javax.el.ExpressionFactory;

public class AbstractResource {
			
	private final Validator validator;
    public Validator getValidator() {
		return validator;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	private final ObjectMapper objectMapper;
    
    public AbstractResource() {
    	ValidatorFactory validatorFactory = Validation.byDefaultProvider()
    		    .configure()
    		    .messageInterpolator(
    		        new ResourceBundleMessageInterpolator(
    		            new PlatformResourceBundleLocator("ValidationMessages")
    		        )
    		    )
    		    .buildValidatorFactory();
        //ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
         validator = validatorFactory.getValidator();
       // validator = validatorFactory.getValidator();
        
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}

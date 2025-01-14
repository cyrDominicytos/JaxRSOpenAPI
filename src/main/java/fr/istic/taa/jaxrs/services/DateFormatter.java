package fr.istic.taa.jaxrs.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 * 
 * This class will convert LocalDateTime to a more readable date string to the client
 *
 */
public class DateFormatter {

	 public static String formatLocalDateTime(LocalDateTime date, String... formats) {
		 String format = "dd-MM-yyyy HH:mm:ss";
		 if(formats.length>0)
			 format=formats[0] ;
		 
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
	        return date.format(formatter);	      
	    }
}

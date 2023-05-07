package fr.istic.taa.jaxrs;

import io.undertow.Undertow;
import io.undertow.servlet.api.FilterInfo;

import java.util.logging.Logger;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

import fr.istic.taa.jaxrs.dao.generic.SupportDao;
import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.Support;
import fr.istic.taa.jaxrs.domain.User;



/**
 * RESTfull microservice, based on JAX-RS and JBoss Undertow
 *
 */
public class RestServer {
    private static final Logger logger = Logger.getLogger(RestServer.class.getName());
    
    public static void main( String[] args ) {
        UndertowJaxrsServer ut = new UndertowJaxrsServer();
        TestApplication ta = new TestApplication();
        ut.deploy(ta);
        ut.start(
                Undertow.builder().addHttpListener(8080, "localhost")
        );
        logger.info("JAX-RS based micro-service running!");
        
        initDB();
    }
    
    private static void initDB() {
    	try {
    		UserDao uDao = new UserDao();
    		
    		//Check if the default user is in the db
    		User defaultUser = uDao.findByEmailAndPassword("user@gmail.com","User@2023");
    		if(defaultUser==null)
    		{
    			//ajouter un utilisateur par défaut
    			User user = new User();
    			user.setEmail("user@gmail.com");
    			user.setPassword("User@2023");
    			uDao.save(user);
    			System.out.println("Default user created successfully");
    		}
    		
    		//check if the default support (it has the full control on the system) is in the db 
    		User defaultSupport = uDao.findByEmailAndPassword("support@gmail.com","Support@2023");
    		if(defaultSupport==null) {
    			SupportDao sDao = new SupportDao();
    			Support support = new Support();
    			support.setEmail("support@gmail.com");
    			support.setPassword("Support@2023");
    			sDao.save(support);
    			System.out.println("Default support created successfully");
    		}
    		
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    }
}



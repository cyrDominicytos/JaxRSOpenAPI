package fr.istic.taa.jaxrs;

import io.undertow.Undertow;

import org.glassfish.jersey.server.ResourceConfig;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

import com.google.common.io.Resources;

import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.ws.rs.core.Application;

/**
 * RESTfull microservice, based on JAX-RS and JBoss Undertow
 *
 */
public class RestServer {

    private static final Logger logger = Logger.getLogger(RestServer.class.getName());

    public static void main( String[] args ) {
    	//ResourceBundle bundle = ResourceBundle.getBundle("validation-messages");
    	
    	
        /*UndertowJaxrsServer ut = new UndertowJaxrsServer();

        TestApplication ta = new TestApplication();

        ut.deploy(ta);
        ut.start(
                Undertow.builder()
                        .addHttpListener(8080, "localhost")
        );
        logger.info("JAX-RS based micro-service running!");
        */
    	        
        //ResourceConfig resourceConfig = new ResourceConfig() .register(TestApplication.class);
        
        UndertowJaxrsServer server = new UndertowJaxrsServer();
        TestApplication ta = new TestApplication();

      //  ut.deploy(ta);
        server.deploy(ta);
        server.start(Undertow.builder()
                .addHttpListener(8080, "localhost"));
        logger.info("JAX-RS based micro-service running!");
    }
}

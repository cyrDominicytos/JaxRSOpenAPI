/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.istic.taa.jaxrs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import fr.istic.taa.jaxrs.exceptions.CustomValidationException;
import fr.istic.taa.jaxrs.rest.BugResource;
import fr.istic.taa.jaxrs.rest.FeatureResource;
import fr.istic.taa.jaxrs.rest.SupportResource;
import fr.istic.taa.jaxrs.rest.SwaggerResource;
import fr.istic.taa.jaxrs.rest.TagResource;
import fr.istic.taa.jaxrs.rest.UserResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

public class TestApplication extends Application {
	
	/*public TestApplication() {
		 packages("com.baeldung.jersey.server");
		 property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
	}*/
	
	
	/*public TestApplication() {
        packages("com.baeldung.jersey.server");
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        register(CustomValidationException.class);
        register(UserResource.class);
        register(BugResource.class);
        register(TagResource.class);
        register(SupportResource.class);
        register(OpenApiResource.class);
        register(SwaggerResource.class);
        
    }*/
	
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> clazzes = new HashSet<Class<?>>();
        
        clazzes.add(UserResource.class);
        clazzes.add(BugResource.class);
        clazzes.add(FeatureResource.class);
        clazzes.add(TagResource.class);
        clazzes.add(SupportResource.class);
        clazzes.add(OpenApiResource.class);
        clazzes.add(SwaggerResource.class);
        //clazzes.add(CustomValidationException.class);
        
        return clazzes;
    }

}

package com.d288.idris.config;

import com.d288.idris.entities.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * <h1>RestDataConfig</h1>
 * per course instructions include this code to configure the rest api end-points exposed for the project
 * TODO restrict non-used rest api end points
 * <p>
 *
 * @author WGU Course Materials
 * @version 0.1
 * @since 2023-02-27
 */
@Configuration
public class RestDataConfig implements RepositoryRestConfigurer {

    /**
     * This method exposes standard rest api end points for the following classes:
     * Country
     * Customer
     * Division
     * Excursion
     * Vacation
     * <p>
     * Set page configuration parameters
     *
     * @param config
     * @param cors
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // Expose IDs for the entities
        config.exposeIdsFor(Country.class);
        config.exposeIdsFor(Customer.class);
        config.exposeIdsFor(Division.class);
        config.exposeIdsFor(Excursion.class);
        config.exposeIdsFor(Vacation.class);

        // Set page configuration parameters
        config.setDefaultPageSize(Integer.MAX_VALUE);
        config.setMaxPageSize(Integer.MAX_VALUE);

        // Configure CORS
       /* cors.addMapping("/**")
                .allowedOriginPatterns("http://localhost:4200")  // Allows any port on localhost
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);

        */

    }
}


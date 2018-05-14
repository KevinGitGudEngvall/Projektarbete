package se.group.projektarbete.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("se.group.projektarbete.web");
    }


    @Bean
    public ObjectMapper objectMapper() {
        // Adds support for Java 8 parameter preservation
        // Note: In IntelliJ, set -parameters option in Build, Execution, Deployment > Compiler > Java Compiler >
        // Additional command line parameters
        // Remember to rebuild project!
        return new ObjectMapper().registerModule(new ParameterNamesModule());
    }
}

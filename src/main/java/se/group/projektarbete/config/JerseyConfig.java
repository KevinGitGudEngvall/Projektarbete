package se.group.projektarbete.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import se.group.projektarbete.web.mapper.InvalidInputMapper;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("se.group.projektarbete.resource");
        register(InvalidInputMapper.class);

    }
}

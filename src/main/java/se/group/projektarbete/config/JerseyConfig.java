package se.group.projektarbete.config;

import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("se.group.projektarbete.resource");
    }
}

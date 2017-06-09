package com.netcompany.fagarbeid.aspektsikkerhet;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("com.netcompany.fagarbeid.aspektsikkerhet");
    }
}
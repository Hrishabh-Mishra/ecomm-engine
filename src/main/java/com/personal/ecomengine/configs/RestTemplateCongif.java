package com.personal.ecomengine.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateCongif {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

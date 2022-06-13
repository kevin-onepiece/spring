package com.foo.jwtbusiness.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author foo
 * @since 2022-06-05
 */
@Configuration
public class ProjectConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

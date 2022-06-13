package com.foo.oauth2github.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

/**
 * @author foo
 * @since 2022-06-11
 */
@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    // 方法一：配置方法 方法二：配置文件
    // OAuth2LoginAUthticationFIlter 会调用 ClientRegistrationRepository
    /*@Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration clientRegistration = clientRegistration();
        return new InMemoryClientRegistrationRepository(clientRegistration);
    }

    private ClientRegistration clientRegistration() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .clientId("aba791e8aeccc6570bf9")
                .clientSecret("92e3eb775af4a6f19651e4b9287ab1a5ead341c4")
                .build();
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2Login();

        http.authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}

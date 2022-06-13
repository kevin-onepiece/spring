package com.foo.jwtbusiness.authentication.proxy;

import com.foo.jwtbusiness.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author foo
 * @since 2022-06-05
 */
@Component
@RequiredArgsConstructor
public class AuthenticationServerProxy {

    private final RestTemplate restTemplate;

    public static final String baseUrl = "http://localhost:8080";

    public void sendAuth(String username, String password) {
        String url = baseUrl + "/check-credentials";
        User user = new User().setUsername(username).setPassword(password);
        HttpEntity<Object> request = new HttpEntity<>(user);
        restTemplate.postForObject(url, request, Boolean.class);
    }

    public boolean sendOTP(String username, String code) {
        String url = baseUrl + "/check-otp";
        User user = new User().setUsername(username).setCode(code);
        HttpEntity<Object> request = new HttpEntity<>(user);
        return restTemplate.postForObject(url, request, Boolean.class);
    }


}

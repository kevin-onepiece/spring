package com.foo.jwtbusiness.authentication.provider;

import com.foo.jwtbusiness.authentication.UsernamePasswordAuthentication;
import com.foo.jwtbusiness.authentication.proxy.AuthenticationServerProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author foo
 * @since 2022-06-05
 */
@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final AuthenticationServerProxy proxy;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        proxy.sendAuth(username, password);
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthentication.class.isAssignableFrom(aClass);
    }
}

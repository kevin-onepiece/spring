package com.foo.jwtbusiness.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author foo
 * @since 2022-06-05
 */
public class UsernamePasswordAuthentication extends UsernamePasswordAuthenticationToken {
    public UsernamePasswordAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public UsernamePasswordAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}

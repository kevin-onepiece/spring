package com.foo.jwtbusiness.authentication.filter;

import com.foo.jwtbusiness.authentication.OtpAuthentication;
import com.foo.jwtbusiness.authentication.UsernamePasswordAuthentication;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author foo
 * @since 2022-06-05
 */
@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager manager;

    @Value("${jwt.signing.key}")
    private String signingKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("request.getServletPath() = " + request.getServletPath());
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String code = request.getHeader("code");
        if (code == null) {
            UsernamePasswordAuthentication authentication = new UsernamePasswordAuthentication(username, password);
            manager.authenticate(authentication);
        } else {
            Authentication otpAuthentication = new OtpAuthentication(username, code);
            manager.authenticate(otpAuthentication);
            SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
            Map<String, String > map = new HashMap<>();
            map.put("username", username);
            String jwt = Jwts.builder().setClaims(map)
                    .signWith(key)
                    .compact();
            response.setHeader("Authorization", jwt);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // 仅仅对 /login 路径应用此过滤器
        return !request.getServletPath().equals("/login");
    }
}

package com.foo.auth.service;

import com.foo.auth.model.CustomerUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author foo
 * @since 2022-06-01
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationProviderService implements AuthenticationProvider {
    private final DBUserDetailsService dbUserDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SCryptPasswordEncoder sCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 验证流程
        // 1. 先从 authentication 中获取名字和密码
        // 2. 再从数据库中判断能不能查找出数据
        // 3. 再判断密码对不对
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        CustomerUserDetails userDetails = dbUserDetailsService.loadUserByUsername(username);
        if (userDetails != null) {
            if (bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
                log.info("登录成功");
                return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
            }
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        // todo 不理解
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}

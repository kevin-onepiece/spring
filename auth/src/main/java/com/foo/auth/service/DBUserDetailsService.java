package com.foo.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.foo.auth.entity.Authority;
import com.foo.auth.entity.User;
import com.foo.auth.mapper.AuthorityMapper;
import com.foo.auth.model.CustomerUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author foo
 * @since 2022-06-01
 */
@Component
@RequiredArgsConstructor
public class DBUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final AuthorityMapper authorityMapper;

    @Override
    public CustomerUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, s).last("limit 1"));
        List<Authority> authorities = authorityMapper.selectList(new LambdaQueryWrapper<Authority>().eq(Authority::getUser, user.getId()));
        return new CustomerUserDetails(user, authorities);
    }
}

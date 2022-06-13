package com.foo.auth.model;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.foo.auth.entity.Authority;
import com.foo.auth.entity.User;
import com.foo.auth.mapper.AuthorityMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author foo
 * @since 2022-06-01
 */
@Data
public class CustomerUserDetails implements UserDetails {

    private final User user;

    private final List<Authority> authorities;

    public CustomerUserDetails(User user, List<Authority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(a -> new SimpleGrantedAuthority(a.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

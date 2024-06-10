package com.orbit.transaction.users.services;

import com.orbit.transaction.users.models.SecurityUser;
import com.orbit.transaction.users.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@RequiredArgsConstructor
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql = "SELECT username, password FROM users WHERE username = ?";
             List<SecurityUser> securityUsers =jdbcTemplate.query(sql, new UserMapper(), username);
        if (securityUsers.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        SecurityUser user = securityUsers.get(0);
        user.setRoles(String.valueOf(new SimpleGrantedAuthority("admin")));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, getAuthorities());
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("admin"));
        return authorities;
    }
}

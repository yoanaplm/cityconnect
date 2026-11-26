package com.cityconnect.main.user.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class AuthenticationDetails implements UserDetails {
    private final UUID userId;
    private final String username;
    private final String password;
    private final UserRole role;

    public AuthenticationDetails(UUID userId, String username, String password, UserRole role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UUID getUserId() {
        return userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of( new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
}

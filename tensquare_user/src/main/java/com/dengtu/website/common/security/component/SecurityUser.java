package com.dengtu.website.common.security.component;


import com.dengtu.website.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SecurityUser implements UserDetails {
    private String userName;
    private String password;
    private User user;
    private  Collection<? extends GrantedAuthority> authorities;
    private Boolean status;
    public SecurityUser(String userName, String password, Collection<? extends GrantedAuthority> authorities, Boolean status, User user) {
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
        this.status = status;
        this.user=user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    public User getUser(){
        return user;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
        return status;
    }
}

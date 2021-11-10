package com.app.springboot.customerservice.config;

import com.app.springboot.customerservice.entity.Users;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ToString
public class MyUserDetails implements UserDetails {
    private String userName;
    private String password;
    private boolean enabled;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(Users user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.authorities = new ArrayList<>();
        this.authorities.add(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return enabled;
    }
}

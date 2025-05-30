package br.com.teste.Aplicacao.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Usuario implements UserDetails {

    private String username;
    private String password;
    private List<String> roles;

     public Usuario(String username, String password, List<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null) {
            return Collections.emptyList();
        }
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implemente a lógica real
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implemente a lógica real
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implemente a lógica real
    }

    @Override
    public boolean isEnabled() {
        return true; // Implemente a lógica real
    }
}
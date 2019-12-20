//package com.lukasz.api.security;
//
//import com.lukasz.api.client.Client;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//
//public class UserPrincipal implements UserDetails {
//
//    private Client client;
//
//    public UserPrincipal(Client client) {
//        super();
//        this.client = client;
//    }
//
//    //TODO sprawdzić czy user nie zadziala a jak nie to nulla przypisać
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singleton(new SimpleGrantedAuthority("USER"));
//        //return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return client.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return client.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}

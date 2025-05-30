package com.payvantage.ethicainternetbanking.security;

import com.payvantage.ethicainternetbanking.data.model.UserTable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FetchUserDetailsFromDb implements UserDetails {

    private String emailAddress;
    private String password;
    private List<GrantedAuthority> authorityList;

    public FetchUserDetailsFromDb(UserTable userTable) {
        emailAddress = userTable.getEmail();
//        password = user.getPassword();
        authorityList = Arrays.stream(userTable.getUserRole().toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return emailAddress;
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

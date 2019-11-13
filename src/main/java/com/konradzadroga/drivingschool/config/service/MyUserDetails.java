package com.konradzadroga.drivingschool.config.service;

import com.konradzadroga.drivingschool.rest_api.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String pesel;
    private Date birthdate;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(Long id, String username, String name, String surname, String email, String pesel, Date birthdate, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.pesel = pesel;
        this.birthdate = birthdate;
        this.password = password;
        this.authorities = authorities;
    }

    public static MyUserDetails build(User user) {

        List<GrantedAuthority> authorities =
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new MyUserDetails(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPesel(),
                user.getBirthdate(),
                user.getPassword(),
                authorities

        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyUserDetails that = (MyUserDetails) o;
        return Objects.equals(id, that.id);
    }

}

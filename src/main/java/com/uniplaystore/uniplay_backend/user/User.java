package com.uniplaystore.uniplay_backend.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document(collection = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    private String id;

    private String login;

    @Indexed(unique = true) 
    private String email;

    private String password;

    private UserRole role;

    @Override
    public String toString() {
        return "User {" + "\n" +
                "Id: " + id + "\n" +
                "name: " + login + "\n" +
                "email: " + email + "\n" +
                "password: " + password + "\n" +
                '}';
    }

    public User(String login, String email, String password, UserRole role) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ROLE_ADMIN) return List.of(
                new SimpleGrantedAuthority(UserRole.ROLE_ADMIN.name()),
                new SimpleGrantedAuthority(UserRole.ROLE_USER.name()));
        else return List.of(new SimpleGrantedAuthority(UserRole.ROLE_USER.name()));
    }

    @Override
    public String getUsername() {
        return login;
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